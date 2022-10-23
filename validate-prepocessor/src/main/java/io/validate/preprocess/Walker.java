package io.validate.preprocess;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.validate.preprocess.processor.TreeWalkProcessor;
import io.validate.preprocess.processor.WalkContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Walker {

    private static final String NEW_CONFIG_ATTRIBUTE = "name";
    private static final String NEW_CONFIG_ATTRIBUTE_PARENT = "config";
    private HashMap<String,Object> objectModel;
    private String currentConfig;
    private TreeWalkProcessor treeWalkProcessor;

    Walker(TreeWalkProcessor treeWalkProcessor){
        this.treeWalkProcessor = treeWalkProcessor;
    }

    public void walk(InputStream config) throws IOException {
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};

        objectModel = new ObjectMapper().readValue(config, typeRef);
        treeWalkProcessor.processPreWalk(createPreWalkContext(objectModel));
        walkObjectModel(objectModel,null);
        treeWalkProcessor.processPostWalk(createPostWalkContext(objectModel));
    }

    private WalkContext createPostWalkContext(HashMap<String, Object> objectModel) {
        return createPreWalkContext(objectModel);
    }

    private WalkContext createPreWalkContext(HashMap<String, Object> objectModel) {
        return createWalkContext(null,null,objectModel,null);
    }

    private void walkObjectModel(Object objectModel,String parentAttribute) {
        HashMap objectModelMap = (HashMap)objectModel;
        Set<String> keys = objectModelMap.keySet();
        for(String key:keys){
            if(parentAttribute==null) parentAttribute = key;
            if(isNewConfig(key,parentAttribute))
                currentConfig = (String)objectModelMap.get(key);
            Object value = objectModelMap.get(key);
            treeWalkProcessor.processWalk(createWalkContext(key,value,objectModelMap,parentAttribute));
            if(value instanceof HashMap){
                parentAttribute = key;
                walkObjectModel(value,parentAttribute);
            }
            if(value instanceof ArrayList){
                List list = (ArrayList)value;
                if(list.get(0) instanceof HashMap){
                    parentAttribute = key;
                    for(Object listItem:list)
                        walkObjectModel(listItem,parentAttribute);
                }
            }
        }
    }

    private WalkContext createWalkContext(String key, Object value, HashMap objectModelMap, String parentAttribute) {
        WalkContext context = new WalkContext();
        context.setCurrentAttribute(key);
        context.setCurrentValue(value);
        context.setObjectModel(objectModel);
        context.setParentAttribute(parentAttribute);
        context.setCurrentConfig(currentConfig);
        return context;
    }

    private boolean isNewConfig(String key,String parentAttribute) {
        return key.equalsIgnoreCase(NEW_CONFIG_ATTRIBUTE) && parentAttribute.equalsIgnoreCase(NEW_CONFIG_ATTRIBUTE_PARENT);
    }
}

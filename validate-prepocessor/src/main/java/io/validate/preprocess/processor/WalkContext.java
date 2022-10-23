package io.validate.preprocess.processor;

import java.util.HashMap;

public class WalkContext {
    HashMap<String,Object> objectModel;
    String currentAttribute;
    Object currentValue;
    String parentAttribute;
    String currentConfig;


    public String getCurrentConfig() {
        return currentConfig;
    }

    public void setCurrentConfig(String currentConfig) {
        this.currentConfig = currentConfig;
    }

    public String getParentAttribute() {
        return parentAttribute;
    }

    public void setParentAttribute(String parentAttribute) {
        this.parentAttribute = parentAttribute;
    }

    public HashMap<String, Object> getObjectModel() {
        return objectModel;
    }

    public void setObjectModel(HashMap<String, Object> objectModel) {
        this.objectModel = objectModel;
    }

    public String getCurrentAttribute() {
        return currentAttribute;
    }

    public void setCurrentAttribute(String currentAttribute) {
        this.currentAttribute = currentAttribute;
    }

    public Object getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Object currentValue) {
        this.currentValue = currentValue;
    }
}

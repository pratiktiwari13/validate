package io.validate.preprocess.symboltable;

import io.vaidate.annotations.Precondition;
import io.vaidate.annotations.Validation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import java.util.HashMap;
import java.util.Set;

public class SymbolTable {

    private static HashMap<String,String> validations;
    private static HashMap<String,String> preconditions;
    private static SymbolTable instance;
    public static SymbolTable getInstance(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment){
        if(instance==null){
            recordValidations(processingEnvironment,roundEnvironment);
            recordPreconditions(processingEnvironment,roundEnvironment);
        }
        return instance;
    }

    private static void recordValidations(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Validation.class);
        for(Element element:elements){
            Validation annotation = element.getAnnotation(Validation.class);
            String key = annotation.name();
            String value = element.getSimpleName().toString();
            validations.put(key,value);
        }
    }

    private static void recordPreconditions(ProcessingEnvironment processingEnvironment, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Precondition.class);
        for(Element element:elements){
            Precondition annotation = element.getAnnotation(Precondition.class);
            String key = annotation.name();
            String value = element.getSimpleName().toString();
            preconditions.put(key,value);
        }
    }
}

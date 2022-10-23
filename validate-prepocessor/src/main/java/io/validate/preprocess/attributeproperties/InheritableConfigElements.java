package io.validate.preprocess.attributeproperties;

public enum InheritableConfigElements {
    PROPERTIES("properties"),
    RULES("inherits");

    private String value;
    InheritableConfigElements(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

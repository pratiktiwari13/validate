package io.validate.preprocess.attributeproperties;

public enum ProcessableConfigElements {
    ROOT("root"),
    PROPERTIES("properties"),
    INHERITS("inherits"),
    VALIDATION("validation"),
    NAME("name");

    private String value;
    ProcessableConfigElements(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

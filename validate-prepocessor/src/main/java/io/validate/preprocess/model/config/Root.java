/* (C)2023 */
package io.validate.preprocess.model.config;

import java.util.List;

public class Root {

    private String name;
    private List<Property> properties;
    private Inherits inherits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Inherits getInherits() {
        return inherits;
    }

    public void setInherits(Inherits inherits) {
        this.inherits = inherits;
    }
}

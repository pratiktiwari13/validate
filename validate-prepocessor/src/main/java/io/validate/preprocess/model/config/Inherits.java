/* (C)2023 */
package io.validate.preprocess.model.config;

import java.util.List;

public class Inherits {

    private String configName;
    private PropertyInherits propertyInherits;
    private RulesInherits rulesInherits;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public PropertyInherits getPropertyInherits() {
        return propertyInherits;
    }

    public void setPropertyInherits(PropertyInherits propertyInherits) {
        this.propertyInherits = propertyInherits;
    }

    public RulesInherits getRulesInherits() {
        return rulesInherits;
    }

    public void setRulesInherits(RulesInherits rulesInherits) {
        this.rulesInherits = rulesInherits;
    }

    private class InheritsInner {

        private List<String> inheritExcept;
        private List<String> inheritSpecific;
        private String inherit; // Can take a regex expression in future, * is an allowed value for
        // inheriting all

        public List<String> getInheritExcept() {
            return inheritExcept;
        }

        public void setInheritExcept(List<String> inheritExcept) {
            this.inheritExcept = inheritExcept;
        }

        public List<String> getInheritSpecific() {
            return inheritSpecific;
        }

        public void setInheritSpecific(List<String> inheritSpecific) {
            this.inheritSpecific = inheritSpecific;
        }

        public String getInherit() {
            return inherit;
        }

        public void setInherit(String inherit) {
            this.inherit = inherit;
        }
    }

    public class PropertyInherits extends InheritsInner {}

    public class RulesInherits extends Inherits {}
}

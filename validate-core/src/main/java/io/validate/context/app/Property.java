/* (C)2022 */
package io.validate.context.app;

public class Property {
    private String propertyPath;
    private String fullyQualifiedValidationClassName;
    private String validationName;
    private Class validation;

    public Property(
            String propertyPath, String fullyQualifiedValidationClassName, String validationName)
            throws ClassNotFoundException {
        this.propertyPath = propertyPath;
        this.validationName = validationName;
        this.validation = Class.forName(fullyQualifiedValidationClassName);
        this.validationName = validationName;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public String getFullyQualifiedValidationClassName() {
        return fullyQualifiedValidationClassName;
    }

    public String getValidationName() {
        return validationName;
    }

    public Class getValidation() {
        return validation;
    }
}

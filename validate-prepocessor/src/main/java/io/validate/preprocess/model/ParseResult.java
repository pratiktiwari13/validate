/* (C)2023 */
package io.validate.preprocess.model;

import io.validate.preprocess.model.config.ValidateConfig;

public class ParseResult {
    private ValidateConfig validateConfig;
    private ValidateParseError validateParseError;

    public ValidateConfig getValidateConfig() {
        return validateConfig;
    }

    public void setValidateConfig(ValidateConfig validateConfig) {
        this.validateConfig = validateConfig;
    }

    public ValidateParseError getValidateParseError() {
        return validateParseError;
    }

    public void setValidateParseError(ValidateParseError validateParseError) {
        this.validateParseError = validateParseError;
    }
}

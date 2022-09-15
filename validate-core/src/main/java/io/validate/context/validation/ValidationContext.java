/* (C)2022 */
package io.validate.context.validation;

import io.validate.context.request.Argument;
import java.util.List;

public class ValidationContext {
    Object rawValue;
    private List<Argument> arguments;

    public ValidationContext(List<Argument> arguments, Object rawValue) {
        this.arguments = arguments;
        this.rawValue = rawValue;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Object getRawValue() {
        return rawValue;
    }
}

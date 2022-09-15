/* (C)2022 */
package io.validate.api;

import io.validate.context.validation.ValidationContext;

public interface Validation<S> {

    default boolean preValidate(Object fieldValue, ValidationContext context) {
        return validate((S) fieldValue, context);
    }

    boolean validate(S fieldValue, ValidationContext context);
}

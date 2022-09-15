/* (C)2022 */
package io.validate.api.internal;

import io.validate.api.Validation;
import io.validate.context.app.Context;

public class DefaultValidationProvider extends AbstractDefaultResourceProvider<Validation> {
    @Override
    public Class provideResourceClass(String validationName) {
        return Context.getInstance().getValidationClass(validationName);
    }
}

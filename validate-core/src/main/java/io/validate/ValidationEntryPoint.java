/* (C)2022 */
package io.validate;

import io.validate.context.app.Context;
import io.validate.context.app.ValidationConfig;
import io.validate.context.request.RequestContext;
import java.util.List;

public class ValidationEntryPoint {

    public static void validate(RequestContext requestContext, String origin) {
        try {
            List<ValidationConfig> validationConfigs =
                    Context.getInstance().getValidationConfigs(origin);
            for (ValidationConfig validationConfig : validationConfigs) {
                if (validationConfig.shouldExecuteBasedOnPreconditions())
                    validationConfig.executeValidations(requestContext);
            }
        } catch (Throwable t) {
            t.getMessage();
        }
    }
}

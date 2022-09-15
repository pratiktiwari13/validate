/* (C)2022 */
package io.validate.context.app;

import io.validate.api.Precondition;
import io.validate.api.Validation;
import io.validate.api.internal.ResourceProvider;
import io.validate.context.request.RequestContext;
import io.validate.context.validation.ValidationContext;
import io.validate.resolvers.DefaultFieldNameResolver;
import io.validate.resolvers.ValueResolver;
import java.util.ArrayList;
import java.util.List;

public class ValidationConfig {
    private final String validationName;
    private final Class root;
    private final List<Class> preconditions;
    private final List<Property> properties;
    private DefaultFieldNameResolver resolver = new DefaultFieldNameResolver();

    public ValidationConfig(
            String validationName,
            Class root,
            List<Class> preconditions,
            List<Property> properties) {
        this.validationName = validationName;
        this.root = root;
        this.preconditions = preconditions;
        this.properties = properties;
    }

    public String getValidationName() {
        return validationName;
    }

    public Class getRoot() {
        return root;
    }

    public List<Class> getPreconditions() {
        return preconditions;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public boolean shouldExecuteBasedOnPreconditions() {
        List<Class> rawPreconditions = this.getPreconditions();
        Class root = this.getRoot();
        return !rawPreconditions.stream()
                .map(
                        rawPrecondition ->
                                (Precondition)
                                        Context.getInstance()
                                                .getCurrentPreconditionProvider()
                                                .provide(rawPrecondition.getName()))
                .filter(precondition -> precondition.preEvaluate(root) == false)
                .findFirst()
                .isPresent();
    }

    public boolean executeValidations(RequestContext requestContext) throws Throwable {
        // resolve arguments in later revision
        Object rootValue = requestContext.getRoot(root);
        if (rootValue == null)
            throw new NullPointerException("No root found or the root value is null");
        for (Property property : properties) {
            ResourceProvider<Validation> resourceProvider =
                    Context.getInstance().getCurrentValidationProvider();
            Validation validation = resourceProvider.provide(property.getValidation().getName());
            Object value = ValueResolver.resolve(rootValue, property.getPropertyPath(), resolver);
            ValidationContext validationContext = new ValidationContext(new ArrayList<>(), value);
            if (!validation.preValidate(value, validationContext)) return false;
        }
        return true;
    }
}

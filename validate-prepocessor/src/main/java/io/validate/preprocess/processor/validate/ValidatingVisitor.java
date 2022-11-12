package io.validate.preprocess.processor.validate;

import io.validate.preprocess.processor.WalkContext;

public interface ValidatingVisitor {
    boolean shouldValidate(WalkContext context);
    boolean validate(ValidateContext context);
}

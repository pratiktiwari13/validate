package io.validate.preprocess.processor.transform;

public interface TransformingVisitor {
    boolean shouldTransform(String attributeName,Object value);
    Object transform(TransformContext context);
}

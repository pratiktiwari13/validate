package io.validate.preprocess.processor.transform.deferred;

import io.validate.preprocess.attributeproperties.InheritableConfigElements;
import io.validate.preprocess.processor.PostWalkHandler;
import io.validate.preprocess.processor.transform.TransformContext;
import io.validate.preprocess.processor.transform.TransformingVisitor;
import org.apache.commons.lang3.EnumUtils;

import java.util.HashMap;

public class InheritanceResolver implements PostWalkHandler, TransformingVisitor {
    @Override
    public boolean shouldTransform(String attributeName, Object value) {
        return EnumUtils.isValidEnum(InheritableConfigElements.class,attributeName);
    }

    @Override
    public Object transform(TransformContext context) {
        return null;
    }

    @Override
    public void handle(HashMap<String, Object> objectModel) {

    }
}

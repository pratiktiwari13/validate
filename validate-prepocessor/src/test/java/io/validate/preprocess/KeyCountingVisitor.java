/* (C)2023 */
package io.validate.preprocess;

import io.validate.preprocess.processor.transform.TransformContext;
import io.validate.preprocess.processor.transform.TransformingVisitor;

public class KeyCountingVisitor implements TransformingVisitor {
    int actualCount = 0;

    @Override
    public boolean shouldTransform(String attributeName, Object value) {
        return true;
    }

    @Override
    public Object transform(TransformContext context) {
        actualCount++;
        return null;
    }

    public int getActualCount() {
        return actualCount;
    }
}

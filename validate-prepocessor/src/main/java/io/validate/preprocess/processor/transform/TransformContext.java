package io.validate.preprocess.processor.transform;

import io.validate.preprocess.processor.WalkContext;

public class TransformContext {
    private WalkContext walkContext;

    public TransformContext(WalkContext walkContext) {
        this.walkContext = walkContext;
    }

    public WalkContext getWalkContext() {
        return walkContext;
    }

    public void setWalkContext(WalkContext walkContext) {
        this.walkContext = walkContext;
    }
}

package io.validate.preprocess.processor;

public interface TreeWalkProcessor {
    void processWalk(WalkContext walkContext);
    void processPreWalk(WalkContext walkContext);
    void processPostWalk(WalkContext walkContext);
}

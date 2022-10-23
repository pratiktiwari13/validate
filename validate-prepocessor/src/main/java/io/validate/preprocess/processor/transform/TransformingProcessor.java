package io.validate.preprocess.processor.transform;

import io.validate.preprocess.processor.PreWalkHandler;
import io.validate.preprocess.processor.PostWalkHandler;
import io.validate.preprocess.processor.TreeWalkProcessor;
import io.validate.preprocess.processor.WalkContext;

import java.util.ArrayList;

public class TransformingProcessor implements TreeWalkProcessor {

    private ArrayList<TransformingVisitor> transformingVisitors;
    private ArrayList<PreWalkHandler> preWalkHandlers;
    private ArrayList<PostWalkHandler> postWalkHandlers;

    public TransformingProcessor(ArrayList<TransformingVisitor> transformingVisitors, ArrayList<PreWalkHandler> preWalkHandlers, ArrayList<PostWalkHandler> postWalkHandlers) {
        this.transformingVisitors = transformingVisitors;
        this.preWalkHandlers = preWalkHandlers;
        this.postWalkHandlers = postWalkHandlers;
    }

    @Override
    public void processWalk(WalkContext walkContext) {
        TransformContext transformContext = createTransformContext(walkContext);
        for(TransformingVisitor transformingVisitor:transformingVisitors){
            if(transformingVisitor.shouldTransform(walkContext.getCurrentAttribute(),walkContext.getCurrentValue())){
                Object transformedValue = transformingVisitor.transform(transformContext);
                walkContext.getObjectModel().replace(walkContext.getCurrentAttribute(),transformedValue);
            }
        }
    }

    @Override
    public void processPreWalk(WalkContext walkContext) {
        preWalkHandlers.stream().forEach(preWalkHandler -> preWalkHandler.handle(walkContext.getObjectModel()));
    }

    @Override
    public void processPostWalk(WalkContext walkContext) {
        postWalkHandlers.stream().forEach(preWalkHandler -> preWalkHandler.handle(walkContext.getObjectModel()));
    }

    private TransformContext createTransformContext(WalkContext walkContext){
        return new TransformContext(walkContext);
    }
}

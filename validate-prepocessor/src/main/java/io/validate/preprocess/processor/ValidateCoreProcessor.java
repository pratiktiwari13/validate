package io.validate.preprocess.processor;

import io.validate.preprocess.processor.PreWalkHandler;
import io.validate.preprocess.processor.PostWalkHandler;
import io.validate.preprocess.processor.TreeWalkProcessor;
import io.validate.preprocess.processor.WalkContext;
import io.validate.preprocess.processor.transform.TransformContext;
import io.validate.preprocess.processor.transform.TransformingVisitor;
import io.validate.preprocess.processor.validate.ValidateContext;
import io.validate.preprocess.processor.validate.ValidatingVisitor;

import java.util.ArrayList;

public class ValidateCoreProcessor implements TreeWalkProcessor {

    private ArrayList<TransformingVisitor> transformingVisitors;
    private ArrayList<PreWalkHandler> preWalkHandlers;
    private ArrayList<PostWalkHandler> postWalkHandlers;
    private ArrayList<ValidatingVisitor> validators;

    public ValidateCoreProcessor(ArrayList<TransformingVisitor> transformingVisitors, ArrayList<PreWalkHandler> preWalkHandlers, ArrayList<PostWalkHandler> postWalkHandlers, ArrayList<ValidatingVisitor> validatingVisitors) {
        this.transformingVisitors = transformingVisitors;
        this.preWalkHandlers = preWalkHandlers;
        this.postWalkHandlers = postWalkHandlers;
        this.validators = validatingVisitors;
    }

    @Override
    public void processWalk(WalkContext walkContext) {
        TransformContext transformContext = createTransformContext(walkContext);
        if(runValidators(walkContext)) {
            for (TransformingVisitor transformingVisitor : transformingVisitors) {
                if (transformingVisitor.shouldTransform(walkContext.getCurrentAttribute(), walkContext.getCurrentValue())) {
                    Object transformedValue = transformingVisitor.transform(transformContext);
                    walkContext.getObjectModel().replace(walkContext.getCurrentAttribute(), transformedValue);
                }
            }
        }
    }

    private boolean runValidators(WalkContext walkContext) {
        ValidateContext validateContext = createValidationContext(walkContext);
        for(ValidatingVisitor validator:validators){
            if(validator.shouldValidate(walkContext)){
                if(validator.validate(validateContext))
                    continue;
                return false;
            }
        }
        return true;
    }

    private ValidateContext createValidationContext(WalkContext walkContext) {
        return new ValidateContext();
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

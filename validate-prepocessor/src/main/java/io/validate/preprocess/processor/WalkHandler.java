package io.validate.preprocess.processor;

import java.util.HashMap;

public interface WalkHandler {
    void handle(HashMap<String,Object> objectModel);
}

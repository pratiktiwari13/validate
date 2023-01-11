/* (C)2023 */
package io.validate.preprocess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.validate.preprocess.processor.PostWalkHandler;
import io.validate.preprocess.processor.PreWalkHandler;
import io.validate.preprocess.processor.transform.TransformingProcessor;
import io.validate.preprocess.processor.transform.TransformingVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WalkerTest {

    @Test
    public void walkerShouldCoverAllTheNodes() throws IOException {
        JsonNode node = null;
        node =
                new ObjectMapper()
                        .readTree(this.getClass().getResourceAsStream("/valid-test-configs.json"));
        List<String> keys = new ArrayList<>();
        TestUtils.getAllKeysUsingJsonNodeFields(node, keys);
        ArrayList<PreWalkHandler> preWalkHandlers = new ArrayList<>();
        ArrayList<PostWalkHandler> postWalkHandlers = new ArrayList<>();
        int expectedCount = keys.size();
        ArrayList<TransformingVisitor> transformingVisitors = new ArrayList<>();
        KeyCountingVisitor keyCountingVisitor = new KeyCountingVisitor();
        transformingVisitors.add(keyCountingVisitor);
        TransformingProcessor transformingProcessor =
                new TransformingProcessor(transformingVisitors, preWalkHandlers, postWalkHandlers);
        Walker walker = new Walker(transformingProcessor);
        walker.walk(this.getClass().getResourceAsStream("/valid-test-configs.json"));
        assertEquals(expectedCount, keyCountingVisitor.getActualCount());
    }
}

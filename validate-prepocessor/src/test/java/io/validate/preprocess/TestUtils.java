/* (C)2023 */
package io.validate.preprocess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static void getAllKeysUsingJsonNodeFields(JsonNode jsonNode, List<String> keys) {

        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(
                    field -> {
                        keys.add(field.getKey());
                        getAllKeysUsingJsonNodeFields((JsonNode) field.getValue(), keys);
                    });
        } else if (jsonNode.isArray()) {
            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(
                    node -> {
                        getAllKeysUsingJsonNodeFields(node, keys);
                    });
        }
    }
}

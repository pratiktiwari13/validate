package spec;/* (C)2022 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TestUtils {

    public static String getConfigSpecPath() {
        String specPath = new File("").getAbsolutePath().concat("/spec/config-spec.json");
        return specPath;
    }

    public static JsonNode getConfigFromTestConfigs(
            JsonNode node, String fieldName, String fieldValue) throws JsonProcessingException {
        JsonNode configNode =
                node.findParents(fieldName).stream()
                        .filter(parent -> parent.findValue(fieldName).asText().equals(fieldValue))
                        .findFirst()
                        .orElse(null);
        String config = "{\"config\":[" + configNode.toString() + "]}";
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(config);
    }
}

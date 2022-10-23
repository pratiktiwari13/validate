package spec;/* (C)2022 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidConfigsTest {

    JsonSchemaFactory jsonSchemaFactory;
    JsonNode schema;
    JsonNode testData;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() throws Exception {
        jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        if (schema == null) schema = objectMapper.readTree(new File(TestUtils.getConfigSpecPath()));
        if (testData == null)
            testData =
                    objectMapper.readTree(
                            this.getClass().getResourceAsStream("/valid-test-configs.json"));
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> o = objectMapper.readValue(this.getClass().getResourceAsStream("/valid-test-configs.json"), typeRef);
    }

    @Test
    public void shouldBeSuccessfulForAllValidConfigs()
            throws JsonProcessingException {
        Set<ValidationMessage> errors = jsonSchemaFactory.getSchema(schema).validate(testData);
        assertTrue(errors.size() == 0);
    }
}

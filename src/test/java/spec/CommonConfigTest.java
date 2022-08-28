package spec;

import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonConfigTest{

    JsonSchemaFactory jsonSchemaFactory;
    String schema;

    @BeforeEach
    public void setup(){
        jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        if(schema==null) schema =
    }
}

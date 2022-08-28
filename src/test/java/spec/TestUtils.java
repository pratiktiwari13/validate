

import java.io.File;

public class TestUtils {

    public static String getConfigSpecPath(){
        String specPath = new File("").getAbsolutePath().concat("/spec/config-spec.json");
        return specPath;
    }
}

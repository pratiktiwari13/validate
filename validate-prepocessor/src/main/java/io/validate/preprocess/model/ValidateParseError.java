/* (C)2023 */
package io.validate.preprocess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidateParseError {

    private HashMap<String, String> unexpectedToken;
    private HashMap<String, List<String>> expectedToken;

    public void unexpectedToken(String path, String token) {
        unexpectedToken.put(path, token);
    }

    public void missingToken(String path, List<String> allowedValues) {
        expectedToken.put(path, allowedValues);
    }

    public List<String> getMessages() {
        List<String> messages = new ArrayList<>();

        for (String path : unexpectedToken.keySet())
            messages.add("Unexpected token: " + unexpectedToken.get(path) + " at path: " + path);

        for (String path : expectedToken.keySet()) {
            String allowedValues = String.join(",", expectedToken.get(path));
            messages.add("Expected either: " + allowedValues + " at path: " + path);
        }
        return messages;
    }
}

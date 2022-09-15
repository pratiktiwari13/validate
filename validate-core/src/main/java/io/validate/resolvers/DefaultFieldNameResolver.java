/* (C)2022 */
package io.validate.resolvers;

import java.util.Arrays;
import java.util.List;

public class DefaultFieldNameResolver implements Resolver<List<String>, String> {
    @Override
    public List<String> resolve(String input) {
        return Arrays.asList(input.split("."));
    }
}

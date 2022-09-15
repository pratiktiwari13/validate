/* (C)2022 */
package io.validate.context.request;

import java.util.List;

public class RequestContext {
    private final String origin;
    private final List<Argument> arguments;

    public RequestContext(String origin, List<Argument> arguments) {
        this.origin = origin;
        this.arguments = arguments;
    }

    public String getOrigin() {
        return origin;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Object getRoot(Class root) {
        Argument rootArgument =
                arguments.stream()
                        .filter(argument -> argument.getClazz().equals(root))
                        .findFirst()
                        .orElse(null);
        if (rootArgument != null) return rootArgument.getValue(root);
        return null;
    }
}

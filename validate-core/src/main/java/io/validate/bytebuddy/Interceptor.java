/* (C)2022 */
package io.validate.bytebuddy;

import io.validate.ValidationEntryPoint;
import io.validate.context.request.Argument;
import io.validate.context.request.RequestContext;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.asm.Advice;

public class Interceptor {
    @Advice.OnMethodEnter
    public static void intercept(
            @Advice.Origin String origin,
            @Advice.This Object thisObject,
            @Advice.AllArguments Object[] ary)
            throws Exception {
        RequestContext requestContext = new RequestContext(origin, makeArguments(ary));
        ValidationEntryPoint.validate(requestContext, origin);
    }

    private static List<Argument> makeArguments(Object[] ary) {
        List<Argument> arguments = new ArrayList<>(ary.length);
        for (Object arg : ary) {
            Argument argument = new Argument(arg.getClass(), arg);
            arguments.add(argument);
        }
        return arguments;
    }
}

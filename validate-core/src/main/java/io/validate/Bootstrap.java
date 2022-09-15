/* (C)2022 */
package io.validate;

import io.validate.bytebuddy.Interceptor;
import io.validate.context.app.Context;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

public class Bootstrap {
    private static boolean CONTEXT_INITIALIZED = false;

    public static void setup() {
        if (CONTEXT_INITIALIZED) throw new IllegalStateException("Context is already initialized!");
        ByteBuddyAgent.install();
        Context.getInstance().getClassesToBeIntercepted().stream()
                .forEach(
                        clazz -> {
                            new ByteBuddy()
                                    .redefine(clazz)
                                    .visit(
                                            Advice.to(Interceptor.class)
                                                    .on(ElementMatchers.named("greeting")))
                                    .make()
                                    .load(
                                            Bootstrap.class.getClassLoader(),
                                            ClassReloadingStrategy.fromInstalledAgent());
                        });
        CONTEXT_INITIALIZED = true;
    }
}

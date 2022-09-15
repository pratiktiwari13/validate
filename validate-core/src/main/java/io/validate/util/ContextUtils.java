/* (C)2022 */
package io.validate.util;

import io.validate.context.app.Context;

public class ContextUtils {

    public static boolean shouldInterceptClass(Context validationContext, String className)
            throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        return validationContext.getClassesToBeIntercepted().contains(clazz);
    }
}

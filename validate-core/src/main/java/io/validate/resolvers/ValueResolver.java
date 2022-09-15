/* (C)2022 */
package io.validate.resolvers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ValueResolver {
    private static ConcurrentHashMap<String, MethodHandle> unreflectedMethodHandles =
            new ConcurrentHashMap<>();
    private static MethodHandles.Lookup factory = MethodHandles.lookup();
    private static Object currentSubfieldValue;

    public static Object resolve(Object root, String input, DefaultFieldNameResolver resolverToUse)
            throws Throwable {
        List<String> resolvedFieldNames = resolverToUse.resolve(input);
        currentSubfieldValue = root;
        for (String fieldName : resolvedFieldNames) {
            MethodHandle methodHandle =
                    unreflectedMethodHandles.computeIfAbsent(
                            generateKey(currentSubfieldValue.getClass(), fieldName),
                            (key) -> {
                                try {
                                    return getMethodHandle(
                                            currentSubfieldValue.getClass(), fieldName);
                                } catch (NoSuchFieldException e) {
                                    throw new RuntimeException(e);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            });
            currentSubfieldValue = methodHandle.invoke(currentSubfieldValue);
        }
        return currentSubfieldValue;
    }

    private static MethodHandle getMethodHandle(Class root, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = root.getDeclaredField(fieldName);
        return factory.unreflectGetter(field);
    }

    private static String generateKey(Class root, String fieldName) {
        return root.getName() + "." + fieldName;
    }
}

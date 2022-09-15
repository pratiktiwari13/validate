/* (C)2022 */
package io.validate.api.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractDefaultResourceProvider<R> implements ResourceProvider<R> {
    private Map<String, R> cache = new ConcurrentHashMap<>();

    public abstract Class provideResourceClass(String resourceName);

    // name represents he classname for now
    @Override
    public R provide(String name) {
        return cache.computeIfAbsent(
                name,
                (validationObj) -> {
                    try {
                        Class resourceClass = provideResourceClass(name);
                        R resource = (R) resourceClass.getDeclaredConstructor().newInstance();
                        return resource;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}

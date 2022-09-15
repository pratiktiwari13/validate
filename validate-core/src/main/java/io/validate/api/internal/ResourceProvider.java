/* (C)2022 */
package io.validate.api.internal;

public interface ResourceProvider<R> {

    R provide(String name);
}

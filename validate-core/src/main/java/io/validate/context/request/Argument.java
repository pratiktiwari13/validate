/* (C)2022 */
package io.validate.context.request;

public class Argument {
    private final Class type;
    private final Object value;

    public Argument(Class type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class getClazz() {
        return this.type;
    }

    public <T> T getValue(Class<T> clazz) {
        return clazz.cast(value);
    }
}

/* (C)2022 */
package io.validate.api;

public interface Precondition<R> {

    default boolean preEvaluate(Object root) {
        return evaluate((R) root);
    }

    boolean evaluate(R root);
}

/* (C)2022 */
package io.validate.resolvers;

public interface Resolver<R, I> {
    R resolve(I input);
}

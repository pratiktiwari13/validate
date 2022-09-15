/* (C)2022 */
package io.validate.api.internal;

import io.validate.api.Precondition;
import io.validate.context.app.Context;

public class DefaultPreconditionProvider extends AbstractDefaultResourceProvider<Precondition> {
    @Override
    public Class provideResourceClass(String name) {
        return Context.getInstance().getPrecondition(name);
    }
}

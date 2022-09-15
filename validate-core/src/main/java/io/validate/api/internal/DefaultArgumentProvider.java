/* (C)2022 */
package io.validate.api.internal;

import io.validate.api.ArgumentProvider;
import io.validate.context.app.Context;

public class DefaultArgumentProvider extends AbstractDefaultResourceProvider<ArgumentProvider> {
    @Override
    public Class provideResourceClass(String resourceName) {
        return Context.getInstance().getArgumentProvider(resourceName);
    }
}

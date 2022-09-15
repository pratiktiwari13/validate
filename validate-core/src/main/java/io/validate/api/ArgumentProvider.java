/* (C)2022 */
package io.validate.api;

import io.validate.context.request.RequestContext;

public interface ArgumentProvider<R> {

    default RequestContext preProvide(Object root, RequestContext context) {
        return provide((R) root, context);
    }

    RequestContext provide(R root, RequestContext context);
}

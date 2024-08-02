package org.acme.filter;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.client.impl.ClientRequestContextImpl;

@ApplicationScoped
@Provider
@Priority(Priorities.AUTHENTICATION+1)
public class RestClientPathInfoFilter implements ClientRequestFilter {

        @Inject
        RestClientPathInfo restClientPathInfo;

        @Override
        public void filter(ClientRequestContext request) {
            restClientPathInfo.setPathInfo(request.getUri().getPath(), pathTemplate(request));
        }

        String pathTemplate(ClientRequestContext request) {
            return String.valueOf(((ClientRequestContextImpl) request).getRestClientRequestContext().getProperties().get("UrlPathTemplate"));
        }
}

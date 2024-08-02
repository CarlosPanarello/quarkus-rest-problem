package org.acme.filter;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import org.jboss.resteasy.reactive.client.impl.ClientRequestContextImpl;
import org.jboss.resteasy.reactive.server.core.ResteasyReactiveRequestContext;
import org.jboss.resteasy.reactive.server.jaxrs.ContainerRequestContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.Serializable;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class LogFilter implements ContainerResponseFilter,
    ContainerRequestFilter, ClientRequestFilter, ClientResponseFilter, Serializable {
    public static final String REQUEST_ID = "requestID";
    static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

    @Inject
    RequestScopeID requestScopeID;

    String pathTemplate(ContainerRequestContext request){
        return ((ResteasyReactiveRequestContext)((ContainerRequestContextImpl) request).getServerRequestContext()).getTarget().getPath().template;
    }

    String pathTemplate(ClientRequestContext request) {
        return String.valueOf(((ClientRequestContextImpl) request).getRestClientRequestContext().getProperties().get("UrlPathTemplate"));
    }

    @Override
    public void filter(ClientRequestContext request) {
        MDC.put(REQUEST_ID, requestScopeID.toString());
        LOG.info("Req Client{path={}, method={}}",
            pathTemplate(request),
            request.getMethod());
    }

    @Override
    public void filter(ClientRequestContext request, ClientResponseContext response) throws IOException {
        LOG.info("Resp Client{path={}, method={}, status={}}",
            pathTemplate(request),
            request.getMethod(),
            response.getStatus());
    }

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        MDC.put(REQUEST_ID, requestScopeID.toString());
        LOG.info("Req {path={}, method={}}",
            pathTemplate(request),
            request.getMethod());
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        LOG.info("Resp {path={}, method={}}",
            pathTemplate(request),
            request.getMethod());
    }

}

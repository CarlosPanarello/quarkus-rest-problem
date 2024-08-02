package org.acme.filter;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.acme.exception.MyRuntimeException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ExceptionMapper implements ResponseExceptionMapper<MyRuntimeException> {

    //TODO use this to get error, comment to work
    @Inject
    UriInfo uriInfo;

    @Inject
    RestClientPathInfo restClientPathInfo;

    @Override
    public MyRuntimeException toThrowable(Response response) {
        //TODO use this to get error, comment to work
        var path = uriInfo.getPath();

        //TODO uncomment this to work
        //var path = restClientPathInfo.of().path();

        return new MyRuntimeException(path, "Mapper Error RestClient Example") ;
    }
}

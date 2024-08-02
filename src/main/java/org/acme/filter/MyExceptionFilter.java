package org.acme.filter;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Provider
@Priority(Priorities.USER)
public class MyExceptionFilter implements ExceptionMapper<MyRuntimeException> {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionFilter.class);

    @Inject
    RequestScopeID requestID;

    @Override
    public Response toResponse(MyRuntimeException e) {
        logger.info("MyExceptionFilter");
        return Response.status(400).entity(
            Map.of("ErrorCode", e.getErrorCode(),
                "PathError", e.getPath(),
                "Message", e.getMessage(),
                "RequestId", requestID.toString()
            )
        ).build();
    }
}

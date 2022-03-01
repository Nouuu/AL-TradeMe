package org.larrieulacoste.noe.al.trademe.kernel.exception;

import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class RuntimeExceptionHandler implements ExceptionMapper<RuntimeException> {
    @Inject
    @LoggerQualifier(RuntimeExceptionHandler.class)
    Logger logger;


    @Override
    public Response toResponse(RuntimeException exception) {
        logger.error(exception.getClass().getSimpleName() + "\n" + exception.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(
                ExceptionResponse.withSourceAndMessage(
                        exception.getClass().getSimpleName(),
                        exception.getMessage()
                )
        ).build();
    }
}

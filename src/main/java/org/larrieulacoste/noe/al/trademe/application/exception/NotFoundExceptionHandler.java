package org.larrieulacoste.noe.al.trademe.application.exception;

import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerQualifier;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Inject
    @LoggerQualifier(NotFoundExceptionHandler.class)
    Logger logger;


    @Override
    public Response toResponse(NotFoundException exception) {
        logger.error(exception.getClass().getSimpleName() + "\n" + exception.getMessage());
        return Response.status(Status.NOT_FOUND).entity(
                ExceptionResponse.withSourceAndMessage(
                        exception.getClass().getSimpleName(),
                        exception.getMessage()
                )
        ).build();
    }
}

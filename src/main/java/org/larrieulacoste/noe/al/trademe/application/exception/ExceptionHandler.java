package org.larrieulacoste.noe.al.trademe.application.exception;

import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<RuntimeException> {
    private final Logger logger;

    public ExceptionHandler() {
        this.logger = LoggerFactory.getLogger(this);
    }

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

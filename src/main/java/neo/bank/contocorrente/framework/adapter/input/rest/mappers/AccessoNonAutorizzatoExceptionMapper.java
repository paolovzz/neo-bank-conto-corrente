package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.domain.exceptions.AccessoNonAutorizzatoException;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ErrorResponse;

@Provider
@Slf4j
public class AccessoNonAutorizzatoExceptionMapper implements ExceptionMapper<AccessoNonAutorizzatoException> {

    @Override
    public Response toResponse(AccessoNonAutorizzatoException exception) {
        log.error("", exception);
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(403)
                       .entity(errorResponse)
                       .build();
    }
}

package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.contocorrente.domain.exceptions.AccessoNonAutorizzatoException;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ErrorResponse;

@Provider
public class AccessoNonAutorizzatoExceptionMapper implements ExceptionMapper<AccessoNonAutorizzatoException> {

    @Override
    public Response toResponse(AccessoNonAutorizzatoException exception) {
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(403)
                       .entity(errorResponse)
                       .build();
    }
}

package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.contocorrente.domain.exceptions.ValidazioneException;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ErrorResponse;

@Provider
public class ValidazioneExceptionMapper implements ExceptionMapper<ValidazioneException> {

    @Override
    public Response toResponse(ValidazioneException exception) {
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(400)
                       .entity(errorResponse)
                       .build();
    }
}

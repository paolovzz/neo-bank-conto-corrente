package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.contocorrente.application.exceptions.ContoCorrenteNonTrovatoException;
import neo.bank.contocorrente.framework.adapter.input.rest.response.ErrorResponse;

@Provider
public class ContoCorrenteNonTrovatoExceptionMapper implements ExceptionMapper<ContoCorrenteNonTrovatoException> {

    @Override
    public Response toResponse(ContoCorrenteNonTrovatoException exception) {
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(404)
                       .entity(errorResponse)
                       .build();
    }
}

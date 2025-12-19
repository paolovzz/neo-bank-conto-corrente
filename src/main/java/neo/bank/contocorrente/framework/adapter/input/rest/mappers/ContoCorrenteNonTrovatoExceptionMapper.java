package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.exceptions.ContoCorrenteNonTrovatoException;
import neo.bank.contocorrente.framework.adapter.input.rest.model.ErrorResponse;

@Provider
@Slf4j
public class ContoCorrenteNonTrovatoExceptionMapper implements ExceptionMapper<ContoCorrenteNonTrovatoException> {

    @Override
    public Response toResponse(ContoCorrenteNonTrovatoException exception) {
        log.error("", exception);
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(404)
                       .entity(errorResponse)
                       .build();
    }
}

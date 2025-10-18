package neo.bank.contocorrente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.contocorrente.domain.exceptions.BusinessRuleException;
import neo.bank.contocorrente.framework.adapter.input.rest.response.ErrorResponse;

@Provider
public class BusinessRuleExceptionMapper implements ExceptionMapper<BusinessRuleException> {

    @Override
    public Response toResponse(BusinessRuleException exception) {
        ErrorResponse errorResponse = new ErrorResponse( exception.getMessage());
        return Response.status(422)
                       .entity(errorResponse)
                       .build();
    }
}

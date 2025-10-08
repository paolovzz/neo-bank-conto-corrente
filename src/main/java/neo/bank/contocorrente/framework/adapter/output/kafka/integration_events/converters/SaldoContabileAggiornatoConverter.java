package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.SaldoContabileAggiornato;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IESaldoContabileAggiornato;

@ApplicationScoped
public class SaldoContabileAggiornatoConverter implements IntegrationEventConverter<SaldoContabileAggiornato, IESaldoContabileAggiornato>, IntegrationEventConverterMarker{

    @Override
    public IESaldoContabileAggiornato convert(SaldoContabileAggiornato ev) {

        return new IESaldoContabileAggiornato(ev.importo());
    }

    @Override
    public Class<SaldoContabileAggiornato> supportedDomainEvent() {
        return SaldoContabileAggiornato.class;
    }
    
}

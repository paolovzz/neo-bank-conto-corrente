package neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.events.SaldoDisponibileAggiornato;
import neo.bank.contocorrente.framework.adapter.output.kafka.integration_events.IESaldoDisponibileAggiornato;

@ApplicationScoped
public class SaldoDisponibileAggiornatoConverter implements IntegrationEventConverter<SaldoDisponibileAggiornato, IESaldoDisponibileAggiornato>, IntegrationEventConverterMarker{

    @Override
    public IESaldoDisponibileAggiornato convert(SaldoDisponibileAggiornato ev) {

        return new IESaldoDisponibileAggiornato(ev.getImporto());
    }

    @Override
    public Class<SaldoDisponibileAggiornato> supportedDomainEvent() {
        return SaldoDisponibileAggiornato.class;
    }
    
}

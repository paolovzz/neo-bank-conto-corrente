package neo.bank.contocorrente.framework.adapter.input.kafka;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ContoCorrenteUseCase;
import neo.bank.contocorrente.application.ports.input.commands.AggiornaSaldoCmd;
import neo.bank.contocorrente.application.ports.input.commands.RipristinaSaldoCmd;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;

@ApplicationScoped
@Slf4j
public class OperazioneConsumer {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private ContoCorrenteUseCase app;

    private static final String EVENT_OWNER = "OPERAZIONE";
    private static final String OPERAZIONE_CONCLUSA_EVENT_NAME = "OperazioneConclusa";
    private static final String OPERAZIONE_ANNULLATA_EVENT_NAME = "OperazioneAnnullata";

    @Incoming("operazione-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            switch (eventType) {
                case OPERAZIONE_CONCLUSA_EVENT_NAME:{
                    String idOperazione =  (String) metadata.getKey();
                    app.aggiornaSaldo(new AggiornaSaldoCmd(new IdOperazione(idOperazione)));
                    break;
                }
                case OPERAZIONE_ANNULLATA_EVENT_NAME:{
                    String idOperazione =  (String) metadata.getKey();
                    app.ripristinaSaldo(new RipristinaSaldoCmd(new IdOperazione(idOperazione)));
                    break;
                }
                default:
                    log.warn("Evento [{}] non gestito...", eventType);
                    break;
            }
        }
        return msg.ack();
    }

    private JsonNode convertToJsonNode(String payload) {
        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Errore durante la conversione json del messaggio kafka", e);
        }
    }
}

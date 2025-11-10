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
public class BonificoConsumer {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private ContoCorrenteUseCase app;

    private static final String EVENT_OWNER = "BONIFICO";
    private static final String BONIFICO_CONVALIDATO_EVENT_NAME = "BonificoConvalidato";
    private static final String CONTROLLI_NON_SUPERATI_EVENT_NAME = "BonificoAnnullato";

    @Incoming("bonifico-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            IdOperazione idOperazione = new IdOperazione((String) metadata.getKey());
            switch (eventType) {
                case BONIFICO_CONVALIDATO_EVENT_NAME:{
                    app.applicaBonifico(new AggiornaSaldoCmd(idOperazione));
                    break;
                }
                case CONTROLLI_NON_SUPERATI_EVENT_NAME:{
                    app.ripristinaSaldo(new RipristinaSaldoCmd(idOperazione));
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

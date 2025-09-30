package neo.bank.contocorrente.framework.adapter.output.kafka;

import java.util.UUID;

import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.output.ErrorEventsPublisherPort;
import neo.bank.contocorrente.domain.models.events.EventPayload;

@ApplicationScoped
@Slf4j
public class ErrorEventsPublisher implements ErrorEventsPublisherPort {

    @Inject
    private ObjectMapper mapper;

    @Channel("conto-corrente-error-notifications")
    Emitter<String> emitter;


    private String toJsonString(EventPayload event) {
        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publish(EventPayload event, String aggregateName, String aggregateId) {
        Message<String> message = Message.of(toJsonString(event))
            .addMetadata(OutgoingKafkaRecordMetadata.<String>builder()
                .withKey(aggregateId)
                .withHeaders(new RecordHeaders()
                    .add("eventType", event.eventType().getBytes())
                    .add("aggregateName", aggregateName.getBytes())
                    .add("eventId", UUID.randomUUID().toString().getBytes()))
                .build());
            log.info("Evento inviato: {}", message);
            emitter.send(message);
    }
}
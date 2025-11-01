package neo.bank.contocorrente.framework.adapter.output.mongodb.entities;

import java.time.LocalDateTime;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;

@MongoEntity(collection="transazioni-projection")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TransazioneProjectionEntity extends PanacheMongoEntityBase {

    @BsonId
    private String idTransazione;
    private String idOperazione;
    private double importo;
    private String idContoCorrente;
    private String iban;
    private LocalDateTime dataCreazione;
    private String causale;
    private TipologiaFlusso tipologiaFlusso;

    public TransazioneProjectionEntity(String idTransazione, String idContoCorrente, String idOperazione, double importo, String iban, LocalDateTime dataCreazione,
            String causale, TipologiaFlusso tipologiaFlusso) {
        this.idTransazione = idTransazione;
        this.idContoCorrente = idContoCorrente;
        this.idOperazione = idOperazione;
        this.importo = importo;
        this.iban = iban;
        this.dataCreazione = dataCreazione;
        this.causale = causale;
        this.tipologiaFlusso = tipologiaFlusso;
    }


    
}
package neo.bank.contocorrente.framework.adapter.output.mongodb.entities;

import java.time.Instant;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MongoEntity(collection="iban-projection")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class IbanProjectionEntity extends PanacheMongoEntityBase {

    @BsonId
    private String iban;
    private String idContoCorrente;

    public IbanProjectionEntity(String iban, String idContoCorrente) {
        this.iban = iban;
        this.idContoCorrente = idContoCorrente;
    }
    
}
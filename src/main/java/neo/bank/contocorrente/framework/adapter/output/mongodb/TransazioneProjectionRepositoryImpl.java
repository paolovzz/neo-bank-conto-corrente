package neo.bank.contocorrente.framework.adapter.output.mongodb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import neo.bank.contocorrente.application.ports.output.TransazioneProjectionRepositoryPort;
import neo.bank.contocorrente.domain.models.events.TipologiaFlusso;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.IdContoCorrente;
import neo.bank.contocorrente.domain.models.vo.IdOperazione;
import neo.bank.contocorrente.domain.models.vo.IdTransazione;
import neo.bank.contocorrente.domain.models.vo.RispostaPaginata;
import neo.bank.contocorrente.domain.models.vo.Transazione;
import neo.bank.contocorrente.framework.adapter.output.mongodb.entities.TransazioneProjectionEntity;

@ApplicationScoped
@Slf4j
public class TransazioneProjectionRepositoryImpl implements PanacheMongoRepositoryBase<TransazioneProjectionEntity, String>, TransazioneProjectionRepositoryPort {@Override
    
    public void salva(Transazione transazione) {
                log.info("Registro la transazione creata nella projection");

        String idTransazione = transazione.getIdTransazione().getId();
        String idOperazione = transazione.getIdOperazione().getId();
        double importo = transazione.getImporto();
        String causale = transazione.getCausale();
        LocalDateTime dataCreazione = transazione.getDataCreazione();
        String idContoCorrente = transazione.getIdContoCorrente().getId();
        String iban = transazione.getIban().getCodice();
        TransazioneProjectionEntity entity = new TransazioneProjectionEntity(idTransazione, idContoCorrente, idOperazione, importo, iban, dataCreazione, causale, transazione.getTipologiaFlusso());
        persist(entity);
    }

    @Override
    public Transazione recuperaDaIdOperazione(IdOperazione idOperazione) {
        return toVO(find("idOperazione", idOperazione.getId()).singleResult());
    }

    @Override
    public void cancella(Transazione transazione) {
        delete(toEntity(transazione));
    }

    @Override
    public void cancellaDaIdOperazione(IdOperazione idOperazione) {
        delete("idOperazione", idOperazione.getId());
    }

    @Override
    public double calcolaTotaleBonificiUscita(Iban iban, LocalDateTime dataInf, LocalDateTime dataSup) {

        List<Bson> pipeline = List.of(
            Aggregates.match(Filters.and(
                Filters.eq("iban", iban.getCodice()),
                Filters.eq("tipologiaFlusso", TipologiaFlusso.ADDEBITO),
                Filters.gte("dataCreazione", dataInf),
                Filters.lte("dataCreazione", dataSup)
            )),
            Aggregates.group(null, Accumulators.sum("totale", "$importo"))
        );

        Document result = mongoCollection().aggregate(pipeline, Document.class).first();

        if (result != null && result.containsKey("totale")) {
            Object totale = result.get("totale");
            if (totale instanceof Number) {
                return ((Number) totale).doubleValue(); 
            }
        }
        return 0.0;
    }


    @Override
    public RispostaPaginata<Transazione> recuperaTransazioni(IdContoCorrente idCC, LocalDateTime dataInf, LocalDateTime dataSup,
            Double importoMin, Double importoMax, TipologiaFlusso tipologiaFlusso, int numeroPagina,
            int dimensionePagina) {

        List<Bson> filtri = new ArrayList<>();
        filtri.add(eq("idContoCorrente", idCC.getId()));

        if (dataInf != null) filtri.add(gte("dataCreazione", dataInf));
        if (dataSup != null) filtri.add(lte("dataCreazione", dataSup));
        if (importoMin != null) filtri.add(gte("importo", importoMin));
        if (importoMax != null) filtri.add(lte("importo", importoMax));
        if (tipologiaFlusso != null) filtri.add(eq("tipologiaFlusso", tipologiaFlusso.name()));

        Bson filtroFinale = and(filtri);
        
        long totRisultati = mongoCollection().countDocuments(filtroFinale);
        List<TransazioneProjectionEntity> transazioni = mongoCollection()
            .find(filtroFinale)
            .sort(Sorts.descending("dataCreazione"))
            .skip(numeroPagina * dimensionePagina)
            .limit(dimensionePagina)
            .into(new ArrayList<>());
        
        return new RispostaPaginata<>(transazioni.stream()
            .map(this::toVO)
            .toList(), numeroPagina, dimensionePagina, totRisultati);
    }
    

    private Transazione toVO(TransazioneProjectionEntity entity) {
        if(entity == null)
            return null;
        else {
            return new Transazione(new IdTransazione(entity.getIdTransazione()),new IdContoCorrente(entity.getIdContoCorrente()), new IdOperazione(entity.getIdOperazione()), entity.getImporto(), new Iban(entity.getIban()), entity.getDataCreazione(), entity.getCausale(), entity.getTipologiaFlusso());
        }
    }

    private TransazioneProjectionEntity toEntity(Transazione vo) {
        if(vo == null)
            return null;
        else {
            return new TransazioneProjectionEntity(vo.getIdTransazione().getId(),vo.getIdContoCorrente().getId(), vo.getIdOperazione().getId(), vo.getImporto(), vo.getIban().getCodice(), vo.getDataCreazione(), vo.getCausale(), vo.getTipologiaFlusso());
        }
    }
}

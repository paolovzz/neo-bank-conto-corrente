package neo.bank.contocorrente.framework.adapter.output.services;

import java.security.SecureRandom;

import org.iban4j.CountryCode;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.contocorrente.domain.models.vo.Abi;
import neo.bank.contocorrente.domain.models.vo.Bic;
import neo.bank.contocorrente.domain.models.vo.Cab;
import neo.bank.contocorrente.domain.models.vo.CoordinateBancarie;
import neo.bank.contocorrente.domain.models.vo.Iban;
import neo.bank.contocorrente.domain.models.vo.NumeroConto;
import neo.bank.contocorrente.domain.services.GeneratoreCoordinateBancarieService;

@ApplicationScoped
public class GeneratoreCoordinateBancarieAdapter implements GeneratoreCoordinateBancarieService {

    @Override
    public CoordinateBancarie genera() {
        org.iban4j.Iban.Builder builder = new org.iban4j.Iban.Builder();
         org.iban4j.Iban iban = builder.countryCode(CountryCode.IT)      // Paese: Italia
                .bankCode("03069")                // ABI (Banca)
                .branchCode("09606")              // CAB (Filiale)
                .accountNumber(generateIdentificativoConto())
                .nationalCheckDigit("S")
                .build();    // Numero conto
        Bic bic = new Bic("BNKSIM91");
        return new CoordinateBancarie(new NumeroConto(iban.getAccountNumber()), new Iban(iban.toString()), bic, new Cab(iban.getBranchCode()), new Abi(iban.getBankCode()));
    }
    private String generateIdentificativoConto() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10); // genera 0-9
            sb.append(digit);
        }
        return sb.toString();
    }
}

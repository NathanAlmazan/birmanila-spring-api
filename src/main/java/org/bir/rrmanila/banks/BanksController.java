package org.bir.rrmanila.banks;

import org.bir.rrmanila.banks.entities.AccreditedBank;
import org.bir.rrmanila.banks.entities.BankDetails;
import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class BanksController {
    @Autowired private BankServices bankServices;

    @QueryMapping
    public Flux<AccreditedBank> findBanksByDistrict(@Argument Integer district) {
        return bankServices.findBanksByDistrict(district);
    }

    @QueryMapping
    public Flux<AccreditedBank> findBanksByAddress(@Argument String address) {
        return bankServices.findBanksByAddress(address);
    }

    @SchemaMapping(typeName = "AccreditedBank", field = "details")
    public Mono<BankDetails> accreditedBankDetails(AccreditedBank bank) {
        return bankServices.findDetailsByBank(bank.getName());
    }

    @SchemaMapping(typeName = "AccreditedBank", field = "district")
    public Mono<RevenueDistrict> bankDistrict(AccreditedBank bank) {
        return bankServices.findBankDistrict(bank.getRevenueDistrict());
    }

    @SchemaMapping(typeName = "AccreditedBank", field = "fullAddress")
    public String bankFullAddress(AccreditedBank bank) {
        return bank.getFullAddress() + ", " + bank.getCity();
    }
}

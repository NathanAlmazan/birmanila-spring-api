package org.bir.rrmanila.banks;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.apache.commons.lang3.StringUtils;
import org.bir.rrmanila.banks.entities.AccreditedBank;
import org.bir.rrmanila.banks.entities.BankDetails;
import org.bir.rrmanila.banks.repositories.BankRepository;
import org.bir.rrmanila.banks.repositories.DetailsRepository;
import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.bir.rrmanila.offices.repositories.DistrictRepository;
import org.bir.rrmanila.statistics.CosineDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class BankServices implements CosineDistance {
    @Autowired private BankRepository bankRepository;
    @Autowired private DetailsRepository detailsRepository;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private DatabaseClient client;

    private static final BiFunction<Row, RowMetadata, AccreditedBank> MAPPING_FUNCTION = (row, rowMetaData) -> new AccreditedBank(
            row.get("bank_no", Long.class),
            row.get("rdo_no", Integer.class),
            row.get("bank_code", String.class),
            row.get("bank_abbr", String.class),
            row.get("bank_branch", String.class),
            row.get("bldg_no", String.class),
            row.get("street", String.class),
            row.get("district", String.class),
            row.get("city", String.class)
    );

    public Flux<AccreditedBank> findBanksByDistrict(Integer district) {
        return bankRepository.findByRevenueDistrict(district);
    }

    public Mono<BankDetails> findDetailsByBank(String shortName) {
        return detailsRepository.findById(shortName);
    }

    public Mono<RevenueDistrict> findBankDistrict(Integer district) {
        return districtRepository.findById(district);
    }

    public Flux<AccreditedBank> findBanksByAddress(String address) {
        // split query into array of words
        String[] words = address.split(" ");

        // convert each word to ILIKE query
        List<String> queries = new ArrayList<>();
        for (String word : words) {
            queries.add("(bldg_no || ' ' || street || ' ' || district) ILIKE '%" + word + "%'");
        }
        String dbQuery = StringUtils.join(queries, " OR ");

        Flux<AccreditedBank> results = client
                .sql("SELECT * FROM accredited_banks WHERE " + dbQuery)
                .map(MAPPING_FUNCTION)
                .all();

        return results.sort((o1, o2) -> Double.compare(
                computeCosineDistance(address, o2.getFullAddress()),
                computeCosineDistance(address, o1.getFullAddress())
        ));
    }
}

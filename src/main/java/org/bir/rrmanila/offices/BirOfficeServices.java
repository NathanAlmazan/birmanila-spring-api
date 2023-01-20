package org.bir.rrmanila.offices;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.apache.commons.lang3.StringUtils;
import org.bir.rrmanila.banks.entities.AccreditedBank;
import org.bir.rrmanila.offices.entities.BirOffice;
import org.bir.rrmanila.offices.entities.ContactInfo;
import org.bir.rrmanila.offices.entities.ContactPerson;
import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.bir.rrmanila.offices.repositories.BirOfficeRepository;
import org.bir.rrmanila.offices.repositories.ContactInfoRepository;
import org.bir.rrmanila.offices.repositories.ContactPersonRepository;
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
public class BirOfficeServices implements CosineDistance {
    @Autowired private BirOfficeRepository birOfficeRepository;
    @Autowired private ContactPersonRepository contactPersonRepository;
    @Autowired private ContactInfoRepository contactInfoRepository;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private DatabaseClient client;

    // Bir Districts
    public Flux<RevenueDistrict> findAllDistricts() {
        return districtRepository.findAll();
    }

    public Mono<RevenueDistrict> findDistrictByNum(Integer num) {
        if (num == null) return null;
        return districtRepository.findById(num);
    }

    // Bir Offices
    public Flux<BirOffice> findAllOffices() {
        return birOfficeRepository.findAll();
    }

    public Flux<BirOffice> findOfficesByDistrict(Integer district) {
        return birOfficeRepository.findByDistrict(district);
    }

    // Office contact persons
    public Flux<ContactPerson> findContactPersonByOffice(Integer officeId) {
        return contactPersonRepository.findByOffice(officeId);
    }

    // Office contact numbers by person
    public Flux<ContactInfo> contactNumbersByPerson(Integer personId) {
        return contactInfoRepository.findByPerson(personId);
    }

    private static final BiFunction<Row, RowMetadata, BirOffice> MAPPING_FUNCTION = (row, rowMetaData) -> new BirOffice(
            row.get("office_id", Integer.class),
            row.get("office_name", String.class),
            row.get("office_address", String.class),
            row.get("office_email", String.class),
            row.get("rdo_no", Integer.class)
    );

    public Flux<BirOffice> findBanksByNameAndAddress(String query) {
        // split query into array of words
        String[] words = query.split(" ");

        // convert each word to ILIKE query
        List<String> queries = new ArrayList<>();
        for (String word : words) {
            queries.add("office_name ILIKE '%" + word + "%' OR office_address ILIKE '%" + word + "%'");
        }
        String dbQuery = StringUtils.join(queries, " OR ");

        Flux<BirOffice> results = client
                .sql("SELECT * FROM offices WHERE " + dbQuery)
                .map(MAPPING_FUNCTION)
                .all();

        return results.sort((o1, o2) -> Double.compare(
                computeCosineDistance(query, o2.getSearchQuery()),
                computeCosineDistance(query, o1.getSearchQuery())
        ));
    }
}

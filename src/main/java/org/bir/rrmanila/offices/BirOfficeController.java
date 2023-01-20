package org.bir.rrmanila.offices;

import org.bir.rrmanila.offices.entities.BirOffice;
import org.bir.rrmanila.offices.entities.ContactInfo;
import org.bir.rrmanila.offices.entities.ContactPerson;
import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class BirOfficeController {
    @Autowired private BirOfficeServices birOfficeServices;

    @QueryMapping
    public Flux<RevenueDistrict> findAllDistricts() {
        return birOfficeServices.findAllDistricts();
    }

    @QueryMapping
    public Flux<BirOffice> findAllOffices() {
        return birOfficeServices.findAllOffices();
    }

    @QueryMapping
    public Flux<BirOffice> findOfficesByDistrict(@Argument Integer district) {
        return birOfficeServices.findOfficesByDistrict(district);
    }

    @QueryMapping
    public Flux<BirOffice> searchBirOffices(@Argument String office) {
        return birOfficeServices.findBanksByNameAndAddress(office);
    }

    @SchemaMapping(typeName = "RevenueDistrict", field = "offices")
    public Flux<BirOffice> officesByDistrict(RevenueDistrict district) {
        return birOfficeServices.findOfficesByDistrict(district.getNumber());
    }

    @SchemaMapping(typeName = "BirOffice", field = "district")
    public Mono<RevenueDistrict> officesDistrict(BirOffice birOffice) {
        return birOfficeServices.findDistrictByNum(birOffice.getDistrict());
    }

    @SchemaMapping(typeName = "BirOffice", field = "officers")
    public Flux<ContactPerson> contactsByOffice(BirOffice office) {
        return birOfficeServices.findContactPersonByOffice(office.getId());
    }

    @SchemaMapping(typeName = "ContactPerson", field = "contacts")
    public Flux<ContactInfo> contactsByPerson(ContactPerson contactPerson) {
        return birOfficeServices.contactNumbersByPerson(contactPerson.getId());
    }
}

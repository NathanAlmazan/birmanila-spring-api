package org.bir.rrmanila.offices;

import org.bir.rrmanila.offices.entities.BirOffice;
import org.bir.rrmanila.offices.entities.ContactInfo;
import org.bir.rrmanila.offices.entities.ContactPerson;
import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.bir.rrmanila.offices.repositories.BirOfficeRepository;
import org.bir.rrmanila.offices.repositories.ContactInfoRepository;
import org.bir.rrmanila.offices.repositories.ContactPersonRepository;
import org.bir.rrmanila.offices.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BirOfficeServices {
    @Autowired private BirOfficeRepository birOfficeRepository;
    @Autowired private ContactPersonRepository contactPersonRepository;
    @Autowired private ContactInfoRepository contactInfoRepository;
    @Autowired private DistrictRepository districtRepository;

    // Bir Districts
    public Flux<RevenueDistrict> findAllDistricts() {
        return districtRepository.findAll();
    }

    public Mono<RevenueDistrict> findDistrictByNum(Integer num) {
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
}

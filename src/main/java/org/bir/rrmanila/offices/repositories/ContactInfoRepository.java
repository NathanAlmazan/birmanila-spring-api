package org.bir.rrmanila.offices.repositories;

import org.bir.rrmanila.offices.entities.ContactInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ContactInfoRepository extends ReactiveCrudRepository<ContactInfo, Integer> {
    Flux<ContactInfo> findByPerson(Integer person);
}

package org.bir.rrmanila.offices.repositories;

import org.bir.rrmanila.offices.entities.ContactPerson;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ContactPersonRepository extends ReactiveCrudRepository<ContactPerson, Integer> {
    Flux<ContactPerson> findByOffice(Integer office);
}

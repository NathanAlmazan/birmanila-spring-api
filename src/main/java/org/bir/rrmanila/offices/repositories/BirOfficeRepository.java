package org.bir.rrmanila.offices.repositories;

import org.bir.rrmanila.offices.entities.BirOffice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BirOfficeRepository extends ReactiveCrudRepository<BirOffice, Integer> {
    Flux<BirOffice> findByDistrict(Integer district);
}

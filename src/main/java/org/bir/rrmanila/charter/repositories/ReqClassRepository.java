package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.RequirementClass;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ReqClassRepository extends ReactiveCrudRepository<RequirementClass, Long> {
    Flux<RequirementClass> findByCharterId(UUID charterId);
}

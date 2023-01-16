package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Requirements;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RequirementRepository extends ReactiveCrudRepository<Requirements, Long> {
    Flux<Requirements> findByClassId(Long classId);
}

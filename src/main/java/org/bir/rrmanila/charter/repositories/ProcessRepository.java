package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Process;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ProcessRepository extends ReactiveCrudRepository<Process, Long> {
    Flux<Process> findByCharterId(UUID charterId);
}

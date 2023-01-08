package org.bir.rrmanila.zones.repositories;

import org.bir.rrmanila.zones.entities.ZonalValue;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ZonalValueRepository extends ReactiveCrudRepository<ZonalValue, Integer> {
    Flux<ZonalValue> findByZoneNumber(Integer zoneNumber);

    Flux<ZonalValue> findByClassification(String classification);
}

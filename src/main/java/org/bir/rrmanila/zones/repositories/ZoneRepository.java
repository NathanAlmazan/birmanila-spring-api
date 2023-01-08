package org.bir.rrmanila.zones.repositories;

import org.bir.rrmanila.zones.entities.Zone;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ZoneRepository extends ReactiveCrudRepository<Zone, Integer> {
    Flux<Zone> findByRevenueDistrict(Integer revenueDistrict);
}

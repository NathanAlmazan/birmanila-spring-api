package org.bir.rrmanila.zones.repositories;

import org.bir.rrmanila.zones.entities.ZonalClassification;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonalClassRepository extends ReactiveCrudRepository<ZonalClassification, String> {
}

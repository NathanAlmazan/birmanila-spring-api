package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Charter;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface CharterRepository extends ReactiveCrudRepository<Charter, UUID> {
    Flux<Charter> findByCategoryId(Integer categoryId);
}

package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Charter;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface CharterRepository extends ReactiveCrudRepository<Charter, UUID> {

    @Query("SELECT * FROM citizen_charter " +
            "ORDER BY citizen_charter.charter_chapter ASC " +
            "LIMIT ?1 OFFSET ?2")
    Flux<Charter> findAllCharter(Integer limit, Integer offset);
}

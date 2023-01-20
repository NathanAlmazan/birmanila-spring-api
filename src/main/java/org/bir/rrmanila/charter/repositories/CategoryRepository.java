package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Categories;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Categories, Long> {

    @Override
    @NonNull
    @Query("SELECT * FROM charter_categories ORDER BY category_id")
    Flux<Categories> findAll();
}

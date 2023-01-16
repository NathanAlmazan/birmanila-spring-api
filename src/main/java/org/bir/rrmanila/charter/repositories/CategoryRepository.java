package org.bir.rrmanila.charter.repositories;

import org.bir.rrmanila.charter.entities.Categories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Categories, Long> {
}

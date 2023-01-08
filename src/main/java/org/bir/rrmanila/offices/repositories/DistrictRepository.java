package org.bir.rrmanila.offices.repositories;

import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<RevenueDistrict, Integer> {
}

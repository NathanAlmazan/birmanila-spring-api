package org.bir.rrmanila.banks.repositories;

import org.bir.rrmanila.banks.entities.AccreditedBank;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankRepository extends ReactiveCrudRepository<AccreditedBank, Long> {
    Flux<AccreditedBank> findByRevenueDistrict(Integer revenueDistrict);
}

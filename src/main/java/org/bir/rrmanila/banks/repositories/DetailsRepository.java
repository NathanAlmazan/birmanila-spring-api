package org.bir.rrmanila.banks.repositories;

import org.bir.rrmanila.banks.entities.BankDetails;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends ReactiveCrudRepository<BankDetails, String> {
}

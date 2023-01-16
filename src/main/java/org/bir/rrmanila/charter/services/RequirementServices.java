package org.bir.rrmanila.charter.services;

import org.bir.rrmanila.charter.entities.RequirementClass;
import org.bir.rrmanila.charter.entities.Requirements;
import org.bir.rrmanila.charter.repositories.ReqClassRepository;
import org.bir.rrmanila.charter.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RequirementServices {
    @Autowired private ReqClassRepository reqClassRepository;
    @Autowired private RequirementRepository requirementRepository;

    public Mono<RequirementClass> createRequirementClass(RequirementClass requirementClass) {
        return reqClassRepository.save(requirementClass).doOnNext(req -> {
            for (Requirements requirement : req.getRequirements()) {
                requirement.setClassId(req.getId());
                createRequirements(requirement).subscribe();
            }
        });
    }

    public Flux<RequirementClass> findReqClassByCharter(UUID charterId) {
        return reqClassRepository.findByCharterId(charterId);
    }

    public Mono<Requirements> createRequirements(Requirements requirements) {
        return requirementRepository.save(requirements);
    }

    public Flux<Requirements> findRequirementsByClass(Long classId) {
        return requirementRepository.findByClassId(classId);
    }
}

package org.bir.rrmanila.charter.services;

import org.bir.rrmanila.charter.entities.Process;
import org.bir.rrmanila.charter.repositories.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProcessServices {
    @Autowired private ProcessRepository processRepository;

    public Mono<Process> createCharterProcess(Process process) {
        return processRepository.save(process);
    }

    public Flux<Process> findProcessByCharter(UUID charterId) {
        return processRepository.findByCharterId(charterId);
    }
}

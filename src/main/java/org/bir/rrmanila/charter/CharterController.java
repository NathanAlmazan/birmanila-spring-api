package org.bir.rrmanila.charter;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Controller
public class CharterController {
    @Autowired private CharterService charterService;

    @QueryMapping
    public Mono<Charter> findCharterById(@Argument UUID uuid) {
        return charterService.findCharterById(uuid);
    }

    @QueryMapping
    public Flux<Charter> findAllCharter(@Argument Integer page, @Argument Integer rowsPerPage) {
        return charterService.findAllCharter(page, rowsPerPage);
    }

    @QueryMapping
    public Flux<Charter> searchCharter(@Argument String search) {
        return charterService.searchCharter(search);
    }

    @MutationMapping
    public Mono<Charter> createCharter(@Argument("charter") @Valid CharterInput charterInput) {
        return charterService.createCharter(charterInput);
    }

    @MutationMapping
    public Mono<Charter> updateCharter(@Argument UUID uuid, @Argument("charter") @Valid CharterInput charterInput) {
        return charterService.updateCharter(uuid, charterInput);
    }
}

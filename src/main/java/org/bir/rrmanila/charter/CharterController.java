package org.bir.rrmanila.charter;

import jakarta.validation.Valid;
import org.bir.rrmanila.charter.entities.*;
import org.bir.rrmanila.charter.entities.Process;
import org.bir.rrmanila.charter.services.CharterService;
import org.bir.rrmanila.charter.services.ProcessServices;
import org.bir.rrmanila.charter.services.RequirementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
public class CharterController {
    @Autowired private CharterService charterService;
    @Autowired private RequirementServices requirementServices;
    @Autowired private ProcessServices processServices;

    @QueryMapping
    public Mono<Categories> findCategoryById(@Argument Long categoryId) {
        return charterService.findCategoryById(categoryId);
    }

    @QueryMapping
    public Flux<Categories> findAllCategories() {
        return charterService.findAllCategories();
    }

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
    public Mono<Categories> createCharterCategory(@Argument String name) {
        return charterService.createCategory(name);
    }

    @MutationMapping
    public Mono<Charter> createCharter(@Argument("charter") @Valid Charter charterInput) {
        return charterService.createCharter(charterInput);
    }

    @SchemaMapping(typeName = "Charter", field = "requirementClasses")
    public Flux<RequirementClass> requirementClassByCharter(Charter charter) {
        return requirementServices.findReqClassByCharter(charter.getUuid());
    }

    @SchemaMapping(typeName = "Charter", field = "processList")
    public Flux<Process> processByCharter(Charter charter) {
        return processServices.findProcessByCharter(charter.getUuid());
    }

    @SchemaMapping(typeName = "RequirementClass", field = "requirements")
    public Flux<Requirements> requirementsByClass(RequirementClass requirementClass) {
        return requirementServices.findRequirementsByClass(requirementClass.getId());
    }
}

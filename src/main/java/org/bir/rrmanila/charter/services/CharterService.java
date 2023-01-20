package org.bir.rrmanila.charter.services;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.apache.commons.lang3.StringUtils;
import org.bir.rrmanila.charter.entities.Categories;
import org.bir.rrmanila.charter.entities.Charter;
import org.bir.rrmanila.charter.entities.Process;
import org.bir.rrmanila.charter.entities.RequirementClass;
import org.bir.rrmanila.charter.repositories.CategoryRepository;
import org.bir.rrmanila.charter.repositories.CharterRepository;
import org.bir.rrmanila.statistics.CosineDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.BiFunction;

@Service
public class CharterService implements CosineDistance {
    @Autowired private CharterRepository charterRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ProcessServices processServices;
    @Autowired private RequirementServices requirementServices;
    @Autowired private DatabaseClient client;

    // charter category services

    public Mono<Categories> createCategory(String categoryName) {
        Categories category = new Categories();
        category.setName(categoryName);

        return categoryRepository.save(category);
    }

    public Mono<Categories> findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Flux<Categories> findAllCategories() {
        return categoryRepository.findAll();
    }

    // charter services

    private static final BiFunction<Row, RowMetadata, Charter> MAPPING_FUNCTION = (row, rowMetaData) -> new Charter(
        row.get("charter_uid", UUID.class),
        row.get("charter_chapter", Integer.class),
        row.get("charter_title", String.class),
        row.get("charter_desc", String.class),
        row.get("charter_location", String.class),
        row.get("charter_applicants", String.class),
        row.get("charter_period", String.class),
        row.get("charter_fee", String.class),
        row.get("charter_duration", String.class),
        row.get("charter_category_id", Long.class),
        null, null
    );

    public Mono<Charter> createCharter(Charter charterInput) {
        return charterRepository.save(charterInput).doOnNext(charter -> {

            for (RequirementClass requirementClass : charter.getRequirementClasses()) {
                requirementClass.setCharterId(charter.getUuid());
                requirementServices.createRequirementClass(requirementClass).subscribe();
            }

            for (Process process : charter.getProcessList()) {
                process.setCharterId(charter.getUuid());
                processServices.createCharterProcess(process).subscribe();
            }
        });
    }

    public Mono<Charter> findCharterById(UUID uuid) {
        return charterRepository.findById(uuid);
    }

    public Flux<Charter> findAllCharter() {
        return charterRepository.findAll();
    }

    public Flux<Charter> findChartersByCategory(Integer categoryId) {
        return charterRepository.findByCategoryId(categoryId);
    }

    public Flux<Charter> searchCharter(String query) {
        // split query into array of words
        String[] words = query.split(" ");

        // convert each word to ILIKE query
        List<String> queries = new ArrayList<>();
        for (String word : words) {
            queries.add("charter_title ILIKE '%" + word + "%' OR charter_desc ILIKE '%" + word + "%' OR charter_applicants ILIKE '%" + word + "%'");
        }
        String dbQuery = StringUtils.join(queries, " OR ");

        Flux<Charter> results = client
                .sql("SELECT * FROM citizen_charter WHERE " + dbQuery)
                .map(MAPPING_FUNCTION)
                .all();

        return results.sort((o1, o2) -> Double.compare(
                computeCosineDistance(query, o2.getSearchQuery()),
                computeCosineDistance(query, o1.getSearchQuery())
        ));
    }
}

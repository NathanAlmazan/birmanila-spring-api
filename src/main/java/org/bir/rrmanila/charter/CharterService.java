package org.bir.rrmanila.charter;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired private DatabaseClient client;

    private static final BiFunction<Row, RowMetadata, Charter> MAPPING_FUNCTION = (row, rowMetaData) -> new Charter(
        row.get("charter_uid", UUID.class),
        row.get("charter_chapter", Integer.class),
        row.get("charter_title", String.class),
        row.get("charter_desc", String.class),
        row.get("charter_applicants", String.class),
        row.get("charter_fee", Double.class),
        row.get("charter_duration", String.class)
    );

    public Mono<Charter> createCharter(CharterInput charterInput) {
        Charter charter = new Charter();
        charter.setChapter(charterInput.getChapter());
        charter.setTitle(charterInput.getTitle());
        charter.setDescription(charterInput.getDescription());
        charter.setApplicants(charterInput.getApplicants());
        charter.setFee(charterInput.getFee());
        charter.setDuration(charterInput.getDuration());

        return charterRepository.save(charter);
    }

    public Mono<Charter> updateCharter(UUID uuid, CharterInput charterInput) {
        Charter charter = new Charter();
        charter.setUuid(uuid);
        charter.setChapter(charterInput.getChapter());
        charter.setTitle(charterInput.getTitle());
        charter.setDescription(charterInput.getDescription());
        charter.setApplicants(charterInput.getApplicants());
        charter.setFee(charterInput.getFee());
        charter.setDuration(charterInput.getDuration());

        return charterRepository.save(charter);
    }

    public Mono<Charter> findCharterById(UUID uuid) {
        return charterRepository.findById(uuid);
    }

    public Flux<Charter> findAllCharter(Integer page, Integer rowsPerPage) {
        Integer limit = page *  rowsPerPage;
        Integer offset = (page - 1) * rowsPerPage;

        return charterRepository.findAllCharter(limit, offset);
    }

    public Flux<Charter> searchCharter(String query) {
        // split query into array of words
        String[] words = query.split(" ");

        // convert each word to ILIKE query
        List<String> queries = new ArrayList<>();
        for (String word : words) {
            queries.add("charter_title ILIKE '%" + word + "%' OR charter_desc ILIKE '%" + word + "%'");
        }
        String dbQuery = StringUtils.join(queries, " OR ");

        Flux<Charter> results = client
                .sql("SELECT * FROM citizen_charter WHERE " + dbQuery)
                .map(MAPPING_FUNCTION)
                .all();

        return results.sort((o1, o2) -> Double.compare(
                computeCosineDistance(query, o2.getTitle()) + computeCosineDistance(query, o2.getDescription()),
                computeCosineDistance(query, o1.getTitle()) + computeCosineDistance(query, o1.getDescription())
        ));
    }
}

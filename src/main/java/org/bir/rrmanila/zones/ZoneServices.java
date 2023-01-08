package org.bir.rrmanila.zones;

import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.bir.rrmanila.offices.repositories.DistrictRepository;
import org.bir.rrmanila.zones.entities.ZonalClassification;
import org.bir.rrmanila.zones.entities.ZonalValue;
import org.bir.rrmanila.zones.entities.Zone;
import org.bir.rrmanila.zones.repositories.ZonalClassRepository;
import org.bir.rrmanila.zones.repositories.ZonalValueRepository;
import org.bir.rrmanila.zones.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ZoneServices {
    @Autowired private ZoneRepository zoneRepository;
    @Autowired private ZonalValueRepository zonalValueRepository;
    @Autowired private ZonalClassRepository zonalClassRepository;
    @Autowired private DistrictRepository districtRepository;

    public Flux<Zone> findZonesByDistrict(Integer district) {
        return zoneRepository.findByRevenueDistrict(district);
    }

    public Flux<ZonalValue> findValuesByZone(Integer zoneNum) {
        return zonalValueRepository.findByZoneNumber(zoneNum);
    }

    public Flux<ZonalValue> findValuesByClass(String classCode) {
        return zonalValueRepository.findByClassification(classCode);
    }

    public Mono<ZonalClassification> findClassByCode(String classCode) {
        return zonalClassRepository.findById(classCode);
    }

    public Flux<ZonalClassification> findAllClass() {
        return zonalClassRepository.findAll();
    }

    public Mono<RevenueDistrict> districtByZone(Integer district) {
        return districtRepository.findById(district);
    }
}

package org.bir.rrmanila.zones;

import org.bir.rrmanila.offices.entities.RevenueDistrict;
import org.bir.rrmanila.zones.entities.ZonalClassification;
import org.bir.rrmanila.zones.entities.ZonalValue;
import org.bir.rrmanila.zones.entities.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ZoneController {
    @Autowired private ZoneServices zoneServices;

    @QueryMapping
    public Flux<Zone> findZonesByDistrict(@Argument Integer district) {
        return zoneServices.findZonesByDistrict(district);
    }

    @QueryMapping
    public Flux<ZonalValue> findValuesByZone(@Argument Integer zoneNumber) {
        return zoneServices.findValuesByZone(zoneNumber);
    }

    @QueryMapping
    public Flux<ZonalValue> findValuesByClass(@Argument String classCode) {
        return zoneServices.findValuesByClass(classCode);
    }

    @QueryMapping
    public Flux<ZonalClassification> findAllZonalClass() {
        return zoneServices.findAllClass();
    }

    @SchemaMapping(typeName = "Zone", field = "district")
    public Mono<RevenueDistrict> revenueDistrictByZone(Zone zone) {
        return zoneServices.districtByZone(zone.getRevenueDistrict());
    }

    @SchemaMapping(typeName = "ZonalValue", field = "classification")
    public Mono<ZonalClassification> classificationByValue(ZonalValue zonalValue) {
        return zoneServices.findClassByCode(zonalValue.getClassification());
    }
}

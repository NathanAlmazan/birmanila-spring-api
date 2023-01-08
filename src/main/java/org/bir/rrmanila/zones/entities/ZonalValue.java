package org.bir.rrmanila.zones.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "zonal_values")
public class ZonalValue {
    @Id
    @Column("zonal_id")
    private Integer id;

    @Column("zonal_barangay")
    private String barangay;

    @Column("zone_num")
    private Integer zoneNumber;

    @Column("zonal_street_subdivision")
    private String streetSubdivision;

    @Column("zonal_vicinity")
    private String vicinity;

    @Column("class_code")
    private String classification;

    @Column("zonal_price_per_sqm")
    private Double pricePerSqm;
}

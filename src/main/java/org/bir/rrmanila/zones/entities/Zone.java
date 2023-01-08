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
@Table(name = "zones")
public class Zone {
    @Id
    @Column("zone_id")
    private Integer id;

    @Column("zone_num")
    private Integer number;

    @Column("zone_barangay")
    private String barangay;

    @Column("zone_boundary")
    private String boundaries;

    @Column("rdo_no")
    private Integer revenueDistrict;
}

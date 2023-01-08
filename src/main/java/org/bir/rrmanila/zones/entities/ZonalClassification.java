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
@Table(name = "zonal_classification")
public class ZonalClassification {
    @Id
    @Column("class_code")
    private String code;

    @Column("class_name")
    private String name;
}

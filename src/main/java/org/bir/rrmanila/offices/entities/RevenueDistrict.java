package org.bir.rrmanila.offices.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "districts")
public class RevenueDistrict {
    @Id
    @Column("rdo_no")
    private Integer number;

    @Column("rdo_name")
    private String name;
}

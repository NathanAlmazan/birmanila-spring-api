package org.bir.rrmanila.offices.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offices")
public class BirOffice {
    @Id
    @Column("office_id")
    private Integer id;

    @Column("office_name")
    private String name;

    @Column("office_address")
    private String address;

    @Column("office_email")
    private String email;

    @Column("rdo_no")
    private Integer district;

    public String getSearchQuery() {
        return name + " " + address;
    }
}

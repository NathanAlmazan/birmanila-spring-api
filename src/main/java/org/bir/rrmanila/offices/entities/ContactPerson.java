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
@Table(name = "contact_person")
public class ContactPerson {
    @Id
    @Column("cp_id")
    private Integer id;

    @Column("cp_name")
    private String name;

    @Column("cp_position")
    private String position;

    @Column("cp_office_id")
    private Integer office;
}

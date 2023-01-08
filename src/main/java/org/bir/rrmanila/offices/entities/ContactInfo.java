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
@Table(name = "contacts")
public class ContactInfo {
    @Id
    @Column("contact_id")
    private Integer id;

    @Column("contact_num")
    private String number;

    @Column("contact_type")
    private ContactType type;

    @Column("contact_person_id")
    private Integer person;
}

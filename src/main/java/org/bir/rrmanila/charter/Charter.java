package org.bir.rrmanila.charter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "citizen_charter")
public class Charter {
    @Id
    @Column("charter_uid")
    private UUID uuid;

    @Column("charter_chapter")
    private Integer chapter;

    @Column("charter_title")
    private String title;

    @Column("charter_desc")
    private String description;

    @Column("charter_applicants")
    private String applicants;

    @Column("charter_fee")
    private Double fee;

    @Column("charter_duration")
    private String duration;
}

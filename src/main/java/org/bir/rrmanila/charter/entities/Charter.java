package org.bir.rrmanila.charter.entities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
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

    @NotNull
    @Column("charter_chapter")
    private Integer chapter;

    @NotNull
    @Column("charter_title")
    private String title;

    @NotNull
    @Column("charter_desc")
    private String description;

    @NotNull
    @Column("charter_location")
    private String location;

    @NotNull
    @Column("charter_applicants")
    private String applicants;

    @Column("charter_period")
    private String period;

    @Column("charter_fee")
    private String fee;

    @NotNull
    @Column("charter_duration")
    private String duration;

    @NotNull
    @Column("charter_category_id")
    private Long categoryId;

    @Transient
    @NotNull
    @Valid
    private List<Process> processList;

    @Transient
    @NotNull
    @Valid
    private List<RequirementClass> requirementClasses;
}

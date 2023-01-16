package org.bir.rrmanila.charter.entities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "charter_requirements_class")
public class RequirementClass {

    @Id
    @Column("class_id")
    private Long id;

    @NotNull
    @Column("class_name")
    private String name;

    @Column("class_required")
    private Boolean required;

    @Column("charter_uid")
    private UUID charterId;

    @Transient
    @NotNull
    @Valid
    private List<Requirements> requirements;
}

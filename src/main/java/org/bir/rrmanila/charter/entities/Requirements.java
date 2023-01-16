package org.bir.rrmanila.charter.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "charter_requirements")
public class Requirements {

    @Id
    @Column("req_id")
    private Long id;

    @NotNull
    @Column("req_name")
    private String name;

    @Column("req_notes")
    private String notes;

    @Column("req_required")
    private Boolean required;

    @Column("req_redirect")
    private Redirects redirect;

    @Column("req_class_id")
    private Long classId;
}

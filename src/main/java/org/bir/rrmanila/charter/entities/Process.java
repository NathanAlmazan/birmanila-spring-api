package org.bir.rrmanila.charter.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "charter_process")
public class Process {

    @Id
    @Column("process_id")
    private Long id;

    @NotNull
    @Column("process_step")
    private Integer step;

    @NotNull
    @Column("process_desc")
    private String description;

    @NotNull
    @Column("process_type")
    private ProcessType type;

    @Column("process_duration")
    private String duration;

    @Column("process_fee")
    private String fee;

    @Column("process_notes")
    private String notes;

    @Column("process_redirect")
    private Redirects redirect;

    @Column("charter_uid")
    private UUID charterId;
}

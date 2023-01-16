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
@Table(name = "charter_categories")
public class Categories {

    @Id
    @Column("category_id")
    private Long id;

    @NotNull
    @Column("category_name")
    private String name;
}

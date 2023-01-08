package org.bir.rrmanila.banks.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "bank_details")
public class BankDetails {
    @Id
    @Column("bank_abbr")
    private String shortName;

    @Column("bank_name")
    private String fullName;

    @Column("bank_logo")
    private String logo;
}

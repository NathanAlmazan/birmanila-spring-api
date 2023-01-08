package org.bir.rrmanila.banks.entities;

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
@Table(name = "accredited_banks")
public class AccreditedBank {
    @Id
    @Column("bank_no")
    private Long id;

    @Column("rdo_no")
    private Integer revenueDistrict;

    @Column("bank_code")
    private String code;

    @Column("bank_abbr")
    private String name;

    @Column("bank_branch")
    private String branch;

    @Column("bldg_no")
    private String address;

    @Column("street")
    private String street;

    @Column("district")
    private String district;

    @Column("city")
    private String city;

    public String getFullAddress() {
        return address + " " + street + " " + district;
    }
}

package org.bir.rrmanila.charter;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CharterInput {

    @NotNull(message = "Charter chapter is required.")
    private Integer chapter;

    @NotNull(message = "Charter title is required.")
    private String title;

    @NotNull(message = "Charter description is required.")
    private String description;

    @NotNull(message = "Charter applicants is required.")
    private String applicants;

    @NotNull(message = "Charter fee is required.")
    private Double fee;

    @NotNull(message = "Charter duration is required.")
    @Length(min = 1, max = 20, message = "Charter duration should be between 1 to 20 characters.")
    private String duration;
}

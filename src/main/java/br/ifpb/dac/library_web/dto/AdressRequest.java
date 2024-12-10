package br.ifpb.dac.library_web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressRequest {

    @NotBlank(message = "The street field cannot be empty")
    private String street;

    @NotBlank(message = "The city field cannot be empty")
    private String city;

    @NotBlank(message = "The state field cannot be empty")
    private String state;

    @NotBlank(message = "The zip code field cannot be empty")
    private String zipCode;

    @NotBlank(message = "The neighborhood code field cannot be empty")
    private String neighborhood;

    @NotBlank(message = "The country code field cannot be empty")
    private String country;

}

package br.ifpb.dac.library_web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryRequest {

    @NotBlank(message = "The name field cannot be empty")
    private String name;

    @NotNull(message = "The address field cannot be empty")
    private AdressRequest address; // endereço associado

    private Long contractId;
}

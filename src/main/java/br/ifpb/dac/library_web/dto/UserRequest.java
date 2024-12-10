package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.TypeUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The e-mail cannot be empty")
    @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Email with invalid format")
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Size(message = "Password with invalid format", max = 6, min = 6)
    private String password;

    @NotNull(message = "The type user cannot be empty")
    private TypeUser type;

    @Valid
    private AdressRequest adress;
}

package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String type;

}

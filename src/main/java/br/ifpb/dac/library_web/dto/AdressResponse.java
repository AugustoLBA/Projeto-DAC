package br.ifpb.dac.library_web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdressResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String neighborhood;
    private String country;
    private Long userId; // ID do usuário associado (opcional)
    private Long libraryId; // ID da biblioteca associada (opcional)
}

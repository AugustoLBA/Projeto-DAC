package br.ifpb.dac.library_web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryResponse {

    private Long id;
    private String name;
    private AdressResponse address; // ID do endereço associado
    private Long contractId; // ID do contrato associado
}

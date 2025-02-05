package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.AdressRequest;
import br.ifpb.dac.library_web.dto.LibraryRequest;
import br.ifpb.dac.library_web.dto.LibraryResponse;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.Library;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryMapper {
    // Converte LibraryRequest para Library
    public static Library toLibrary(LibraryRequest request) {

        Library library = new Library();
        library.setName(request.getName());

        // Converte AdressRequest para Adress
        Adress address = AdressMapper.toAdress(request.getAddress());
        address.setLibrary(library); // Define a biblioteca no endereço
        library.setAdress(address);

        // Associa o contrato pelo ID (se fornecido)
        if (request.getContractId() != null) {
            Contract contract = new Contract();
            contract.setId(request.getContractId());
            library.setContract(contract);
        }

        return library;
    }
    public static LibraryResponse toResponse(Library library) {
        return new LibraryResponse(
                library.getId(),
                library.getName(),
                library.getAdress() != null ? AdressMapper.toResponse(library.getAdress()) : null, // Converte Adress para AdressResponse
                library.getContract() != null ? library.getContract().getId() : null
        );
    }
    public static List<LibraryResponse> toResponseList(List<Library> libraries) {
        return libraries.stream()
                .map(LibraryMapper::toResponse)
                .collect(Collectors.toList());
    }
}

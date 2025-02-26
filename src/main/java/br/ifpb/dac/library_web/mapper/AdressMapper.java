package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.AdressRequest;
import br.ifpb.dac.library_web.dto.AdressResponse;
import br.ifpb.dac.library_web.entity.Adress;
import org.modelmapper.ModelMapper;

public class AdressMapper {

    public static Adress toAdress(AdressRequest request) {
        return new ModelMapper().map(request, Adress.class);
    }

    public static AdressResponse toResponse(Adress adress) {
        return new AdressResponse(
                adress.getId(),
                adress.getStreet(),
                adress.getCity(),
                adress.getState(),
                adress.getZipCode(),
                adress.getNeighborhood(),
                adress.getCountry(),
                adress.getUser() != null ? adress.getUser().getId() : null,
                adress.getLibrary() != null ? adress.getLibrary().getId() : null
        );
    }
}

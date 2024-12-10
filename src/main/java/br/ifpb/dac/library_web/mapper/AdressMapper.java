package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.AdressRequest;
import br.ifpb.dac.library_web.entity.Adress;
import org.modelmapper.ModelMapper;

public class AdressMapper {

    public static Adress toAdress(AdressRequest request) {
        return new ModelMapper().map(request, Adress.class);
    }
}

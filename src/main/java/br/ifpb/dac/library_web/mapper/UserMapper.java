package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.UserRequest;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.entity.User;
import org.modelmapper.ModelMapper;


public class UserMapper {

    public static User toUser(UserRequest request) {
        Adress adress = AdressMapper.toAdress(request.getAdress());
        User user =  new ModelMapper().map(request, User.class);
        user.setAdress(adress);
        return user;
    }
}

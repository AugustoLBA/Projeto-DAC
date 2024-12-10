package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.UserRequest;
import br.ifpb.dac.library_web.dto.UserResponse;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.enumeration.TypeUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {

    public static User toUser(UserRequest request) {
        Adress adress = AdressMapper.toAdress(request.getAdress());
        User user =  new ModelMapper().map(request, User.class);
        adress.setUser(user);
        user.setAdress(adress);
        return user;
    }

    public static UserResponse toUserResponse(User user){
        String type = user.getType().name().substring("ROLE_".length());
        PropertyMap<User, UserResponse> props = new PropertyMap<User, UserResponse>() {
            @Override
            protected void configure() {
                map().setType(type);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponse.class);
    }

    public static List<UserResponse> toListUserResponse(List<User> users){
        return users.stream().map(user -> toUserResponse(user)).collect(Collectors.toList());
    }
}

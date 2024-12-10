package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.UserRequest;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.mapper.AdressMapper;
import br.ifpb.dac.library_web.mapper.UserMapper;
import br.ifpb.dac.library_web.repository.UserRepository;
import br.ifpb.dac.library_web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRequest> save(@Valid @RequestBody UserRequest request){
        userService.saveUser(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.UserRequest;
import br.ifpb.dac.library_web.dto.UserResponse;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.mapper.AdressMapper;
import br.ifpb.dac.library_web.mapper.UserMapper;
import br.ifpb.dac.library_web.repository.UserRepository;
import br.ifpb.dac.library_web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        UserResponse response = UserMapper.toUserResponse(userService.findUserById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(UserMapper.toListUserResponse(userService.findAllUsers()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

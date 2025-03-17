package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.enumeration.TypeUser;
import br.ifpb.dac.library_web.exception.PasswordInvalidException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * saveUser: Método que realiza a criação e atualização do User.
     * O Spring Data JPA gerencia automaticamente as operações de persistência.
     * @param user
     * @return
     */
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    /**
     * findUserById: Recupera um usuário pelo ID.
     * Lança uma exceção personalizada (ResourceNotFoundException) se o usuário não for encontrado.
     * @param id
     * @return User
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)                     //"Usuário não encontrado com ID"
                .orElseThrow(()-> new ResourceNotFoundException(MessageKeyEnum.USER_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    /**
     * findAllUsers: Retorna uma lista de todos os usuários armazenados no banco de dados.
     * @return List<User>
     */

    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * deleteUserById: Exclui um usuário pelo ID e verifica se ele existe antes de realizar a exclusão.
     * @param id
     */
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(MessageKeyEnum.USER_NOT_FOUND_WITH_ID.getMessage(id));
        }
        userRepository.deleteById(id);
    }

    /**
     * findUserByEmail recupera um usuário por email.
     * Lança uma exceção personalizada (ResourceNotFoundException) se o usuário não for encontrado.
     * @param email
     * @return
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)              //Usuário não encontrado com e-mail
                .orElseThrow(()-> new ResourceNotFoundException(MessageKeyEnum.USER_NOT_FOUND_WITH_ID.getMessage(email)));
    }

    public TypeUser findUserByType(String email) {
        return userRepository.findUserTypeByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(MessageKeyEnum.USER_NOT_FOUND_WITH_ID.getMessage(email))
        );
    }

    @Transactional
    public User atualizarSenha(Long id, String senhaAtual, String novaSenha, String comfirmaSenha) {
        if(!novaSenha.equals(comfirmaSenha)){
            throw  new PasswordInvalidException(MessageKeyEnum.PASSWORD_INVALID.getMessage());
        }
        User user = findUserById(id);
        if(!passwordEncoder.matches(senhaAtual, user.getPassword())){
            throw new PasswordInvalidException(MessageKeyEnum.PASSWORD_INVALID.getMessage());
        }
        user.setPassword(passwordEncoder.encode(novaSenha));
        //Essa abordagem de chamar o save do objeto usuarioRepository.save(user);
        //não teria problema nenhum.
        return user;
    }
}

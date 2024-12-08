package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * saveUser: Método que realiza a criação e atualização do User.
     * O Spring Data JPA gerencia automaticamente as operações de persistência.
     * @param user
     * @return
     */
    public User saveUser(User user) {

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

}

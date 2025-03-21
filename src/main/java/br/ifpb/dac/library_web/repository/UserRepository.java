package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.enumeration.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Metodo adicional para buscar um Usuario por email
     * @param email
     * @return O método findByEmail retorna um Optional<User> porque o email pode não existir no banco de dados.
     */
    Optional<User> findByEmail(String email);


    @Query("SELECT u.type FROM User u WHERE u.email = :email")
    Optional<TypeUser> findUserTypeByEmail(String email);

}


package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

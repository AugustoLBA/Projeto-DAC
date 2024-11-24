package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

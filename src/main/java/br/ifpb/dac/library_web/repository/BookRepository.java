package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

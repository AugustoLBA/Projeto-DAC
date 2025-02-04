package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Exemplary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {

    @Query("SELECT e FROM Exemplary e WHERE e.book.id = :bookId")
    List<Exemplary> findAllByBookId(Long bookId);

}

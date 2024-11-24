package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}

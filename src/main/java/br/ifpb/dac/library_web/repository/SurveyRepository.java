package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}

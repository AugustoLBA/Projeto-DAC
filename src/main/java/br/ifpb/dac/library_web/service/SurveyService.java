package br.ifpb.dac.library_web.service;


import br.ifpb.dac.library_web.dto.AuthorRequest;
import br.ifpb.dac.library_web.dto.SurveyRequest;
import br.ifpb.dac.library_web.entity.Author;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Survey;
import br.ifpb.dac.library_web.enumeration.StatusSurvey;
import br.ifpb.dac.library_web.exception.SurveyNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.ExemplaryRepository;
import br.ifpb.dac.library_web.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final ExemplaryRepository exemplaryRepository;

    /**
     * saveSurvey: Metodo que realiza a criação de um Survey(Inspeção).
     *
     * @param survey
     * @return
     */
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    /**
     * findSurveyAll: Retorna uma lista de todas as inspessoes.
     *
     * @return
     */
    public List<Survey> findSurveyAll() {
        return surveyRepository.findAll();
    }

    /**
     * findSurveyById: retorna uma inspeção a partir de um ID.
     * Lança um exceção personalizada caso a inspeção não seja encontrada.
     *
     * @param id
     * @return
     */
    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    /**
     * deleteSurveyById: Deleta uma inspeção a partir de um ID.
     *
     * @param id
     */
    public void deleteSurveyById(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id));
        }
        surveyRepository.deleteById(id);
    }

    public Survey update(Long id, SurveyRequest surveyRequest) {
        Survey existingSurvey = findSurveyById(id); // Busca a vistoria existente
        Exemplary exemplary = exemplaryRepository.findById(surveyRequest.getExemplary_id())
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));

        // Atualiza os campos
        existingSurvey.setDate_performed(surveyRequest.getDate_performed());
        existingSurvey.setStatusSurvey(surveyRequest.getStatusSurvey());
        existingSurvey.setExemplary(exemplary);

        return surveyRepository.save(existingSurvey);
    }

}



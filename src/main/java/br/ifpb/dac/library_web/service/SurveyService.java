package br.ifpb.dac.library_web.service;


import br.ifpb.dac.library_web.entity.Survey;
import br.ifpb.dac.library_web.exception.SurveyNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    /**
     * saveSurvey: Metodo que realiza a criação de um Survey(Inspeção).
     * @param survey
     * @return
     */
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    /**
     * findSurveyAll: Retorna uma lista de todas as inspessoes.
     * @return
     */
    public List<Survey> findSurveyAll () {
        return surveyRepository.findAll();
    }

    /**
     * findSurveyById: retorna uma inspeção a partir de um ID.
     * Lança um exceção personalizada caso a inspeção não seja encontrada.
     * @param id
     * @return
     */
    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    /**
     * deleteSurveyById: Deleta uma inspeção a partir de um ID.
     * @param id
     */
    public void deleteSurveyById(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id));
        }
        surveyRepository.deleteById(id);
    }

    /**
     * updateSurvey recupera uma inspeção no Banco de Dados e atualiza seus dados.
     * Lança um exceção personalizada caso a inspeção não seja encontrada.
     * @param id
     * @param survey
     * @return
     */
    public Survey updateSurvey(Long id, Survey survey) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));
        existingSurvey.setDate_performed(survey.getDate_performed());
        existingSurvey.setStatusSurvey(survey.getStatusSurvey());
        existingSurvey.setExemplary(survey.getExemplary());
        return surveyRepository.save(existingSurvey);
    }
}

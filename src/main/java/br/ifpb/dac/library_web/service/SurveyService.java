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

    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    public void deleteById(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id));
        }
        surveyRepository.deleteById(id);
    }
    public Survey update(Long id, Survey survey) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(MessageKeyEnum.SURVEY_NOT_FOUND_WITH_ID.getMessage(id)));
        existingSurvey.setDate_performed(survey.getDate_performed());
        existingSurvey.setStatusSurvey(survey.getStatusSurvey());
        existingSurvey.setExemplary(survey.getExemplary());
        return surveyRepository.save(existingSurvey);
    }
}

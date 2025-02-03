package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.SurveyRequest;
import br.ifpb.dac.library_web.dto.SurveyResponse;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Survey;
import br.ifpb.dac.library_web.enumeration.StatusSurvey;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyMapper {
    public static Survey toSurvey(SurveyRequest request) {
        Survey survey = new Survey();
        survey.setDate_performed(request.getDate_performed());
        survey.setStatusSurvey(request.getStatusSurvey()); // Converte String para enum

        Exemplary exemplary = new Exemplary();
        exemplary.setId(request.getExemplary_id());
        survey.setExemplary(exemplary);

        return survey;
    }

    public static SurveyResponse toResponse(Survey survey) {
        return new SurveyResponse(
                survey.getId(),
                survey.getDate_performed(),
                survey.getStatusSurvey().name(), // Converte enum para String
                survey.getExemplary().getId() // Retorna o ID do Exemplary
        );
    }
    public static List<SurveyResponse> toResponseList(List<Survey> surveys) {
        return surveys.stream()
                .map(SurveyMapper::toResponse) // Converte cada Survey para SurveyResponse
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }
}

package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.entity.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private Long id;
    private LocalDateTime date_performed;
    private String statusSurvey; // Retorna o status como String
    private Long exemplary_id; // Retorna o ID do Exemplary

}


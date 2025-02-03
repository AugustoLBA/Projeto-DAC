package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.StatusSurvey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequest {

    @NotNull(message = "The date_performed field cannot be empty")
    private LocalDateTime date_performed;

    @NotNull(message = "The status field cannot be empty")
    private StatusSurvey statusSurvey; // Recebe o status como String

    @NotNull(message = "The exemplary_id field cannot be empty")
    private Long exemplary_id; // Recebe o ID do Exemplary

}

package br.ifpb.dac.library_web.dto;


import br.ifpb.dac.library_web.enumeration.LoanStatus;
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
public class LoanRequest {

    @NotNull(message = "The loan end date field cannot be empty")
    private LocalDateTime loanEndDate;

    @NotNull(message = "The loan status field cannot be empty")
    private LoanStatus loanStatus;

    @NotNull(message = "The exemplary ID field cannot be empty")
    private Long exemplaryId; // ID do exemplar associado

    @NotNull(message = "The user ID field cannot be empty")
    private Long userId; // ID do usuário associado
}

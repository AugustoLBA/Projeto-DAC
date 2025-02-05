package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private Long id;
    private LocalDateTime loanInitialDate;
    private LocalDateTime loanEndDate;
    private LoanStatus loanStatus;
    private Long exemplaryId; // ID do exemplar associado
    private Long userId; // ID do usuário associado
}

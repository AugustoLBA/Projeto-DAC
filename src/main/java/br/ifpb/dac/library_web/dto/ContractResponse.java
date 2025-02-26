package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.StatusContract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {

    private Long id;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private StatusContract status;
    private BigDecimal value;
    private Long userId; // ID do usuário associado
    private Long libraryId; // ID da biblioteca associada
    private List<Long> clauseIds; // IDs das cláusulas associadas
}

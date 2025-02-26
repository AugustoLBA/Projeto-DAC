package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.StatusContract;
import jakarta.validation.constraints.NotNull;
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
public class ContractRequest {

    @NotNull(message = "The contract start date field cannot be empty")
    private LocalDateTime contractStartDate;

    @NotNull(message = "The contract end date field cannot be empty")
    private LocalDateTime contractEndDate;

    @NotNull(message = "The status field cannot be empty")
    private StatusContract status;

    @NotNull(message = "The value field cannot be empty")
    private BigDecimal value;

    @NotNull(message = "The user ID field cannot be empty")
    private Long userId; // ID do usuário associado

    @NotNull(message = "The library ID field cannot be empty")
    private Long libraryId; // ID da biblioteca associada

    private List<Long> clauseIds; // IDs das cláusulas associadas
}

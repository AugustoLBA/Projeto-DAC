package br.ifpb.dac.library_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractClauseRequest {

    @NotBlank(message = "The description field cannot be empty")
    private String description;

    @NotNull(message = "The fine value field cannot be empty")
    private BigDecimal fineValue;

    @NotNull(message = "The contract ID field cannot be empty")
    private Long contractId; // ID do contrato associado

}

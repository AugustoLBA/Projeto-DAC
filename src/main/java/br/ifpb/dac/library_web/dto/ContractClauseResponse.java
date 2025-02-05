package br.ifpb.dac.library_web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractClauseResponse {
    private Long id;
    private String description;
    private BigDecimal fineValue;
    private Long contractId;
}

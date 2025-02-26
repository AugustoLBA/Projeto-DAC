package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.ContractClauseRequest;
import br.ifpb.dac.library_web.dto.ContractClauseResponse;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.ContractClause;

import java.util.List;
import java.util.stream.Collectors;

public class ContractClauseMapper {

    // Converte ContractClauseRequest para ContractClause
    public static ContractClause toContractClause(ContractClauseRequest request) {
        ContractClause clause = new ContractClause();
        clause.setDescription(request.getDescription());
        clause.setFineValue(request.getFineValue());

        // Associa o contrato pelo ID
        Contract contract = new Contract();
        contract.setId(request.getContractId());
        clause.setContract(contract);

        return clause;
    }

    // Converte ContractClause para ContractClauseResponse
    public static ContractClauseResponse toResponse(ContractClause clause) {
        return new ContractClauseResponse(
                clause.getId(),
                clause.getDescription(),
                clause.getFineValue(),
                clause.getContract() != null ? clause.getContract().getId() : null
        );
    }

    // Converte uma lista de ContractClause para uma lista de ContractClauseResponse
    public static List<ContractClauseResponse> toResponseList(List<ContractClause> clauses) {
        return clauses.stream()
                .map(ContractClauseMapper::toResponse)
                .collect(Collectors.toList());
    }
}

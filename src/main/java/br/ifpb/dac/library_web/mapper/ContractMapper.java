package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.ContractRequest;
import br.ifpb.dac.library_web.dto.ContractResponse;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.ContractClause;
import br.ifpb.dac.library_web.entity.Library;
import br.ifpb.dac.library_web.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ContractMapper {
    public static Contract toContract(ContractRequest request) {
        Contract contract = new Contract();
        contract.setContractStartDate(request.getContractStartDate());
        contract.setContractEndDate(request.getContractEndDate());
        contract.setStatus(request.getStatus());
        contract.setValue(request.getValue());

        // Associa o usuário e a biblioteca pelos IDs
        User user = new User();
        user.setId(request.getUserId());
        contract.setUser(user);

        Library library = new Library();
        library.setId(request.getLibraryId());
        contract.setLibrary(library);

        // Associa as cláusulas pelos IDs
        if (request.getClauseIds() != null) {
            List<ContractClause> clauses = request.getClauseIds().stream()
                    .map(clauseId -> {
                        ContractClause clause = new ContractClause();
                        clause.setId(clauseId);
                        return clause;
                    })
                    .collect(Collectors.toList());
            contract.setClause(clauses);
        }

        return contract;
    }

    // Converte Contract para ContractResponse
    public static ContractResponse toResponse(Contract contract) {
        List<Long> clauseIds = contract.getClause() != null
                ? contract.getClause().stream()
                .map(ContractClause::getId)
                .collect(Collectors.toList())
                : List.of(); // Retorna uma lista vazia se não houver cláusulas

        return new ContractResponse(
                contract.getId(),
                contract.getContractStartDate(),
                contract.getContractEndDate(),
                contract.getStatus(),
                contract.getValue(),
                contract.getUser() != null ? contract.getUser().getId() : null,
                contract.getLibrary() != null ? contract.getLibrary().getId() : null,
                clauseIds
        );
    }

    // Converte uma lista de Contract para uma lista de ContractResponse
    public static List<ContractResponse> toResponseList(List<Contract> contracts) {
        return contracts.stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }
}


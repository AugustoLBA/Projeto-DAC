package br.ifpb.dac.library_web.mapper;

import br.ifpb.dac.library_web.dto.LoanRequest;
import br.ifpb.dac.library_web.dto.LoanResponse;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Loan;
import br.ifpb.dac.library_web.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class LoanMapper {

    // Converte LoanRequest para Loan
    public static Loan toLoan(LoanRequest request) {
        Loan loan = new Loan();
        loan.setLoanEndDate(request.getLoanEndDate());
        loan.setLoanStatus(request.getLoanStatus());

        // Associa o exemplar e o usuário pelos IDs
        Exemplary exemplary = new Exemplary();
        exemplary.setId(request.getExemplaryId());
        loan.setExemplary(exemplary);

        User user = new User();
        user.setId(request.getUserId());
        loan.setUser(user);

        return loan;
    }

    // Converte Loan para LoanResponse
    public static LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getLoanInitialDate(),
                loan.getLoanEndDate(),
                loan.getLoanStatus(),
                loan.getExemplary() != null ? loan.getExemplary().getId() : null,
                loan.getUser() != null ? loan.getUser().getId() : null
        );
    }

    // Converte uma lista de Loan para uma lista de LoanResponse
    public static List<LoanResponse> toResponseList(List<Loan> loans) {
        return loans.stream()
                .map(LoanMapper::toResponse)
                .collect(Collectors.toList());
    }
}

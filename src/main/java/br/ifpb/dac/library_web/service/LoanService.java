package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.LoanRequest;
import br.ifpb.dac.library_web.entity.Exemplary;
import br.ifpb.dac.library_web.entity.Loan;
import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.enumeration.LoanStatus;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.repository.ExemplaryRepository;
import br.ifpb.dac.library_web.repository.LoanRepository;
import br.ifpb.dac.library_web.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final ExemplaryRepository exemplaryRepository;
    private final UserRepository userRepository;

    // Cria um novo empréstimo
    @Transactional
    public Loan save(Loan request) {

        // Verifica se o exemplar e o usuário existem
        Exemplary exemplary = exemplaryRepository.findById(request.getExemplary().getId())
                .orElseThrow(() -> new RuntimeException("Exemplary not found with id: " + request.getExemplary().getId()));
        exemplary.setIsActive(false);
        request.setExemplary(exemplary);

        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUser().getId()));
        request.setUser(user);

        return loanRepository.save(request);
    }

    // Busca um empréstimo por ID
    public Loan findById(Long id) {
      return loanRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

    }

    // Busca todos os empréstimos
    public List<Loan> findAll() {
        return loanRepository.findAll();

    }

    // Atualiza um empréstimo
    @Transactional
    public Loan update(Long id, LoanRequest request) {
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        existingLoan.setLoanEndDate(request.getLoanEndDate());
        existingLoan.setLoanStatus(request.getLoanStatus());

        // Atualiza o exemplar e o usuário
        Exemplary exemplary = exemplaryRepository.findById(request.getExemplaryId())
                .orElseThrow(() -> new RuntimeException("Exemplary not found with id: " + request.getExemplaryId()));
        existingLoan.setExemplary(exemplary);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        existingLoan.setUser(user);

        return loanRepository.save(existingLoan);

    }
    public Loan renewLoan(Long id) {

        Loan loan = loanRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Emprestimo nao encontado "+ id));

        if (loan.getRenewalCount() == 2 ){
            throw new ResourceNotFoundException("Só é possível renovar este empréstimo 2 vezes.");
        }


        loan.setRenewalCount(loan.getRenewalCount() + 1);
        loan.setLoanStatus(LoanStatus.RENOVADO);
        loan.setLoanEndDate(loan.getLoanEndDate().plusDays(5));
        return loanRepository.save(loan);

    }

    // Deleta um empréstimo
    @Transactional
    public void delete(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        loanRepository.delete(loan);
    }

    @Transactional
    public void markOverdueLoans() {
        List<Loan> overdueLoans = loanRepository.findByLoanEndDateBeforeAndLoanStatusNot(
                LocalDateTime.now(), LoanStatus.ATRASADO
        );

        for (Loan loan : overdueLoans) {
            loan.setLoanStatus(LoanStatus.ATRASADO);
        }

        loanRepository.saveAll(overdueLoans);
    }

    // Agendamento para verificar e atualizar os empréstimos vencidos todo dia à meia-noite
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleOverdueCheck() {
        markOverdueLoans();
    }
}

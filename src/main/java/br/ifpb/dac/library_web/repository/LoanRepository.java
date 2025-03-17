package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Loan;
import br.ifpb.dac.library_web.enumeration.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByLoanEndDateBeforeAndLoanStatusNot(LocalDateTime now, LoanStatus loanStatus);
}

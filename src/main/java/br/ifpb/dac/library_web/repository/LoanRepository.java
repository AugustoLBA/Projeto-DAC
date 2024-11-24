package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

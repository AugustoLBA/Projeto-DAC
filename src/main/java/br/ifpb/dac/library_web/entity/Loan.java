package br.ifpb.dac.library_web.entity;

import br.ifpb.dac.library_web.enumeration.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Essa entidade representa um emprestimo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_loans")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "loan start date cannot be empt")
    @Column(name = "loan_initial_date")
    private LocalDateTime loanInitialDate;

    @NotNull(message = "loan end date cannot be empty")
    @Column(name = "loan_end_date")
    private LocalDateTime loanEndDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "the loan_status field cannot be empty")
    @Column(name = "loan_status")
    private LoanStatus loanStatus;

    @OneToOne
    @JoinColumn(name = "exemplary_id") // Define a chave estrangeira para Exemplary
    private Exemplary exemplary;

    @ManyToOne
    @JoinColumn(name = "user_id") // Define a chave estrangeira para User
    private User user;

    @PrePersist
    public void prePersist() {
        final LocalDateTime current = LocalDateTime.now();
        loanInitialDate = current;
    }
}

package br.ifpb.dac.library_web.entity;

import br.ifpb.dac.library_web.enumeration.StatusContract;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_contracts")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_start_date")
    private LocalDateTime contractStartDate;

    @Column(name = "contract_end_date")
    private LocalDateTime contractEndDate;

    @NotNull(message = "The status field cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusContract status;

    @NotNull(message = "The value field cannot be empty")
    @Column(name = "value")
    private BigDecimal value;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_library")
    private Library library;

    @OneToMany(mappedBy = "contract")
    private List<ContractClause> clause;

    @PrePersist
    public void prePersist() {
        final LocalDateTime current = LocalDateTime.now();
        contractStartDate = current;
    }
}

package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_contracts_clauses")
public class ContractClause implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The description field cannot be empty")
    @Column(name = "description")
    private String description;

    @NotNull(message = "The fine value field cannot be empty")
    @Column(name = "fine_value")
    private BigDecimal fineValue;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @NotBlank(message = "The field contract cannot be empty")
    private Contract contract;






}

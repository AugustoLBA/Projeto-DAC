package br.ifpb.dac.library_web.entity;

import br.ifpb.dac.library_web.enumeration.StatusSurvey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Survey = Vistoria
 * Essa entidade representa uma vistoria para o livro ao final do emprestimo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_inspections")
public class Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_performed")
    private LocalDateTime date_performed;

    @NotNull(message = "Status cannot be empty")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSurvey statusSurvey;//definir enum para o status da vistoria

    @ManyToOne
    @NotNull(message = "the exemplary_id field cannot be empty")
    @JoinColumn(name = "exemplary_id") // Define a chave estrangeira para Exemplary
    private Exemplary exemplary;
}

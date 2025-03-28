package br.ifpb.dac.library_web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Exemplar= Exemplary
 * Exemplares = Copies(Como sera representado no Banco de Dados
 *
 * Essa Classe representa um exemplar de um livro.
 * Relacionamento:
 * Exemplar e Livro = @ManyToOne(Muitos pra um, Define que cada cópia pertence a um único livro.
 *
 * A anotação @JoinColumn especifica a chave estrangeira (book_id) que conecta as tabelas no banco de dados.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_copies")
@Builder
public class Exemplary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @OneToMany(mappedBy = "exemplary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Survey> surveys; // Lista de vistorias associadas

    @JsonIgnore
    @OneToOne(mappedBy = "exemplary", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Loan loan;

    @Column(name = "is_active")
    private Boolean isActive = true;
}

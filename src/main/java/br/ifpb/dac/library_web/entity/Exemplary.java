package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
public class Exemplary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "number of copies cannot be zero")
    @Column(name = "num_copies")
    private int numberExemplary;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "exemplary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys; // Lista de vistorias associadas

    @OneToOne(mappedBy = "exemplary", cascade = CascadeType.ALL, orphanRemoval = true)
    private Loan loan;
}

package br.ifpb.dac.library_web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Essa classe representa um Livro
 *
 * Relacionamentos:
 *
 * Book e Author = @ManyToMany indica que existe um relacionamento muitos-para-muitos entre as entidades
 * joinColumns = @JoinColumn(name = "book_id") Especifica a coluna na tabela de junção que faz referência à entidade Book,a coluna book_id será usada para associar os registros de Book à tabela de junção.
 * inverseJoinColumns = @JoinColumn(name = "author_id")Define a coluna na tabela de junção que faz referência à entidade Author.
 * A coluna author_id conectará os registros de Author à tabela de junção.
 * Set<Author> authors  Essa coleção representa os autores associados a um livro.
 * @JoinColumn(name = "publisher_id")  Define a coluna da chave estrangeira na tabela Book
 *
 * Book e Publisher = @ManyToOne indica que muitos livros podem ter o mesmo editor (publisher), mas cada livro pertence a apenas um editor.
 * Livro com exemplar = @OneToMany define que um livro pode ter várias cópias.
 * O atributo mappedBy = "book" indica que o relacionamento é mapeado pela propriedade book na entidade Copy.
 * O cascade = CascadeType.ALL propaga operações (persistência, remoção, etc.) realizadas na entidade Book para as entidades relacionadas.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The title field cannot be empty")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "The isbn field cannot be empty")
    @Column(name = "isbn", unique = true)
    private String isbn;

    @NotNull(message = "The year publication field cannot be empty")
    @Column(name = "year_publication")
    private Integer yearPublication;

    @ElementCollection
    @CollectionTable(name = "tb_chapters", joinColumns = @JoinColumn(name = "book_id"))
    @MapKeyColumn(name = "title") // Define o nome da coluna que vai armazenar as chaves do mapa
    //@NotNull(message = "The number chapters field cannot be empty")
    @Column(name = "number_chapters") // Define o nome da coluna que vai armazenar os valores do mapa
    private Map<String, Integer> chapters;

    @NotNull(message = "The field number pages cannot be empty")
    @Column(name = "number_pages")
    private Integer numberPages;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_book_author",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "book_id"),  // Coluna que faz referência à tabela Book
            inverseJoinColumns = @JoinColumn(name = "author_id"))  // Coluna que faz referência à tabela Author
        private List<Author> authors;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exemplary> numberCopies;

    @Column(name = "gender")
    private String gender;

}

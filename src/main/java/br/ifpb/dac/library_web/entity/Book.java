package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @CollectionTable(name = "chapters", joinColumns = @JoinColumn(name = "book_id"))
    @NotBlank(message = "The title field cannot be empty")
    @MapKeyColumn(name = "title") // Define o nome da coluna que vai armazenar as chaves do mapa
    @NotNull(message = "The number chapters field cannot be empty")
    @Column(name = "number_chapters") // Define o nome da coluna que vai armazenar os valores do mapa
    private Map<String, Integer> chapters;

    @NotNull(message = "The field number pages cannot be empty")
    @Column(name = "number_pages")
    private Integer numberPages;

    @ManyToOne
    @JoinColumn(name = "publisher_id")  // Define a coluna da chave estrangeira na tabela Book
    private Publisher publisher;


    @ManyToMany
    @JoinTable(name = "book_author",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "book_id"),  // Coluna que faz referência à tabela Book
            inverseJoinColumns = @JoinColumn(name = "author_id"))  // Coluna que faz referência à tabela Author
    private Set<Author> authors;

}

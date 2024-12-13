package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_publishers")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The name field cannot be empty")
    @Column(name = "name")
    private String name;

    // Relacionamento OneToMany: Uma editora pode ter vários livros.
    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL, orphanRemoval = true)  // mappedBy define o lado inverso da relação
    private List<Book> books;
}

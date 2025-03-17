package br.ifpb.dac.library_web.entity;

import br.ifpb.dac.library_web.enumeration.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Type user cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "type_user")
    private TypeUser type;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Adress adress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans; // Lista de empréstimos associados

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Contract contract;
}

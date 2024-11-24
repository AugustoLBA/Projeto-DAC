package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_libraries")
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Column(name = "name")
    private String name;


    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL)
    private Adress adress;

    @OneToOne(mappedBy = "library",cascade = CascadeType.ALL)
    private Contract contract;



}

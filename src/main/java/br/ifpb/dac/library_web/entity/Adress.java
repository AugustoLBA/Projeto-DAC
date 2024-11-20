package br.ifpb.dac.library_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "adresses")
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The street field cannot be empty")
    @Column(name = "street")
    private String street;

    @NotBlank(message = "The city field cannot be empty")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "The state field cannot be empty")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "The zip code field cannot be empty")
    @Column(name = "zip_code")
    private String zipCode;

    @NotBlank(message = "The neighborhood code field cannot be empty")
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotBlank(message = "The country code field cannot be empty")
    @Column(name = "country")
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.simplesdental.desafio.profissionais.api.model;

import com.simplesdental.desafio.profissionais.api.model.enums.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profissionais")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cargo")
    private CargoEnum cargo;
    @Column(name = "nascimento")
    private LocalDate nascimento;
    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}

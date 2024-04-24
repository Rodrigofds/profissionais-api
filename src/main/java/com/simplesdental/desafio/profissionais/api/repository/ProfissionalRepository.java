package com.simplesdental.desafio.profissionais.api.repository;

import com.simplesdental.desafio.profissionais.api.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    @Query("SELECT p FROM Profissional p WHERE LOWER(p.nome) LIKE LOWER(concat('%', :filtro, '%')) OR " +
            "LOWER(p.cargo) LIKE LOWER(concat('%', :filtro, '%')) OR " +
            "LOWER(p.nascimento) LIKE LOWER(concat('%', :filtro, '%'))")
    List<Profissional> findByFiltro(String filtro);
}

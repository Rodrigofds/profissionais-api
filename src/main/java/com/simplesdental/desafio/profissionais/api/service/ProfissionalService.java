package com.simplesdental.desafio.profissionais.api.service;

import com.simplesdental.desafio.profissionais.api.model.Profissional;

import java.util.List;
import java.util.Optional;

public interface ProfissionalService {

    List<Profissional> listarProfissionais(String filtro, List<String> fields);

    Optional<Profissional> buscarPorId(Long id);

    Profissional salvar(Profissional profissional);

    Profissional atualizar(Profissional profissional);

    void excluir(Long id);
}

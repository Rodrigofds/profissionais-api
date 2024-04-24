package com.simplesdental.desafio.profissionais.api.service;

import com.simplesdental.desafio.profissionais.api.model.Profissional;

import java.util.List;

public interface ProfissionalService {

    List<Profissional> buscarProfissionais(String filtro);

    Profissional buscarPorId(Long id);

    Profissional salvar(Profissional profissional);

    String atualizar(Profissional profissional);

    String excluir(Long id);
}

package com.simplesdental.desafio.profissionais.api.service;

import com.simplesdental.desafio.profissionais.api.model.Profissional;
import com.simplesdental.desafio.profissionais.api.repository.ProfissionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfissionalServiceImpl implements ProfissionalService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfissionalServiceImpl.class);

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalServiceImpl(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    public List<Profissional> listarProfissionais(String filtro, List<String> fields) {
        // TODO -> Chama a repository

        List<Profissional> profissionais = profissionalRepository.findByFiltro(filtro);

        if (Objects.nonNull(profissionais) && Objects.nonNull(fields) && !fields.isEmpty()) {
           return verificaCamposInformados(profissionais, fields);
        }

        return profissionais;
    }

    @Override
    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    @Override
    public Profissional salvar(Profissional profissional) {
        return profissionalRepository.saveAndFlush(profissional);
    }

    @Override
    public Profissional atualizar(Profissional profissional) {
        return profissionalRepository.saveAndFlush(profissional);
    }

    @Override
    public void excluir(Long id) {
        profissionalRepository.deleteById(id);
    }

    private List<Profissional> verificaCamposInformados(List<Profissional> profissionais, List<String> fields) {
        List<Profissional> filtrados = new ArrayList<>();

        for (Profissional profissional : profissionais) {
            try {
                Profissional profissionalFiltrado = new Profissional();
                for (String fieldName : fields) {
                    Field field = Profissional.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object value = field.get(profissional);
                    field.set(profissionalFiltrado, value);
                }
                filtrados.add(profissionalFiltrado);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error("Erro filtrar profissionais: {}", e.getMessage());
            }
        }

        return filtrados;
    }
}

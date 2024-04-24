package com.simplesdental.desafio.profissionais.api.controller;

import com.simplesdental.desafio.profissionais.api.model.Profissional;
import com.simplesdental.desafio.profissionais.api.service.ProfissionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfissionalController.class);
    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> buscarProfissionais(@RequestParam(required = false) String filtro) {
        try {
            List<Profissional> profissionais = profissionalService.buscarProfissionais(filtro);

            if (Objects.nonNull(profissionais)) {
                return ResponseEntity.status(HttpStatus.OK).body(profissionais);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            LOGGER.error("Erro interno ao buscar profissionais: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (Objects.nonNull(profissional)) {
            return ResponseEntity.status(HttpStatus.OK).body(profissional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody @Valid Profissional profissional) {
        Profissional novoProfissional = profissionalService.salvar(profissional);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Sucesso profissional com id " + novoProfissional.getId() + " cadastrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Profissional profissional) {
        Profissional profissionalDb = profissionalService.buscarPorId(id);

        if (Objects.nonNull(profissionalDb)){
            profissionalService.atualizar(profissional);
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso profissional alterado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        Profissional profissionalDb = profissionalService.buscarPorId(id);

        if (Objects.nonNull(profissionalDb)){
            profissionalService.excluir(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucesso profissional excluído com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


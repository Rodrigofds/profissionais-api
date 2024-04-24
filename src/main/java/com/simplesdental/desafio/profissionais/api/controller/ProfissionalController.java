package com.simplesdental.desafio.profissionais.api.controller;

import com.simplesdental.desafio.profissionais.api.model.Profissional;
import com.simplesdental.desafio.profissionais.api.service.ProfissionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfissionalController.class);
    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> buscarProfissionais(@RequestParam(required = false) String filtro,
                                                                  @RequestParam(required = false) List<String> fields) {
        try {
            List<Profissional> profissionais = profissionalService.listarProfissionais(filtro, fields);

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
    public ResponseEntity<Optional<Profissional>> buscarPorId(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalService.buscarPorId(id);
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
    public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody Profissional profissional) {
        Optional<Profissional> profissionalDb = profissionalService.buscarPorId(id);

        if (Objects.nonNull(profissionalDb)){
            Profissional profissionalAtualizado = profissionalService.atualizar(profissional);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(profissionalAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        Optional<Profissional> profissionalDb = profissionalService.buscarPorId(id);

        if (Objects.nonNull(profissionalDb)){
            profissionalService.excluir(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucesso profissional excluído");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


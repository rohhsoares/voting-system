package com.robsoares.voting.controller;

import com.robsoares.voting.model.Option;
import com.robsoares.voting.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService service;

    public OptionController(OptionService service) {
        this.service = service;
    }

    // Criar nova opção
    @PostMapping
    public ResponseEntity<Option> createOption(
            @RequestParam Long pollId,
            @RequestParam String description) {

        Option novaOption = service.createOption(pollId, description);

        URI uri = URI.create("/options/" + novaOption.getId());

        return ResponseEntity.created(uri).body(novaOption);
    }

    // Buscar opção por ID
    @GetMapping("/{id}")
    public ResponseEntity<Option> findById(@PathVariable Long id) {
        Option option = service.findById(id);
        return ResponseEntity.ok(option);
    }

    // Listar opções de uma votação
    @GetMapping("/poll/{pollId}")
    public ResponseEntity<List<Option>> listOptionsByPoll(
            @PathVariable Long pollId) {

        List<Option> lista = service.listOptionsByPoll(pollId);
        return ResponseEntity.ok(lista);
    }
}
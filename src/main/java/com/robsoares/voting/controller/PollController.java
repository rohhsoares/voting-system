package com.robsoares.voting.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoares.voting.dto.PollResultDTO;
import com.robsoares.voting.model.Poll;
import com.robsoares.voting.service.PollService;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService service;

    // 🔹 Criar votação
    @PostMapping
    public ResponseEntity<Poll> create(@RequestBody Poll poll) {
        Poll novaPoll = service.insert(poll);
        URI uri = URI.create("/polls/" + novaPoll.getId());
        return ResponseEntity.created(uri).body(novaPoll);
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Poll> findById(@PathVariable Long id) {
        Poll poll = service.findById(id);
        return ResponseEntity.ok(poll);
    }

    // 🔹 Listar todas
    @GetMapping
    public ResponseEntity<List<Poll>> findAll() {
        List<Poll> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    // 🔹 Encerrar votação
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Poll> close(@PathVariable Long id) {
        Poll poll = service.closeVoting(id);
        return ResponseEntity.ok(poll);
    }
    
    @GetMapping("/{pollId}/results")
    public ResponseEntity<PollResultDTO> getResults(@PathVariable Long pollId) {
        return ResponseEntity.ok(service.getResults(pollId));
    }
}
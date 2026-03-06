package com.robsoares.voting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robsoares.voting.model.Poll;
import com.robsoares.voting.model.StatusPoll;
import com.robsoares.voting.repository.PollRepository;
import com.robsoares.voting.service.exception.ResourceNotFoundException;

@Service
public class PollService {

    @Autowired
    private PollRepository repository;

    // Criar votação
    public Poll insert(Poll obj) {
        obj.setStatusPoll(StatusPoll.ATIVA);
        return repository.save(obj);
    }

    // Buscar votação por ID
    public Poll findById(Long id) {
        Optional<Poll> poll = repository.findById(id);
        return poll.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Listar todas
    public List<Poll> findAll() {
        return repository.findAll();
    }

    // Encerrar votação
    public Poll closeVoting(Long id) {

        Poll poll = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (poll.getStatusPoll() == StatusPoll.ENCERRADA) {
            throw new IllegalStateException("Voting is already closed");
        }

        poll.setStatusPoll(StatusPoll.ENCERRADA);

        return repository.save(poll);
    }

    // Verificar se a votação está ativa
    public void checkActive(Poll poll) {

        if (poll.getStatusPoll() != StatusPoll.ATIVA) {
            throw new IllegalStateException("Voting is not active");
        }

    }

    // Verificar período da votação
    public void checkPeriod(Poll poll) {

        LocalDate today = LocalDate.now();

        if (poll.getStartDate() != null && today.isBefore(poll.getStartDate())) {
            throw new IllegalStateException("Voting has not started yet");
        }

        if (poll.getEndDate() != null && today.isAfter(poll.getEndDate())) {
            throw new IllegalStateException("Voting has already ended");
        }
    }
}
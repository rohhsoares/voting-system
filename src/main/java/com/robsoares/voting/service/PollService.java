package com.robsoares.voting.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robsoares.voting.dto.OptionResultDTO;
import com.robsoares.voting.dto.PollResultDTO;
import com.robsoares.voting.model.Poll;
import com.robsoares.voting.model.StatusPoll;
import com.robsoares.voting.repository.PollRepository;
import com.robsoares.voting.repository.VoteRepository;
import com.robsoares.voting.service.exception.ResourceNotFoundException;

@Service
public class PollService {

    @Autowired
    private PollRepository repository;

    @Autowired
    private VoteRepository voteRepository;

    // Criar votação
    public Poll insert(Poll obj) {

        obj.setStatus(StatusPoll.ATIVA);

        if (obj.getStartTime() == null) {
            obj.setStartTime(LocalDateTime.now());
        }

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

        if (poll.getStatus() == StatusPoll.ENCERRADA) {
            throw new IllegalStateException("Voting is already closed");
        }

        poll.setStatus(StatusPoll.ENCERRADA);

        return repository.save(poll);
    }

    // Verificar se votação está aberta
    public void checkActive(Poll poll) {

        if (poll.getStatus() != StatusPoll.ATIVA) {
            throw new IllegalStateException("Voting is not open");
        }

    }

    // Verificar período da votação
    public void checkPeriod(Poll poll) {

        LocalDateTime now = LocalDateTime.now();

        if (poll.getStartTime() != null && now.isBefore(poll.getStartTime())) {
            throw new IllegalStateException("Voting has not started yet");
        }

        if (poll.getEndTime() != null && now.isAfter(poll.getEndTime())) {
            throw new IllegalStateException("Voting has already ended");
        }
    }

    // Resultado da votação
    public PollResultDTO getResults(Long pollId) {

        Poll poll = repository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException(pollId));

        List<OptionResultDTO> results = poll.getOptions().stream()
                .map(option -> {
                    long count = voteRepository.countByOption(option);
                    return new OptionResultDTO(option.getDescription(), count);
                })
                .toList();

        return new PollResultDTO(poll.getTitle(), results);
    }
}
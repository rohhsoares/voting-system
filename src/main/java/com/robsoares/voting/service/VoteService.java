package com.robsoares.voting.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robsoares.voting.model.Option;
import com.robsoares.voting.model.Poll;
import com.robsoares.voting.model.Vote;
import com.robsoares.voting.repository.OptionRepository;
import com.robsoares.voting.repository.PollRepository;
import com.robsoares.voting.repository.VoteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;

    public VoteService(VoteRepository voteRepository,
                       PollRepository pollRepository,
                       OptionRepository optionRepository) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public Vote registerVote(Long pollId, Long optionId, String cpf) {

        // verifica se a votação existe
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new EntityNotFoundException("Voting results not found"));

        // verifica se a opção existe
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException("Option not found"));

        // verifica se a opção pertence à votação
        if (!option.getPoll().getId().equals(pollId)) {
            throw new IllegalArgumentException("This option does not belong to this vote");
        }

        // verifica se CPF já votou nessa votação
        boolean alreadyVoted = voteRepository.existsByCpfAndOptionPollId(cpf, pollId);

        if (alreadyVoted) {
            throw new IllegalArgumentException("This CPF has already voted in this election");
        }

        // cria o voto
        Vote vote = new Vote();
        vote.setCpf(cpf);
        vote.setVotingDate(new Date());
        vote.setPoll(poll);
        vote.setOption(option);

        return voteRepository.save(vote);
    }
}
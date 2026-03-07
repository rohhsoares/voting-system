package com.robsoares.voting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoares.voting.model.Vote;
import com.robsoares.voting.service.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Vote> vote(
            @RequestBody Long pollId,
            @RequestBody Long optionId,
            @RequestBody String cpf) {

        Vote vote = voteService.registerVote(pollId, optionId, cpf);

        return ResponseEntity.ok(vote);
    }
}
package com.robsoares.voting.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Para o model.addAttribute	
// Para @GetMapping, @PostMapping
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.robsoares.voting.dto.VoteForm;
import com.robsoares.voting.model.Poll;
import com.robsoares.voting.service.PollService;
import com.robsoares.voting.service.VoteService;

@Controller
public class WebVotingController {

    @Autowired
    private PollService pollService;

    @Autowired
    private VoteService voteService;

    @GetMapping("/web/votar/{pollId}")
    public String paginaVotacao(@PathVariable Long pollId, Model model) {
        Poll poll = pollService.findById(pollId);
        model.addAttribute("poll", poll);
        model.addAttribute("voteForm", new VoteForm());
        return "votar"; // aponta para src/main/resources/templates/votar.html
    }

    @PostMapping("/web/votar")
    public String registrarVoto(VoteForm form) {
        voteService.registerVote(form.getPollId(), form.getOptionId(), form.getCpf());
        return "redirect:/web/resultado/" + form.getPollId();
    }
}
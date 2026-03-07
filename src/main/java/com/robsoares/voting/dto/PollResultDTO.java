package com.robsoares.voting.dto;

import java.util.List;

public class PollResultDTO {
	
	private String poll;
    private List<OptionResultDTO> results;

    public PollResultDTO(String poll, List<OptionResultDTO> results) {
        this.poll = poll;
        this.results = results;
    }

    public String getPoll() {
        return poll;
    }

    public List<OptionResultDTO> getResults() {
        return results;
    }
}

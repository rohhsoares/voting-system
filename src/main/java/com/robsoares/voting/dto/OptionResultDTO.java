package com.robsoares.voting.dto;

public class OptionResultDTO {

	private String option;
    private long votes;

    public OptionResultDTO(String option, long votes) {
        this.option = option;
        this.votes = votes;
    }

    public String getOption() {
        return option;
    }

    public long getVotes() {
        return votes;
    }
    
}

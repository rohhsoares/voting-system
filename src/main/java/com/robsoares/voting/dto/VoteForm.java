package com.robsoares.voting.dto;

public class VoteForm {
    private Long pollId;
    private Long optionId;
    private String cpf;

    public VoteForm() {}

    // Getters e Setters (pode usar o "Source -> Generate Getters and Setters" do seu STS)
    public Long getPollId() { return pollId; }
    public void setPollId(Long pollId) { this.pollId = pollId; }
    public Long getOptionId() { return optionId; }
    public void setOptionId(Long optionId) { this.optionId = optionId; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}
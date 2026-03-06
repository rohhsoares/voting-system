package com.robsoares.voting.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vote")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(name = "voting_date")
    private Date votingDate;

    @ManyToOne
    @JoinColumn(name = "fk_poll", nullable = false)
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "fk_option", nullable = false)
    private Option option;

    public Vote() {
    }

    public Vote(Long id, String cpf, Date votingDate, Poll poll, Option option) {
        this.id = id;
        this.cpf = cpf;
        this.votingDate = votingDate;
        this.poll = poll;
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getVotingDate() {
        return votingDate;
    }

    public Poll getPoll() {
        return poll;
    }

    public Option getOption() {
        return option;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setVotingDate(Date votingDate) {
        this.votingDate = votingDate;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vote other = (Vote) obj;
        return Objects.equals(id, other.id);
    }
}
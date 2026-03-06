package com.robsoares.voting.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_poll")
public class Poll implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private StatusPoll statusPoll;

    private LocalDate startDate;

    private LocalDate endDate;

    public Poll() {
    }

    public Poll(Long id, String title, StatusPoll statusPoll, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.statusPoll = statusPoll;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public StatusPoll getStatusPoll() {
        return statusPoll;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatusPoll(StatusPoll statusPoll) {
        this.statusPoll = statusPoll;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Poll)) return false;
        Poll other = (Poll) obj;
        return Objects.equals(id, other.id);
    }
}
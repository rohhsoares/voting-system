package com.robsoares.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.robsoares.voting.model.Option;
import com.robsoares.voting.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

    boolean existsByCpfAndOptionPollId(String cpf, Long pollId);

    @Query("SELECT v.option.id, COUNT(v) FROM Vote v WHERE v.option.poll.id = :pollId GROUP BY v.option.id")
    List<Object[]> countVotesByPoll(@Param("pollId") Long pollId);

    long countByOption(Option option);
}
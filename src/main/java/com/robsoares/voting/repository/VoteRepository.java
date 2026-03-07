package com.robsoares.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robsoares.voting.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

	boolean existsByCpfAndPollId(String cpf, Long pollId);

}

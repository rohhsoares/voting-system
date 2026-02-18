package com.robsoares.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robsoares.voting.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>{

}

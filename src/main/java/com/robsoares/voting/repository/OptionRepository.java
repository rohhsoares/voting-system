package com.robsoares.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robsoares.voting.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long>{

	List<Option> findByPollId(Long pollId);

}

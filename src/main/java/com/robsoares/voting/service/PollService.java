package com.robsoares.voting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robsoares.voting.model.Poll;
import com.robsoares.voting.model.StatusPoll;
import com.robsoares.voting.repository.PollRepository;
import com.robsoares.voting.service.exception.ResourceNotFoundException;

@Service
public class PollService {

	@Autowired
	private PollRepository repository;
	
	public Poll insert(Poll obj) {
		return repository.save(obj);
	}
	
	public Poll findById(Long id) {
		Optional<Poll> poll = repository.findById(id);
		return poll.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Poll> findAll(){
		return repository.findAll();
	}
	
	public void closeVoting(Long id) {
		Poll poll = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		if (poll.getStatus() == StatusPoll.ENCERRADA) {
			throw new IllegalStateException("Voting is now closed");
		}
		
		poll.setStatus(StatusPoll.ENCERRADA);
	}
	
	public void checkActive(Poll poll) {
		if (poll.getStatus() != StatusPoll.ATIVA) {
			throw new IllegalStateException("Voting is not active");
		}
	}
	
	public void checkPeriod(Poll poll) {
		LocalDate today = LocalDate.now();
		
		if (poll.getStartDate() != null && today.isBefore(poll.getStartDate())) {
			throw new IllegalStateException("Voting is not start");
		}
		
		if (poll.getEndDate() != null && today.isBefore(poll.getEndDate()) ) {
			throw new IllegalStateException("Voting has already ended");
		}
	}
}







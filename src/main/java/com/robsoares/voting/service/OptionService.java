 package com.robsoares.voting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robsoares.voting.model.Option;
import com.robsoares.voting.model.Poll;
import com.robsoares.voting.repository.OptionRepository;
import com.robsoares.voting.repository.PollRepository;
import com.robsoares.voting.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OptionService {

	@Autowired
	private OptionRepository repository;
	
	@Autowired
	private PollRepository pollRepository;
	
	public Option createOption(Long pollId, String description) {
		Poll poll = pollRepository.findById(pollId)
				.orElseThrow(() -> 
				new EntityNotFoundException("Voting not found"));
		
		Option option = new Option();
		option.setDescription(description);
		option.setPoll(poll);
		
		return repository.save(option);
	}

	public List<Option> listOptionsByPoll(Long pollId){
		if(!pollRepository.existsById(pollId)) {
			throw new EntityNotFoundException("Voting not found");
		}
		
		return repository.findByPollId(pollId);
	}
	
	public Option findById(Long id) {
		Optional<Option> option = repository.findById(id);
		return option.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public void validateOptionBelongsToPoll(Long optionId, Long pollId) {
		
		Option option = findById(optionId);
		
		if (!option.getPoll().getId().equals(pollId)) {
			throw new IllegalArgumentException("This option does not belong to this vote.");
		}
		
	}
}







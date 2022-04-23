package com.pi.accountmanagement.services;

import com.pi.accountmanagement.domains.People;
import com.pi.accountmanagement.repositories.PeopleRepository;
import org.springframework.stereotype.Service;


@Service
public class PeopleService {
	
final PeopleRepository peopleRepository;
	
	public PeopleService(PeopleRepository repository) {
		this.peopleRepository = repository;
	}


	public People getPeopleById(Long id) {
		return this.peopleRepository.getById(id);
	}
}

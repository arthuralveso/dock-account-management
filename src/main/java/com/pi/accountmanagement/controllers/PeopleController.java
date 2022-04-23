package com.pi.accountmanagement.controllers;

import com.pi.accountmanagement.services.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoa")
public class PeopleController {

	private PeopleService peopleService;
	

}

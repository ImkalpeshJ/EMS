package com.employee.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.entity.Country;
import com.employee.app.exception.ResourceAlreadyExistsException;
import com.employee.app.service.CountryService;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	CountryService service;

	@GetMapping(value = "/get-all-countries")
	public ResponseEntity<List<Country>> getAllCountry() {
		List<Country> countries = service.getAllCountry();
		if (countries.isEmpty()) {
			return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(countries);
	}

	@PostMapping(value = "/post-country")
	public ResponseEntity<String> postCountry(@RequestBody Country country) {
		boolean isAdded = service.postCountry(country);
		if (isAdded) {
			return new ResponseEntity<String>("Country with id " + country.getCid() + " is added!", HttpStatus.OK);
		}
		throw new ResourceAlreadyExistsException("Country with name " + country.getCname() + " already exists!");
	}

}

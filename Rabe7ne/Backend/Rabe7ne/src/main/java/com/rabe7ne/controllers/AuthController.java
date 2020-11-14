package com.rabe7ne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabe7ne.models.AbstractModel;
import com.rabe7ne.properties.ApplicationProperties;
import com.rabe7ne.services.AppService;

@RestController
@CrossOrigin(origins = ApplicationProperties.ORIGIN)
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/getCountries", method = RequestMethod.GET)
	public ResponseEntity<Object> getCountries() {
		return new ResponseEntity<Object>(appService.getCountries(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public ResponseEntity<Object> checkPhone(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.checkPhone(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.login(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/loginPortal", method = RequestMethod.POST)
	public ResponseEntity<Object> loginPortal(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.loginPortal(model), HttpStatus.OK);
	}

}

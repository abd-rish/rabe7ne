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
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/getUserData", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserData() {
		return new ResponseEntity<Object>(appService.getUserData(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/editUserData", method = RequestMethod.POST)
	public ResponseEntity<Object> editUserData(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.editUserData(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTodayQues", method = RequestMethod.GET)
	public ResponseEntity<Object> getTodayQues() {
		return new ResponseEntity<Object>(appService.getTodayQues(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ansQues", method = RequestMethod.POST)
	public ResponseEntity<Object> ansQues(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.ansQues(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNotifications", method = RequestMethod.POST)
	public ResponseEntity<Object> getNotifications(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.getNotifications(model), HttpStatus.OK);
	}
	
}

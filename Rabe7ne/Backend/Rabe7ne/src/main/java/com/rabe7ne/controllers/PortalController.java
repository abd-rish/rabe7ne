package com.rabe7ne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabe7ne.models.AbstractModel;
import com.rabe7ne.properties.ApplicationProperties;
import com.rabe7ne.services.AppService;

@RestController
@CrossOrigin(origins = ApplicationProperties.ORIGIN)
@RequestMapping("/portal")
public class PortalController {
	
	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/createPartner", method = RequestMethod.POST)
	public ResponseEntity<Object> createPartner(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.createPartner(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/editPartner", method = RequestMethod.POST)
	public ResponseEntity<Object> editPartner(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.editPartner(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public ResponseEntity<Object> addQuestion(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.addQuestion(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/editQuestion", method = RequestMethod.POST)
	public ResponseEntity<Object> editQuestion(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.editQuestion(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteQuestion(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.deleteQuestion(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPartners", method = RequestMethod.POST)
	public ResponseEntity<Object> getPartners(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.getPartners(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPartnerData", method = RequestMethod.GET)
	public ResponseEntity<Object> getPartnerData(@RequestParam(required = false) Long partnerId) {
		return new ResponseEntity<Object>(appService.getPartnerData(partnerId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getQuestions", method = RequestMethod.POST)
	public ResponseEntity<Object> getQuestions(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.getQuestions(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllPartners", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllPartners() {
		return new ResponseEntity<Object>(appService.getAllPartners(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getQuestionData", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionData(@RequestParam Long id) {
		return new ResponseEntity<Object>(appService.getQuestionData(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserPartnerPoints", method = RequestMethod.POST)
	public ResponseEntity<Object> getUserPartnerPoints(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.getUserPartnerPoints(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserPoints", method = RequestMethod.POST)
	public ResponseEntity<Object> getUserPoints(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.getUserPoints(model), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addUserNotification", method = RequestMethod.POST)
	public ResponseEntity<Object> addUserNotification(@RequestBody AbstractModel model) {
		return new ResponseEntity<Object>(appService.addUserNotification(model), HttpStatus.OK);
	}

}

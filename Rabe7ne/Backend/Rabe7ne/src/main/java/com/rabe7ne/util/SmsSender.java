package com.rabe7ne.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabe7ne.properties.SmsProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsSender {
	
	@Autowired
	private SmsProperties smsProperties;
	
	public boolean sendSmsTextMessage(String to, String textMessage) {
		try {
		    Twilio.init(smsProperties.getAccountSid(), smsProperties.getAuthToken());
	    	Message message = Message
    	               .creator(new PhoneNumber(to),
	                           new PhoneNumber(smsProperties.getFrom()),
	                           textMessage)
	                   .create();
//			System.out.println(message.getSid());
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}

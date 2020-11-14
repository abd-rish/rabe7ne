package com.rabe7ne.util;

public interface SystemConstants {
	
	public interface Regex {
		String NAME = "^[A-Za-z]{1,100}$";
		String EMAIL = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		String PHONE_NUMBER = "^\\+?[0-9]{1,19}$";
	}
	
	public interface UserActiveFlags {
		Character ACTIVE = 'Y';
		Character INACTIVE = 'N';
		Character PENDING = 'P';
	}
	
	public interface NextValues {
		String USER_ID = "USER_ID";
		String PARTNER_ID = "PARTNER_ID";
		String QUESTION_ID = "QUESTION_ID";
		String USER_NOTIFICATION_ID = "USER_NOTIFICATION_ID";
	}
	
	public interface ConfirmationMsg {
		String CONFIRMATION_CODE = "confirmationCode";
		String CONFIRMATION_TRY_NUMBER = "confirmationTryNumber";
	}

}

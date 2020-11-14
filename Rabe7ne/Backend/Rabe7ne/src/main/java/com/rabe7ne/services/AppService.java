package com.rabe7ne.services;

import com.rabe7ne.models.AbstractModel;

public interface AppService {
	
	public Object getCountries();
	
	public Object checkPhone(AbstractModel model);
	
	public Object login(AbstractModel model);
	
	public Object getUserData();
	
	public Object editUserData(AbstractModel model);
	
	public Object getTodayQues();
	
	public Object ansQues(AbstractModel model);
	
	public Object loginPortal(AbstractModel model);
	
	public Object createPartner(AbstractModel model);

	public Object editPartner(AbstractModel model);
	
	public Object addQuestion(AbstractModel model);

	public Object editQuestion(AbstractModel model);
	
	public Object deleteQuestion(AbstractModel model);
	
	public Object getPartners(AbstractModel model);
	
	public Object getPartnerData(Long partnerId);
	
	public Object getQuestions(AbstractModel model);
	
	public Object getAllPartners();
	
	public Object getQuestionData(Long id);
	
	public Object getUserPartnerPoints(AbstractModel model);
	
	public Object getUserPoints(AbstractModel model);
	
	public Object getNotifications(AbstractModel model);
	
	public Object addUserNotification(AbstractModel model);

}

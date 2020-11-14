package com.rabe7ne.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabe7ne.dao.AppDAO;
import com.rabe7ne.models.AbstractModel;
import com.rabe7ne.pojos.Countries;
import com.rabe7ne.pojos.Partners;
import com.rabe7ne.pojos.PortalUsers;
import com.rabe7ne.pojos.Questions;
import com.rabe7ne.pojos.UserAnss;
import com.rabe7ne.pojos.UserAnssId;
import com.rabe7ne.pojos.UserNotifications;
import com.rabe7ne.pojos.Users;
import com.rabe7ne.pojos.VPartners;
import com.rabe7ne.pojos.VQuestions;
import com.rabe7ne.pojos.VUserPartnerPoints;
import com.rabe7ne.pojos.VUserPoints;
import com.rabe7ne.services.AppService;
import com.rabe7ne.util.CharInterval;
import com.rabe7ne.util.Helper;
import com.rabe7ne.util.QueryParam;
import com.rabe7ne.util.QueryParams;
import com.rabe7ne.util.Result;
import com.rabe7ne.util.SessionVariables;
import com.rabe7ne.util.SmsSender;
import com.rabe7ne.util.SystemConstants;

@Component
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppDAO appDAO;
	
	@Autowired
	private SmsSender smsSender;
	
	@Autowired
	private SessionVariables sessionVariables;
	
	@Override
	public Object getCountries() {
		List<Countries> countries = appDAO.getCountries();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(countries != null && !countries.isEmpty()) {
			Collections.sort(countries, new Comparator<Countries>() {
				public int compare(Countries c1, Countries c2) {
					return c1.getArName().compareTo(c2.getArName());
				};
			});
			Map<String, Object> map;
			for(Countries country : countries ) {
				map = new HashMap<String, Object>();
				map.put("code", country.getCode());
				map.put("name", country.getName());
				map.put("arName", country.getArName());
				map.put("callingCode", country.getCallingCode());
				result.add(map);
			}
		}
		return result;
	}
	
	@Override
	public Object checkPhone(AbstractModel model) {
		String countryCode = model.getString("countryCode");
		String phone = model.getString("phone");
		if(countryCode == null || countryCode.isEmpty() || phone == null || phone.isEmpty())
			return Helper.error();
		
		Countries country = appDAO.getCountry(countryCode);
		if(country == null)
			return Helper.error();
		
		Boolean newFlag = false;
		Boolean msgSent = false;
		boolean addPoints = false;
		
		Users user = appDAO.getUser(countryCode, phone);
		if(user == null) {
			String firstName = model.getString("firstName");
			String lastName = model.getString("lastName");
			if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty())
				newFlag = true;
			else {
				user = new Users();
				user.setUserId(appDAO.getNextValue(SystemConstants.NextValues.USER_ID));
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setSpecCode(countryCode + Helper.createRandomCode(3, new CharInterval('A', 'Z')) + user.getUserId());
				user.setPoints(0L);
				user.setCountryCode(countryCode);
				user.setPhone(phone);
				user.setPass(UUID.randomUUID().toString() + new Date().getTime());
				appDAO.savePojo(user);
				addPoints = true;
			}
		}
		
		if(!newFlag) {
			String confirmCode = Helper.createRandomCode(6, new CharInterval('0', '9'));
			if(!smsSender.sendSmsTextMessage("+" + country.getCallingCode() + phone, "يرجي ادخال هذا الرمز للدخول الى حسابك لدى ربحني: " + confirmCode)) {
				AbstractModel result = new AbstractModel();
				result.set("invalidPhone", true);
				return result;
			}
			msgSent = true;
			if(addPoints) {
				String specCode = model.getString("specCode");
				if(specCode != null && !specCode.isEmpty()) {
				    Users fUser = appDAO.getUser(specCode);
				    if(fUser != null) {
				    	fUser.setPoints(fUser.getPoints() + 1L);
				    	appDAO.updatePojo(fUser);
				    }
				}
			}
			user.setConfirmCode(confirmCode);
			appDAO.updatePojo(user);
		}
		
		AbstractModel result = new AbstractModel();
		result.set("newFlag", newFlag);
		result.set("msgSent", msgSent);
		return result;
	}
	
	@Override
	public Object login(AbstractModel model) {
		String countryCode = model.getString("countryCode");
		String phone = model.getString("phone");
		String confirmCode = model.getString("confirmCode");
		if(countryCode == null || countryCode.isEmpty() || phone == null || phone.isEmpty() || confirmCode == null || confirmCode.isEmpty())
			return Helper.error();
		
		model.clear();
		
		Users user = appDAO.getUser(countryCode, phone);
		if(user != null && user.getConfirmCode() != null && user.getConfirmCode().equals(confirmCode)) {
			user.setConfirmCode(null);
			appDAO.updatePojo(user);
			String auth = countryCode + ":" + phone + ":" + user.getPass();
			auth = Helper.encodeBase64(auth);
			model.set("auth", auth);
		}
		
		return model;
	}
	
	@Override
	public Object getUserData() {
		Users user = sessionVariables.getUser();
		AbstractModel model = new AbstractModel();
		model.set("firstName", user.getFirstName());
		model.set("lastName", user.getLastName());
		model.set("specCode", user.getSpecCode());
		model.set("points", user.getPoints());
		return model;
	}
	
	@Override
	public Object editUserData(AbstractModel model) {
		String firstName = model.getString("firstName");
		String lastName = model.getString("lastName");
		if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty())
			return Helper.error();
		
		Users user = sessionVariables.getUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		appDAO.updatePojo(user);
		
		return model;
	}
	
	@Override
	public Object getTodayQues() {
		Questions question = appDAO.getTodayQues(sessionVariables.getUser().getCountryCode());
		AbstractModel model = new AbstractModel();
		if(question != null) {
			model.set("id", question.getId());
			model.set("description", question.getDescription());
			model.set("ans1", question.getAns1());
			model.set("ans2", question.getAns2());
			model.set("ans3", question.getAns3());
			model.set("ans4", question.getAns4());
			UserAnss userAnss = appDAO.getUserAnss(sessionVariables.getUser().getUserId(), question.getId());
			model.set("userAns", userAnss == null ? null : userAnss.getAns());
			model.set("correctFlag", userAnss == null ? null : userAnss.getAns().equals(question.getCorrectAns()));
			model.set("points", appDAO.getUserAnssCorrectCount(sessionVariables.getUser().getUserId(), question.getPartnerId()));
			model.set("img64", appDAO.getPartner(question.getPartnerId()).getImg());
		}
		return model;
	}
	
	@Override
	public Object ansQues(AbstractModel model) {
		Long quesId = model.getLong("quesId");
		Byte ans = model.getByte("ans");
		if(quesId == null || ans == null)
			return Helper.error();
		
		Users user = sessionVariables.getUser();
		Questions question = appDAO.getTodayQues(sessionVariables.getUser().getCountryCode());
		if(question == null || !question.getId().equals(quesId))
			return Helper.error();
		
		UserAnss userAns = appDAO.getUserAnss(user.getUserId(), quesId);
		if(userAns != null)
			return Helper.error();
		
		Boolean correct = question.getCorrectAns().equals(ans);
		UserAnssId userAnsId = new UserAnssId();
		userAnsId.setUserId(user.getUserId());
		userAnsId.setQuesId(quesId);
		userAns = new UserAnss();
		userAns.setId(userAnsId);
		userAns.setAns(ans);
		userAns.setCorrect(correct ? "Y" : "N");
		appDAO.savePojo(userAns);
		
		model.clear();
		model.set("correct", correct);
		return model;
	}
	
	@Override
	public Object loginPortal(AbstractModel model) {
		String username = model.getString("username");
		String password = model.getString("password");
		if(username == null || password == null)
			return Helper.error();
		model.clear();
		PortalUsers portalUser = appDAO.getPortalUser(username);
		if(portalUser == null || !portalUser.getPassword().equals(password))
			return model;
		String auth = username + ":" + password + ":" + portalUser.getHideSecCode();
		auth = Helper.encodeBase64(auth);
		model.set("auth", auth);
		return model;
	}
	
	@Override
	public Object createPartner(AbstractModel model) {
		String name = model.getString("name");
		String countryCode = model.getString("countryCode");
		String img = model.getString("img");
		if(name == null || name.isEmpty() || countryCode == null || img == null || img.isEmpty())
			return Helper.error();
		if(!appDAO.isExists("COUNTRIES", new QueryParam("CODE", countryCode)))
			return Helper.error();
		model.clear();
		if(appDAO.isPartnerExists(name, countryCode)) {
			model.set("exists", true);
			return model;
		}
		Partners partner = new Partners();
		partner.setName(name);
		partner.setCountryCode(countryCode);
		partner.setImg(img);
		partner.setPartnerId(appDAO.getNextValue(SystemConstants.NextValues.PARTNER_ID));
		appDAO.savePojo(partner);
		return model;
	}
	
	@Override
	public Object editPartner(AbstractModel model) {
		Long partnerId = model.getLong("partnerId");
		String name = model.getString("name");
		String countryCode = model.getString("countryCode");
		String img = model.getString("img");
		if(partnerId == null || name == null || name.isEmpty() || countryCode == null || img == null || img.isEmpty())
			return Helper.error();
		if(!appDAO.isExists("COUNTRIES", new QueryParam("CODE", countryCode)))
			return Helper.error();
		Partners partner = appDAO.getPartner(partnerId);
		if(partner == null)
			return Helper.error();
		model.clear();
		partner.setName(name);
		partner.setCountryCode(countryCode);
		partner.setImg(img);
		appDAO.updatePojo(partner);
		return model;
	}
	
	@Override
	public Object addQuestion(AbstractModel model) {
		Long partnerId = model.getLong("partnerId");
		String description = model.getString("description");
		String ans1 = model.getString("ans1");
		String ans2 = model.getString("ans2");
		String ans3 = model.getString("ans3");
		String ans4 = model.getString("ans4");
		Byte correctAns = model.getByte("correctAns");
		Date quesDate = model.getDate("quesDate");
		if(partnerId == null || description == null || description.isEmpty() || ans1 == null || ans1.isEmpty() || ans2 == null || ans2.isEmpty() || correctAns == null || quesDate == null)
			return Helper.error();
		if(!appDAO.isExists("PARTNERS", new QueryParam("PARTNER_ID", partnerId)))
			return Helper.error();
		model.clear();
		Date now = Helper.getDayStart(new Date());
		if(quesDate.before(now)) {
			model.set("invalidDate", true);
			return model;
		}
		Questions question = new Questions();
		question.setId(appDAO.getNextValue(SystemConstants.NextValues.QUESTION_ID));
		question.setPartnerId(partnerId);
		question.setDescription(description);
		question.setAns1(ans1);
		question.setAns2(ans2);
		question.setAns3(ans3);
		question.setAns4(ans4);
		question.setCorrectAns(correctAns);
		question.setQuesDate(quesDate);
		appDAO.savePojo(question);
		return model;
	}
	
	@Override
	public Object editQuestion(AbstractModel model) {
		Long id = model.getLong("id");
		Long partnerId = model.getLong("partnerId");
		String description = model.getString("description");
		String ans1 = model.getString("ans1");
		String ans2 = model.getString("ans2");
		String ans3 = model.getString("ans3");
		String ans4 = model.getString("ans4");
		Byte correctAns = model.getByte("correctAns");
		Date quesDate = model.getDate("quesDate");
		if(id == null || partnerId == null || description == null || description.isEmpty() || ans1 == null || ans1.isEmpty() || ans2 == null || ans2.isEmpty() || correctAns == null || quesDate == null)
			return Helper.error();
		if(!appDAO.isExists("PARTNERS", new QueryParam("PARTNER_ID", partnerId)))
			return Helper.error();
		Date now = Helper.getDayStart(new Date());
		if(quesDate.before(now))
			return Helper.error();
		Questions question = appDAO.getQuestion(id);
		if(question == null || question.getQuesDate().before(now))
			return Helper.error();
		model.clear();
		question.setPartnerId(partnerId);
		question.setDescription(description);
		question.setAns1(ans1);
		question.setAns2(ans2);
		question.setAns3(ans3);
		question.setAns4(ans4);
		question.setCorrectAns(correctAns);
		question.setQuesDate(quesDate);
		appDAO.updatePojo(question);
		return model;
	}
	
	@Override
	public Object deleteQuestion(AbstractModel model) {
		Long id = model.getLong("id");
		if(id == null)
			return model;
		Questions question = appDAO.getQuestion(id);
		if(question != null && !question.getQuesDate().before(Helper.getDayStart(new Date())))
			appDAO.deletePojo(question);
		return model;
	}
	
	@Override
	public Object getPartners(AbstractModel model) {
		Integer start = model.getStart();
		Integer length = model.getLength();
		Result result = Helper.checkPaginationStartAndLength(new Result(), start, length);
		if(!result.isExecutionSuccessful())
			return result;
		
		String name = model.getString("name");
		String countryName = model.getString("countryName");
		if(name != null && name.isEmpty())
			name = null;
		if(countryName != null && countryName.isEmpty())
			countryName = null;
		Boolean sizeFlag = model.getSizeFlag();
		model.clear();
		List<VPartners> vPartners = appDAO.getVPartners(name, countryName, start, length, false);
		model.set("data", vPartners);
		if(sizeFlag)	
			model.set("size", appDAO.getVPartners(name, countryName, start, length, true));
		return model;
	}
	
	@Override
	public Object getPartnerData(Long partnerId) {
		AbstractModel model = new AbstractModel();
		String partnerCountryCode = null;
		if(partnerId != null) {
		    Partners partner = appDAO.getPartner(partnerId);
		    if(partner != null) {
		    	model.set("partnerData", partner);
		    	partnerCountryCode = partner.getCountryCode();
		    }
		}
		List<Countries> countries = appDAO.getEnCountries();
		if(countries != null && !countries.isEmpty()) {
			if(partnerCountryCode != null)
				for(Countries country : countries)
					if(country.getCode().equals(partnerCountryCode)) {
						model.set("partnerCountry", country);
						break;
					}
		    model.set("countries", countries);
		}
		return model;
	}
	
	@Override
	public Object getQuestions(AbstractModel model) {
		Integer start = model.getStart();
		Integer length = model.getLength();
		Result result = Helper.checkPaginationStartAndLength(new Result(), start, length);
		if(!result.isExecutionSuccessful())
			return result;
		
		String partnerName = model.getString("partnerName");
		String description = model.getString("description");
		String quesDateString = model.getString("quesDateString");
		if(partnerName != null && partnerName.isEmpty())
			partnerName = null;
		if(description != null && description.isEmpty())
			description = null;
		if(quesDateString != null && quesDateString.isEmpty())
			quesDateString = null;
		Boolean sizeFlag = model.getSizeFlag();
		model.clear();
		List<Object> questions = new ArrayList<Object>();
		List<VQuestions> vQuestions = appDAO.getVQuestions(partnerName, description, quesDateString, start, length, false);
		if(vQuestions != null && !vQuestions.isEmpty()) {
			Date now = Helper.getDayStart(new Date());
			for(VQuestions question : vQuestions) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", question.getId());
				map.put("partnerId", question.getPartnerId());
				map.put("partnerName", question.getPartnerName());
				map.put("description", question.getDescription());
				map.put("quesDateString", question.getQuesDateString());
				map.put("allowDelete", !question.getQuesDate().before(now));
				questions.add(map);
			}
		}
		model.set("data", questions);
		if(sizeFlag)	
			model.set("size", appDAO.getVQuestions(partnerName, description, quesDateString, start, length, true));
		return model;
	}
	
	@Override
	public Object getAllPartners() {
		List<VPartners> partners = appDAO.getVPartners();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("partners", partners);
		return model;
	}
	
	@Override
	public Object getQuestionData(Long id) {
		Questions question = appDAO.getQuestion(id);
		if(question == null)
			return Helper.error();
		Partners partner = appDAO.getPartner(question.getPartnerId());
		Countries country = appDAO.getCountry(partner.getCountryCode());
		AbstractModel model = new AbstractModel();
		model.set("partnerId", question.getPartnerId());
		model.set("partnerName", partner.getName() + " - " + country.getName());
		model.set("description", question.getDescription());
		model.set("ans1", question.getAns1());
		model.set("ans2", question.getAns2());
		model.set("ans3", question.getAns3());
		model.set("ans4", question.getAns4());
		model.set("correctAns", question.getCorrectAns());
		model.set("quesDate", question.getQuesDate().getTime());
		return model;
	}
	
	@Override
	public Object getUserPartnerPoints(AbstractModel model) {
		Integer start = model.getStart();
		Integer length = model.getLength();
		Result result = Helper.checkPaginationStartAndLength(new Result(), start, length);
		if(!result.isExecutionSuccessful())
			return result;
		
		String firstName = model.getString("firstName");
		String lastName = model.getString("lastName");
		String points = model.getString("points");
		String countryName = model.getString("countryName");
		String arCountryName = model.getString("arCountryName");
		String callingCode = model.getString("callingCode");
		String phone = model.getString("phone");
		
		if(firstName != null && firstName.isEmpty())
			firstName = null;
		if(lastName != null && lastName.isEmpty())
			lastName = null;
		if(points != null && points.isEmpty())
			points = null;
		if(countryName != null && countryName.isEmpty())
			countryName = null;
		if(arCountryName != null && arCountryName.isEmpty())
			arCountryName = null;
		if(callingCode != null && callingCode.isEmpty())
			callingCode = null;
		if(phone != null && phone.isEmpty())
			phone = null;
		Boolean sizeFlag = model.getSizeFlag();
		model.clear();
		List<VUserPartnerPoints> vUserPartnerPoints = appDAO.getVUserPartnerPoints(firstName, lastName, points, countryName, arCountryName, callingCode, phone, start, length, false);
		model.set("data", vUserPartnerPoints);
		if(sizeFlag)	
			model.set("size", appDAO.getVUserPartnerPoints(firstName, lastName, points, countryName, arCountryName, callingCode, phone, start, length, true));
		return model;
	}
	
	@Override
	public Object getUserPoints(AbstractModel model) {
		Integer start = model.getStart();
		Integer length = model.getLength();
		Result result = Helper.checkPaginationStartAndLength(new Result(), start, length);
		if(!result.isExecutionSuccessful())
			return result;
		
		String firstName = model.getString("firstName");
		String lastName = model.getString("lastName");
		String points = model.getString("points");
		String countryName = model.getString("countryName");
		String arCountryName = model.getString("arCountryName");
		String callingCode = model.getString("callingCode");
		String phone = model.getString("phone");
		
		if(firstName != null && firstName.isEmpty())
			firstName = null;
		if(lastName != null && lastName.isEmpty())
			lastName = null;
		if(points != null && points.isEmpty())
			points = null;
		if(countryName != null && countryName.isEmpty())
			countryName = null;
		if(arCountryName != null && arCountryName.isEmpty())
			arCountryName = null;
		if(callingCode != null && callingCode.isEmpty())
			callingCode = null;
		if(phone != null && phone.isEmpty())
			phone = null;
		Boolean sizeFlag = model.getSizeFlag();
		model.clear();
		List<VUserPoints> vUserPoints = appDAO.getVUserPoints(firstName, lastName, points, countryName, arCountryName, callingCode, phone, start, length, false);
		model.set("data", vUserPoints);
		if(sizeFlag)	
			model.set("size", appDAO.getVUserPoints(firstName, lastName, points, countryName, arCountryName, callingCode, phone, start, length, true));
		return model;
	}
	
	@Override
	public Object getNotifications(AbstractModel model) {
		Integer start = model.getStart();
		Integer length = model.getLength();
		Result result = Helper.checkPaginationStartAndLength(new Result(), start, length);
		if(!result.isExecutionSuccessful())
			return result;

		Boolean sizeFlag = model.getSizeFlag();
		model.clear();
		List<UserNotifications> userNotifications = appDAO.getUserNotifications(sessionVariables.getUser().getUserId(), start, length, false);
		model.set("data", userNotifications);
		if(sizeFlag)	
			model.set("size", appDAO.getUserNotifications(sessionVariables.getUser().getUserId(), start, length, true));
		return model;
	}
	
	@Override
	public Object addUserNotification(AbstractModel model) {
		Long userId = model.getLong("userId");
		String desc = model.getString("desc");
		if(userId == null || desc == null || desc.trim().isEmpty())
			return Helper.error();
		
		UserNotifications userNotifications = new UserNotifications();
		userNotifications.setId(appDAO.getNextValue(SystemConstants.NextValues.USER_NOTIFICATION_ID));
		userNotifications.setUserId(userId);
		userNotifications.setDesc(desc);
		appDAO.savePojo(userNotifications);
		
		model.clear();
		return model;
	}

}

package com.rabe7ne.dao;

import java.util.List;

import com.rabe7ne.pojos.Countries;
import com.rabe7ne.pojos.Partners;
import com.rabe7ne.pojos.PortalUsers;
import com.rabe7ne.pojos.Questions;
import com.rabe7ne.pojos.UserAnss;
import com.rabe7ne.pojos.Users;
import com.rabe7ne.pojos.VPartners;

public interface AppDAO extends AbstractDAO {
	
	public List<Countries> getCountries();
	
	public Countries getCountry(String code);
	
	public Users getUser(String countryCode, String phone);
	
	public Users getUser(String specCode);
	
	public Questions getTodayQues(String countryCode);
	
	public UserAnss getUserAnss(Long userId, Long quesId);
	
	public Long getUserAnssCorrectCount(Long userID, Long partnerId);
	
	public Partners getPartner(Long partnerId);
	
	public PortalUsers getPortalUser(String username);
	
	public Questions getQuestion(Long id);
	
	public<T> T getVPartners(String name, String countryName, Integer start, Integer length, boolean countFlag);
	
	public List<Countries> getEnCountries();
	
	public<T> T getVQuestions(String partnerName, String description, String quesDateString, Integer start, Integer length, boolean countFlag);
	
	public boolean isPartnerExists(String name, String countryCode);
	
	public List<VPartners> getVPartners();
	
	public<T> T getVUserPartnerPoints(String firstName, String lastName, String points, String countryName, String arCountryName, String callingCode, String phone, Integer start, Integer length, boolean countFlag);

	public<T> T getVUserPoints(String firstName, String lastName, String points, String countryName, String arCountryName, String callingCode, String phone, Integer start, Integer length, boolean countFlag);
	
	public <T> T getUserNotifications(Long userId, Integer start, Integer length, boolean countFlag);

}

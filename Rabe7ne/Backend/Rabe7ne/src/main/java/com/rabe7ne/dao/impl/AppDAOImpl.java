package com.rabe7ne.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rabe7ne.dao.AppDAO;
import com.rabe7ne.pojos.Countries;
import com.rabe7ne.pojos.Partners;
import com.rabe7ne.pojos.PortalUsers;
import com.rabe7ne.pojos.Questions;
import com.rabe7ne.pojos.UserAnss;
import com.rabe7ne.pojos.Users;
import com.rabe7ne.pojos.VPartners;

@Repository
@Transactional
public class AppDAOImpl extends AbstractDAOImpl implements AppDAO {
	
	@Override
	public List<Countries> getCountries() {
		return createQuery("from Countries").getResultList();
	}
	
	@Override
	public Countries getCountry(String code) {
		return createQuery("from Countries where code = :code").setParameter("code", code).getSingleResult();
	}
	
	@Override
	public Users getUser(String countryCode, String phone) {
		return createQuery("from Users where countryCode = :countryCode and phone = :phone")
				.setParameter("countryCode", countryCode).setParameter("phone", phone)
				.getSingleResult();
	}
	
	@Override
	public Users getUser(String specCode) {
		return createQuery("from Users where specCode = :specCode").setParameter("specCode", specCode).getSingleResult();
	}
	
	@Override
	public Questions getTodayQues(String countryCode) {
		Object id = createSQLQuery("SELECT ID FROM QUESTIONS WHERE PARTNER_ID IN (SELECT PARTNER_ID FROM PARTNERS WHERE COUNTRY_CODE = :countryCode) AND DATE_FORMAT(QUES_DATE, \"%m-%d-%Y\") = :currentDate")
				.setParameter("countryCode", countryCode).setParameter("currentDate", new SimpleDateFormat("MM-dd-yyyy").format(new Date()))
				.getSingleResult();
		if(id == null)
			return null;
		return createQuery("from Questions where id = :id")
				.setParameter("id", Long.parseLong(id.toString()))
				.getSingleResult();
	}
	
	@Override
	public UserAnss getUserAnss(Long userId, Long quesId) {
		return createQuery("from UserAnss where id.userId = :userId and id.quesId = :quesId")
				.setParameter("userId", userId).setParameter("quesId", quesId)
				.getSingleResult();
	}
	
	@Override
	public Long getUserAnssCorrectCount(Long userID, Long partnerId) {
		Object res = createSQLQuery("SELECT COUNT(*) FROM USER_ANSS WHERE USER_ID = :userID AND QUES_ID IN (SELECT ID FROM QUESTIONS WHERE PARTNER_ID = :partnerId) AND CORRECT = 'Y'")
				.setParameter("partnerId", partnerId).setParameter("userID", userID)
				.getSingleResult();
		if(res == null)
			return 0L;
		return Long.parseLong(res.toString());
	}
	
	@Override
	public Partners getPartner(Long partnerId) {
		return createQuery("from Partners where partnerId = :partnerId").setParameter("partnerId", partnerId).getSingleResult();
	}
	
	@Override
	public PortalUsers getPortalUser(String username) {
		return createQuery("from PortalUsers where username = :username").setParameter("username", username).getSingleResult();
	}
	
	@Override
	public Questions getQuestion(Long id) {
		return createQuery("from Questions where id = :id").setParameter("id", id).getSingleResult();
	}
	
	@Override
	public<T> T getVPartners(String name, String countryName, Integer start, Integer length, boolean countFlag) {
		String qlString = (countFlag ? "select count(*) " : "") + "from VPartners";
		String where = "";
		if(name != null)
			where += " and lower(name) like '%" + name.toLowerCase() + "%'";
		if(countryName != null)
			where += " and lower(countryName) like '%" + countryName + "%'";
		if(!where.isEmpty())
			qlString += " where " + where.substring(5);
		if(countFlag) {
			Object count = createQuery(qlString).getSingleResult();
			if(count == null)
				count = 0L;
			else if(!(count instanceof Long))
				count = Long.parseLong(count.toString());
			return (T) count;
		}
		return (T) createQuery(qlString + " order by name asc").setStart(start).setLength(length).getResultList();
	}
	
	@Override
	public List<Countries> getEnCountries() {
		return createQuery("from Countries order by name asc").getResultList();
	}
	
	@Override
	public<T> T getVQuestions(String partnerName, String description, String quesDateString, Integer start, Integer length, boolean countFlag) {
		String qlString = (countFlag ? "select count(*) " : "") + "from VQuestions";
		String where = "";
		if(partnerName != null)
			where += " and lower(partnerName) like '%" + partnerName.toLowerCase() + "%'";
		if(description != null)
			where += " and lower(description) like '%" + description + "%'";
		if(quesDateString != null)
			where += " and lower(quesDateString) like '%" + quesDateString + "%'";
		if(!where.isEmpty())
			qlString += " where " + where.substring(5);
		if(countFlag) {
			Object count = createQuery(qlString).getSingleResult();
			if(count == null)
				count = 0L;
			else if(!(count instanceof Long))
				count = Long.parseLong(count.toString());
			return (T) count;
		}
		return (T) createQuery(qlString).setStart(start).setLength(length).getResultList();
	}
	
	@Override
	public boolean isPartnerExists(String name, String countryCode) {
		Object count = createQuery("select count(*) from Partners where lower(name) = :name and countryCode = :countryCode")
				.setParameter("name", name.toLowerCase()).setParameter("countryCode", countryCode).getSingleResult();
		return count == null ? false : (count instanceof Long ? (Long) count : Long.parseLong(count.toString())) > 0;
	}
	
	@Override
	public List<VPartners> getVPartners() {
		return createQuery("from VPartners order by name asc").getResultList();
	}
	
	@Override
	public<T> T getVUserPartnerPoints(String firstName, String lastName, String points, String countryName, String arCountryName, String callingCode, String phone, Integer start, Integer length, boolean countFlag) {
		String qlString = (countFlag ? "select count(*) " : "") + "from VUserPartnerPoints";
		String where = "";
		if(firstName != null)
			where += " and lower(firstName) like '%" + firstName.toLowerCase() + "%'";
		if(lastName != null)
			where += " and lower(lastName) like '%" + lastName + "%'";
		if(points != null)
			where += " and lower(points) like '%" + points + "%'";
		if(countryName != null)
			where += " and lower(countryName) like '%" + countryName + "%'";
		if(arCountryName != null)
			where += " and lower(arCountryName) like '%" + arCountryName + "%'";
		if(callingCode != null)
			where += " and lower(callingCode) like '%" + callingCode + "%'";
		if(phone != null)
			where += " and lower(phone) like '%" + phone + "%'";
		if(!where.isEmpty())
			qlString += " where " + where.substring(5);
		if(countFlag) {
			Object count = createQuery(qlString).getSingleResult();
			if(count == null)
				count = 0L;
			else if(!(count instanceof Long))
				count = Long.parseLong(count.toString());
			return (T) count;
		}
		return (T) createQuery(qlString).setStart(start).setLength(length).getResultList();
	}
	
	@Override
	public<T> T getVUserPoints(String firstName, String lastName, String points, String countryName, String arCountryName, String callingCode, String phone, Integer start, Integer length, boolean countFlag) {
		String qlString = (countFlag ? "select count(*) " : "") + "from VUserPoints";
		String where = "";
		if(firstName != null)
			where += " and lower(firstName) like '%" + firstName.toLowerCase() + "%'";
		if(lastName != null)
			where += " and lower(lastName) like '%" + lastName + "%'";
		if(points != null)
			where += " and lower(points) like '%" + points + "%'";
		if(countryName != null)
			where += " and lower(countryName) like '%" + countryName + "%'";
		if(arCountryName != null)
			where += " and lower(arCountryName) like '%" + arCountryName + "%'";
		if(callingCode != null)
			where += " and lower(callingCode) like '%" + callingCode + "%'";
		if(phone != null)
			where += " and lower(phone) like '%" + phone + "%'";
		if(!where.isEmpty())
			qlString += " where " + where.substring(5);
		if(countFlag) {
			Object count = createQuery(qlString).getSingleResult();
			if(count == null)
				count = 0L;
			else if(!(count instanceof Long))
				count = Long.parseLong(count.toString());
			return (T) count;
		}
		return (T) createQuery(qlString).setStart(start).setLength(length).getResultList();
	}
	
	@Override
	public <T> T getUserNotifications(Long userId, Integer start, Integer length, boolean countFlag) {
		String qlString = (countFlag ? "select count(*) " : "") + "from UserNotifications where userId = " + userId;
		if(!countFlag)
			qlString += " order by id desc";
		if(countFlag) {
			Object count = createQuery(qlString).getSingleResult();
			if(count == null)
				count = 0L;
			else if(!(count instanceof Long))
				count = Long.parseLong(count.toString());
			return (T) count;
		}
		return (T) createQuery(qlString).setStart(start).setLength(length).getResultList();
	}

}

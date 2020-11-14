package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "USERS")
public class Users extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "SPEC_CODE")
	private String specCode;

	@Column(name = "POINTS")
	private Long points;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "PASS")
	private String pass;

	@Column(name = "CONFIRM_CODE")
	private String confirmCode;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("USERS");
		queryParams.addId("USER_ID", userId);
		queryParams.add("FIRST_NAME", firstName);
		queryParams.add("LAST_NAME", lastName);
		queryParams.add("SPEC_CODE", specCode);
		queryParams.add("POINTS", points);
		queryParams.add("COUNTRY_CODE", countryCode);
		queryParams.add("PHONE", phone);
		queryParams.add("PASS", pass);
		queryParams.add("CONFIRM_CODE", confirmCode);
		return queryParams;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

}

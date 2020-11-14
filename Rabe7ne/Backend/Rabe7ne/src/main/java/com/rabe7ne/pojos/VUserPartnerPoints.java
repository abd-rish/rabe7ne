package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "V_USER_PARTNER_POINTS")
public class VUserPartnerPoints implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private VUserPartnerPointsId id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "POINTS")
	private String points;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	@Column(name = "AR_COUNTRY_NAME")
	private String arCountryName;

	@Column(name = "CALLING_CODE")
	private String callingCode;

	@Column(name = "PHONE")
	private String phone;

	public VUserPartnerPointsId getId() {
		return id;
	}

	public void setId(VUserPartnerPointsId id) {
		this.id = id;
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

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getArCountryName() {
		return arCountryName;
	}

	public void setArCountryName(String arCountryName) {
		this.arCountryName = arCountryName;
	}

	public String getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

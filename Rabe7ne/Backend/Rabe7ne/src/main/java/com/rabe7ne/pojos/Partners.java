package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "PARTNERS")
public class Partners extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PARTNER_ID")
	private Long partnerId;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "IMG")
	private String img;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("PARTNERS");
		queryParams.addId("PARTNER_ID", partnerId);
		queryParams.add("NAME", name);
		queryParams.add("COUNTRY_CODE", countryCode);
		queryParams.add("IMG", img);
		return queryParams;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}

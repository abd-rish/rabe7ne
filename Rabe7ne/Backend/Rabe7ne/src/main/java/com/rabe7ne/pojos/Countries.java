package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "COUNTRIES")
public class Countries extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "AR_NAME")
	private String arName;
	
	@Column(name = "CALLING_CODE")
	private String callingCode;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("COUNTRIES");
		queryParams.addId("CODE", code);
		queryParams.add("NAME", name);
		queryParams.add("AR_NAME", arName);
		queryParams.add("CALLING_CODE", callingCode);
		return queryParams;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getArName() {
		return arName;
	}
	
	public void setArName(String arName) {
		this.arName = arName;
	}

	public String getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}
	
}

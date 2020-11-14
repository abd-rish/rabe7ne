package com.rabe7ne.pojos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "V_QUESTIONS")
public class VQuestions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PARTNER_ID")
	private Long partnerId;

	@Column(name = "PARTNER_NAME")
	private String partnerName;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "QUES_DATE")
	private Date quesDate;
	
	@Column(name = "QUES_DATE_STRING")
	private String quesDateString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getQuesDate() {
		return quesDate;
	}

	public void setQuesDate(Date quesDate) {
		this.quesDate = quesDate;
	}

	public String getQuesDateString() {
		return quesDateString;
	}

	public void setQuesDateString(String quesDateString) {
		this.quesDateString = quesDateString;
	}

}

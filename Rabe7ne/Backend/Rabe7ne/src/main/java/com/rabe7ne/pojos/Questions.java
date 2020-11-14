package com.rabe7ne.pojos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "QUESTIONS")
public class Questions extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PARTNER_ID")
	private Long partnerId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ANS_1")
	private String ans1;

	@Column(name = "ANS_2")
	private String ans2;

	@Column(name = "ANS_3")
	private String ans3;

	@Column(name = "ANS_4")
	private String ans4;
	
	@Column(name = "CORRECT_ANS")
	private Byte correctAns;

	@Column(name = "QUES_DATE")
	private Date quesDate;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("QUESTIONS");
		queryParams.addId("ID", id);
		queryParams.add("PARTNER_ID", partnerId);
		queryParams.add("DESCRIPTION", description);
		queryParams.add("ANS_1", ans1);
		queryParams.add("ANS_2", ans2);
		queryParams.add("ANS_3", ans3);
		queryParams.add("ANS_4", ans4);
		queryParams.add("CORRECT_ANS", correctAns);
		queryParams.add("QUES_DATE", quesDate);
		return queryParams;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public String getAns4() {
		return ans4;
	}

	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	public Byte getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(Byte correctAns) {
		this.correctAns = correctAns;
	}

	public Date getQuesDate() {
		return quesDate;
	}

	public void setQuesDate(Date quesDate) {
		this.quesDate = quesDate;
	}

}

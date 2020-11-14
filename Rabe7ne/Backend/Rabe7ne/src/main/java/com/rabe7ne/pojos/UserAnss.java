package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "USER_ANSS")
public class UserAnss extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserAnssId id;

	@Column(name = "ANS")
	private Byte ans;

	@Column(name = "CORRECT")
	private String correct;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("USER_ANSS");
		queryParams.addId("USER_ID", id.getUserId());
		queryParams.addId("QUES_ID", id.getQuesId());
		queryParams.add("ANS", ans);
		queryParams.add("CORRECT", correct);
		return queryParams;
	}

	public UserAnssId getId() {
		return id;
	}

	public void setId(UserAnssId id) {
		this.id = id;
	}

	public Byte getAns() {
		return ans;
	}

	public void setAns(Byte ans) {
		this.ans = ans;
	}
	
	public String getCorrect() {
		return correct;
	}
	
	public void setCorrect(String correct) {
		this.correct = correct;
	}

}

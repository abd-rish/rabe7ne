package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "PORTAL_USERS")
public class PortalUsers extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "HIDE_SEC_CODE")
	private String hideSecCode;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("PORTAL_USERS");
		queryParams.addId("USER_ID", userId);
		queryParams.add("USERNAME", username);
		queryParams.add("PASSWORD", password);
		queryParams.add("HIDE_SEC_CODE", hideSecCode);
		return queryParams;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHideSecCode() {
		return hideSecCode;
	}

	public void setHideSecCode(String hideSecCode) {
		this.hideSecCode = hideSecCode;
	}

}

package com.rabe7ne.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rabe7ne.util.QueryParams;

@Entity
@Table(name = "NEXT_VALUES")
public class NextValues extends AbstractPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "VALUE")
	private Long value;
	
	public QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams("NEXT_VALUES");
		queryParams.addId("CODE", code);
		queryParams.add("VALUE", value);
		return queryParams;
	}
	
	public NextValues() {}

	public NextValues(String code, Long value) {
		super();
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}

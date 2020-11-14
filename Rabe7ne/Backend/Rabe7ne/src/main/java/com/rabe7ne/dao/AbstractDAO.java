package com.rabe7ne.dao;

import com.rabe7ne.pojos.AbstractPojo;
import com.rabe7ne.util.QueryParam;

public interface AbstractDAO {
	
	public <P extends AbstractPojo> void savePojo(P p);
	
	public <P extends AbstractPojo> void updatePojo(P p);
	
	public <P extends AbstractPojo> void deletePojo(P p);
	
	public Long count(String table, QueryParam ... params);
	
	public boolean isExists(String table, QueryParam ... params);
	
	public void delete(String table, QueryParam ... params);
	
	public Long getNextValue(String code);
	
}

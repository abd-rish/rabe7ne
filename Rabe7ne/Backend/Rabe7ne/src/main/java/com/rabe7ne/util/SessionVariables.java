package com.rabe7ne.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabe7ne.pojos.PortalUsers;
import com.rabe7ne.pojos.Users;

@Component
public class SessionVariables {
	
	@Autowired
	private HttpSession session;
	
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public Users getUser() {
		Object user = session.getAttribute("user");
		if(user == null)
			return null;
		return (Users) user;
	}
	
	public PortalUsers getPortalUser() {
		Object portalUser = session.getAttribute("portalUser");
		if(portalUser == null)
			return null;
		return (PortalUsers) portalUser;
	}

}

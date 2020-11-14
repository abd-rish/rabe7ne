package com.rabe7ne.security;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rabe7ne.dao.AppDAO;
import com.rabe7ne.pojos.PortalUsers;
import com.rabe7ne.pojos.Users;
import com.rabe7ne.util.Helper;
import com.rabe7ne.util.SessionVariables;

@Component
public class RequestInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AppDAO appDAO;
	
	@Autowired
	private SessionVariables sessionVariables;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod() != null && request.getMethod().toUpperCase().equals("OPTIONS"))
			return true;
		if(request.getRequestURI() != null && request.getRequestURI().contains("/auth/"))
			return true;
		
		try {
			String auth = request.getHeader("Authorization");
			if(auth == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			auth = Helper.decodeBase64(auth);
			StringTokenizer st = new StringTokenizer(auth, ":");
			
			if(request.getRequestURI() != null && request.getRequestURI().contains("/portal/")) {
				String username = st.nextToken();
				String password = st.nextToken();
				String hideSecCode = st.nextToken();
				PortalUsers portalUser = appDAO.getPortalUser(username);
				if(portalUser == null || !portalUser.getPassword().equals(password) || !portalUser.getHideSecCode().equals(hideSecCode)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
				sessionVariables.set("portalUser", portalUser);
				return true;
			}
			
			String countryCode = st.nextToken();
			String phone = st.nextToken();
			String pass = st.nextToken();
			Users user = appDAO.getUser(countryCode, phone);
			if(user == null || !user.getPass().equals(pass)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			sessionVariables.set("user", user);
			return true;
		} catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}

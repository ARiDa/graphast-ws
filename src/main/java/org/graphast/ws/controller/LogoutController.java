package org.graphast.ws.controller;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/logout")
public class LogoutController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody void index(HttpServletRequest request) {
		log.debug("logout service");
		request.getSession().invalidate();
	}
	
}

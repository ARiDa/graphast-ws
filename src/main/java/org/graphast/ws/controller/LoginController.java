package org.graphast.ws.controller;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/login")
public class LoginController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> index(@RequestParam String pass, HttpServletRequest request) {
		log.debug("login service");
		if (pass.equals("graphast2015")) {
			request.getSession().setAttribute("login", "OK");
			return new ResponseEntity<String>("OK",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Fail",HttpStatus.NOT_FOUND);
		}
	}
	
}

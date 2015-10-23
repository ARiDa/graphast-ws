package org.graphast.ws.controller;

import java.io.IOException;

import javax.inject.Named;

import org.graphast.ws.model.WebAppGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/reload")
public class MainController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String reload() {
		try {
			log.debug("RELOAD");
			WebAppGraph.reload();
			return "Graph Dir: " + WebAppGraph.getGraphDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}

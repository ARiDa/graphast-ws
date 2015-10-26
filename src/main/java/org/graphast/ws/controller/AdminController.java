package org.graphast.ws.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.graphast.ws.config.Config;
import org.graphast.ws.model.WebAppGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/admin")
public class AdminController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> apps() {
		log.debug("apps service");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("apps", Config.getApps());
		result.put("selectedApp", Config.getSelectedApp());
		return result;
	}

	@RequestMapping(value="/reload", method = RequestMethod.GET)
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

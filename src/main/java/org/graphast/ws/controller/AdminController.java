package org.graphast.ws.controller;

import java.util.List;

import javax.inject.Named;

import org.graphast.app.GraphInfo;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/admin")
public class AdminController {

	private GraphService service = new GraphService();
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String index() {
		log.debug("admin service");
		return "Admin service";
	}

	@RequestMapping(value="/apps", method = RequestMethod.GET)
	public @ResponseBody List<GraphInfo> apps() {
		log.debug("apps");
		return Configuration.getApps();
	}
	
	@RequestMapping(value="/load/{app}", method = RequestMethod.GET)
	public @ResponseBody GraphInfo load(@PathVariable String app) {
		log.debug("load {}", app);
		return service.load(app);
	}
	
	@RequestMapping(value="/create/{url}", method = RequestMethod.GET)
	public @ResponseBody GraphInfo create(@PathVariable String url) {
		log.debug("create");
		return service.create(null, url);
	}
	
}

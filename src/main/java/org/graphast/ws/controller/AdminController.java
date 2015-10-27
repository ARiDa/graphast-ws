package org.graphast.ws.controller;

import java.util.List;

import javax.inject.Named;

import org.graphast.app.AppGraph;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.graphast.geometry.BBox;
import org.graphast.model.Graph;
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
	public @ResponseBody String admin() {
		log.debug("admin service");
		return "Admin service";
	}

	@RequestMapping(value="/apps", method = RequestMethod.GET)
	public @ResponseBody List<String> apps() {
		log.debug("apps");
		return Configuration.getApps();
	}
	
	@RequestMapping(value="/selected-app", method = RequestMethod.GET)
	public @ResponseBody String selectedApp() {
		log.debug("selected-app");
		return Configuration.getSelectedApp();
	}
	
	@RequestMapping(value="/reload", method = RequestMethod.GET)
	public @ResponseBody String reload() {
		log.debug("reload");
		service.load(null);
		return "Graph Dir: " + AppGraph.getGraphDir();
	}

	@RequestMapping(value="/load/{app}", method = RequestMethod.GET)
	public @ResponseBody String load(@PathVariable String app) {
		log.debug("load {}", app);
		service.load(app);
		return "Graph Dir: " + AppGraph.getGraphDir();
	}
	
	@RequestMapping(value="/bbox", method = RequestMethod.GET)
	public @ResponseBody BBox bBox() {
		log.debug("BBox");
		Graph graph = AppGraph.getGraph();
		return graph.getBBox();
	}
	
}

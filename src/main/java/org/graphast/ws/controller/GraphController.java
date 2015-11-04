package org.graphast.ws.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.graphast.app.AppGraph;
import org.graphast.app.GraphInfo;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.graphast.geometry.BBox;
import org.graphast.geometry.PoI;
import org.graphast.model.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/graph")
public class GraphController {

	private GraphService service = new GraphService();
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String index() {
		log.debug("graph service");
		if (AppGraph.getGraph() == null) {
			return "no graph loaded";
		}
		return Configuration.getSelectedApp();
	}

	@RequestMapping(value="/reload", method = RequestMethod.GET)
	public @ResponseBody GraphInfo reload() {
		log.debug("reload");
		return service.load(null);
	}
	
	@RequestMapping(value="/bbox", method = RequestMethod.GET)
	public @ResponseBody BBox bBox() {
		log.debug("BBox");
		Graph graph = AppGraph.getGraph();
		return graph.getBBox();
	}

	@RequestMapping(value="/pois", method = RequestMethod.GET)
	public @ResponseBody List<PoI> pois() {
		log.debug("POIs");
		Graph graph = AppGraph.getGraph();
		return graph.getPOIs();
	}
	
	@RequestMapping(value="/pois/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<PoI> pois(@PathVariable Integer categoryId) {
		log.debug("POIs - categoryId");
		Graph graph = AppGraph.getGraph();
		return graph.getPOIs(categoryId);
	}

	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public @ResponseBody List<Integer> categories() {
		log.debug("POI Categories");
		Graph graph = AppGraph.getGraph();
		return graph.getPOICategories();
	}
	
	@RequestMapping(value="/allcategories", method = RequestMethod.GET)
	public @ResponseBody Map<Integer, String> allCategories() {
		log.debug("All POI Categories");
		return AppGraph.getAllPoiCategories();
	}
	
	@RequestMapping(value="/query-services", method = RequestMethod.GET)
	public @ResponseBody String[] queryServices() {
		log.debug("Query Services");
		String queryServices = Configuration.getProperty(Configuration.getSelectedApp(), "query-services");
		return queryServices != null ? queryServices.split("\\s*,\\s*") : null;
	}

	@RequestMapping(value="/query-services/{app}", method = RequestMethod.GET)
	public @ResponseBody String[] pois(@PathVariable String app) {
		log.debug("Query Services of a given App");
		String queryServices = Configuration.getProperty(app, "query-services"); 
		return queryServices != null ? queryServices.split("\\s*,\\s*") : null;
	}
	
}

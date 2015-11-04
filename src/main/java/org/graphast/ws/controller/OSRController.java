package org.graphast.ws.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.graphast.app.AppGraph;
import org.graphast.model.GraphBounds;
import org.graphast.query.route.osr.OSRSearch;
import org.graphast.query.route.shortestpath.model.Path;
import org.graphast.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/osr")
public class OSRController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String index() {
		log.debug("OSR service");
		return "OSR"; 
	}
	
	@RequestMapping(value="{lat1}/{long1}/{lat2}/{long2}/{hour}/{minute}/{categoriesString}" ,method = RequestMethod.GET)
	public @ResponseBody Path osr(@PathVariable Double lat1, 
			@PathVariable Double long1, @PathVariable Double lat2, @PathVariable Double long2, 
			@PathVariable int hour, @PathVariable int minute, @PathVariable String categoriesString) {
		
		log.debug("OSR");
		GraphBounds graph = AppGraph.getGraph();
		
		OSRSearch osr = new OSRSearch(graph, (short)0);

		Long source = graph.getNodeId(lat1, long1);
		Long target = graph.getNodeId(lat2, long2);

		Date date = DateUtils.parseDate(hour, minute, 0);

		String[] cat = categoriesString.split("\\s*,\\s*");
		List<Integer> categories = new ArrayList<Integer>();
		for(int i = 0; i < cat.length; i++) {
			categories.add(Integer.parseInt(cat[i]));
		}
		
		return osr.getFullPath(graph.getNode(source), 
				graph.getNode(target), date, categories);
	}
	
}

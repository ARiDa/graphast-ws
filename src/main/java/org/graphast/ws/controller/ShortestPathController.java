package org.graphast.ws.controller;

import java.text.ParseException;
import java.util.Date;

import javax.inject.Named;

import org.graphast.app.AppGraph;
import org.graphast.model.Graph;
import org.graphast.query.route.shortestpath.AbstractShortestPathService;
import org.graphast.query.route.shortestpath.astar.AStarConstantWeight;
import org.graphast.query.route.shortestpath.dijkstra.DijkstraConstantWeight;
import org.graphast.query.route.shortestpath.dijkstra.DijkstraLinearFunction;
import org.graphast.query.route.shortestpath.model.Path;
import org.graphast.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/shortestpath")
public class ShortestPathController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String shortestPath() {
		return "Shortest Path Services"; 
	}
	
	@RequestMapping(value="{lat1}/{long1}/{lat2}/{long2}", method = RequestMethod.GET)
	public @ResponseBody Path shortestPath(@PathVariable Double lat1, 
			@PathVariable Double long1, @PathVariable Double lat2, @PathVariable Double long2) {
		
		log.debug("Atividade - GET (id)");
		Graph graph = AppGraph.getGraph();
		
		AbstractShortestPathService sp = new DijkstraConstantWeight(graph);
		long source = graph.getNodeId(lat1, long1);
		long target = graph.getNodeId(lat2, long2);
		Path path = sp.shortestPath(source, target);
		return path;
	}
	
	@RequestMapping(value="{lat1}/{long1}/{lat2}/{long2}/{time}", method = RequestMethod.GET)
	public @ResponseBody Path shortestPathLinearFunction(@PathVariable Double lat1, 
			@PathVariable Double long1, @PathVariable Double lat2, @PathVariable Double long2, @PathVariable int time) throws ParseException {

		log.debug("Atividade - GET (id)");
		Graph graph;
		graph = AppGraph.getGraph();
		
		AbstractShortestPathService sp = new DijkstraLinearFunction(graph);
		new org.graphast.util.DateUtils();
		Date d = DateUtils.parseDate(0, time, 0);
		long source = graph.getNodeId(lat1, long1);
		long target = graph.getNodeId(lat2, long2);
		Path path = sp.shortestPath(source, target, d);
		return path;
	}
	
	@RequestMapping(value="a*/{lat1}/{long1}/{lat2}/{long2}", method = RequestMethod.GET)
	public @ResponseBody Path shortestPathAStar(@PathVariable Double lat1, 
			@PathVariable Double long1, @PathVariable Double lat2, @PathVariable Double long2) {
		
		log.debug("Atividade - GET (id)");
		Graph graph;
		graph = AppGraph.getGraph();
		
		AbstractShortestPathService sp = new AStarConstantWeight(graph);
		long source = graph.getNodeId(lat1, long1);
		long target = graph.getNodeId(lat2, long2);
		Path path = sp.shortestPath(source, target);
		return path;
	}
	
	
}

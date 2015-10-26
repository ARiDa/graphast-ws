package org.graphast.ws.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.graphast.enums.GraphBoundsType;
import org.graphast.model.GraphBounds;
import org.graphast.query.knn.BoundsKNNTC;
import org.graphast.query.knn.KNNTCSearch;
import org.graphast.query.knn.NearestNeighbor;
import org.graphast.util.DateUtils;
import org.graphast.ws.model.KNNResult;
import org.graphast.ws.model.WebAppGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/knn")
public class KNNController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/{sourceLat}/{sourceLong}/{time}/{boundType}", method = RequestMethod.GET)
	public @ResponseBody KNNResult knn(@PathVariable Double sourceLat, 
			@PathVariable Double sourceLong, @PathVariable int time, @PathVariable int boundType) throws ParseException {
		
		log.debug("Atividade - GET (id)");
		GraphBounds graph;
		graph = (GraphBounds)WebAppGraph.getGraph();
		graph.createBounds();
		
		//calculate or load bounds
		BoundsKNNTC minBounds = new BoundsKNNTC(graph, GraphBoundsType.LOWER);
		BoundsKNNTC maxBounds = new BoundsKNNTC(graph, GraphBoundsType.UPPER);
		
		KNNTCSearch knn = new KNNTCSearch(graph, minBounds, maxBounds);
		long source = graph.getNodeId(sourceLat, sourceLong);
		
		new DateUtils();
		//TODO mudar para entrar uma data
		Date d = DateUtils.parseDate(0, time, 0);
		List<NearestNeighbor> nn = knn.search(graph.getNode(source), d, boundType);
		
		KNNResult knnResult = new KNNResult(graph.getNode(source));
		knnResult.generateResult(graph, nn, time);
		
		return knnResult;
	}
	
	
}

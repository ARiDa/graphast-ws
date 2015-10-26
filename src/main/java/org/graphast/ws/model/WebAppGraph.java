package org.graphast.ws.model;

import java.io.IOException;

import org.graphast.model.Graph;
import org.graphast.model.GraphBoundsImpl;
import org.graphast.ws.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppGraph {

	private static Graph graph;
	private static String graphDir;

	private static Logger log = LoggerFactory.getLogger(WebAppGraph.class);

	private WebAppGraph() {	
		super();
	} 

	public static Graph reload() throws IOException {
		Config.reload();
		graphDir = Config.getProperty("graphast." + Config.getSelectedApp() + ".dir");
		log.debug("graphDir: {}", graphDir);
		graph = new GraphBoundsImpl(graphDir);
		graph.load();
		return graph;
	}
	
	public static Graph getGraph() throws IOException {
		if (graph == null) {
			reload();
		}
		return graph;
	}

	public static String getGraphDir() throws IOException {
		if (graphDir == null) {
			reload();
		}
		return graphDir;
	}

	
}

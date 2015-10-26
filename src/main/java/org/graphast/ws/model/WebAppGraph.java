package org.graphast.ws.model;

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

	public static Graph load(String app) {
		Config.reload();
		if (app != null) {
			Config.setSelectedApp(app);
			Config.save();
		}
		graphDir = Config.getProperty("graphast." + Config.getSelectedApp() + ".dir");
		log.debug("graphDir: {}", graphDir);
		graph = new GraphBoundsImpl(graphDir);
		graph.load();
		return graph;
	}
	
	public static Graph getGraph() {
		if (graph == null) {
			load(null);
		}
		return graph;
	}

	public static String getGraphDir() {
		if (graphDir == null) {
			load(null);
		}
		return graphDir;
	}
	
}

package org.graphast.ws.model;

import java.io.IOException;

import org.graphast.model.Graph;
import org.graphast.model.GraphImpl;
import org.graphast.ws.config.Config;

public class WebAppGraph {

	private static Graph graph;
	private static String graphDir;

	private WebAppGraph() {	
		super();
	} 

	public static Graph reload() throws IOException {
		Config.reload();
		graphDir = Config.getProperty("graph.dir");
		graph = new GraphImpl(graphDir);
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

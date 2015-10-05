package org.graphast.ws.model;

import java.io.IOException;

import org.graphast.config.Configuration;
import org.graphast.model.Graph;
import org.graphast.model.GraphImpl;

public class LoadedGraph {

	private static LoadedGraph loadedGraph;
	
	private Graph graph;

	private LoadedGraph() {	
		super();
	} 

	public static LoadedGraph getInstance() throws IOException {
		if (loadedGraph == null) {
			loadedGraph = new LoadedGraph();
			loadedGraph.graph = new GraphImpl(Configuration.USER_HOME + "/graphast/monaco");
			loadedGraph.graph.load();
		}
		return loadedGraph;
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
}

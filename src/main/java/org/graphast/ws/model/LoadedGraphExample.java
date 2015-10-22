package org.graphast.ws.model;

import java.io.IOException;

import org.graphast.config.Configuration;
import org.graphast.model.Graph;
import org.graphast.model.GraphImpl;

public class LoadedGraphExample {
	private static LoadedGraphExample loadedGraph;
	private Graph graph;

	private LoadedGraphExample() {	
		super();
	} 
	
	public static LoadedGraphExample getInstanceExample() throws IOException {
		if (loadedGraph == null) {
			loadedGraph = new LoadedGraphExample();
			loadedGraph.graph = new GraphImpl(Configuration.USER_HOME + "/graphast/example");
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

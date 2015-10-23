package org.graphast.ws.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.graphast.model.Edge;
import org.graphast.model.Graph;
import org.graphast.model.Node;
import org.graphast.query.knn.NearestNeighbor;
import org.graphast.query.route.shortestpath.AbstractShortestPathService;
import org.graphast.query.route.shortestpath.dijkstra.DijkstraLinearFunction;
import org.graphast.query.route.shortestpath.model.Path;

public class KNNResult {
	private Node source;
	private ArrayList<Node> nodes;
	private HashMap<Node,ArrayList<Point>> geometry;
	private HashMap<Node,ArrayList<Instruction>> path;
	private HashMap<Node,Double> distance;
	private HashMap<Node,Double> cost;
	
	public KNNResult(Node source) {
		this.source = source;
		nodes = new ArrayList<Node>();
		geometry = new HashMap<Node,ArrayList<Point>>();
		path = new HashMap<Node,ArrayList<Instruction>>();
		distance = new HashMap<Node,Double>();
		cost = new HashMap<Node,Double>();
	}
	
	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public HashMap<Node, ArrayList<Point>> getGeometry() {
		return geometry;
	}

	public void setGeometry(HashMap<Node, ArrayList<Point>> geometry) {
		this.geometry = geometry;
	}

	public HashMap<Node, ArrayList<Instruction>> getPath() {
		return path;
	}

	public void setPath(HashMap<Node, ArrayList<Instruction>> path) {
		this.path = path;
	}

	public HashMap<Node, Double> getDistance() {
		return distance;
	}

	public void setDistance(HashMap<Node, Double> distance) {
		this.distance = distance;
	}

	public HashMap<Node, Double> getCost() {
		return cost;
	}

	public void setCost(HashMap<Node, Double> cost) {
		this.cost = cost;
	}
	
	public void generateResult(Graph graph, List<NearestNeighbor> nn, int time) {
		//import Nodes
		for(int i = 0; i < nn.size(); i++) {
			nodes.add(graph.getNode(nn.get(i).getId()));
		}
		
		//import geometry,path, distance and cost
		for (int i = 0; i < nodes.size(); i++) {
			AbstractShortestPathService shortestPath = new DijkstraLinearFunction(graph);
			Path path = shortestPath.shortestPath(source, nodes.get(i));
			
			//path
			ArrayList<Instruction> localPath = new ArrayList<Instruction>();
			int geometryIndex = -1;
			int jAtual = -1;
			for(int o = 0; o < path.getInstructions().size(); o++) {
				String labelName = path.getInstructions().get(o).getLabel();
				Instruction inst = new Instruction();
				inst.setBeginInterval(geometryIndex+1);
				int j = jAtual+1;
				boolean sair = false;
				while(j < path.getEdges().size()) {
					Edge e = graph.getEdge(path.getEdges().get(j));
					if(e.getLabel().equals(labelName)) {
						inst.setDistance(inst.getDistance() + e.getDistance());
						if(graph.getEdgeCost(e, 0) != null) {
							inst.setCost(inst.getCost() + graph.getEdgeCost(e, 0));
						}
						if(e.getGeometry() != null) { 
							geometryIndex += e.getGeometry().size();
						}	
						inst.setEndInterval(geometryIndex);
						jAtual++;
						sair = true;
					}else {
						if(sair = true) {
							break;
						}
					}
					j++;
				}
				inst.setDirection(path.getInstructions().get(o).getDirection());
				inst.setLabel(labelName);
				localPath.add(inst);
			}
			this.path.put(nodes.get(i), localPath);
			
			//geometry, distance and cost
			ArrayList<Point> geometry = new ArrayList<Point>();
			double distance = 0;
			double cost = 0;
			for(int a = 0; a < path.getEdges().size(); a++){
				Edge e = graph.getEdge(path.getEdges().get(a));
				distance += e.getDistance();
				cost += graph.getEdgeCost(e, time);
				for(int b = 0; b < e.getGeometry().size(); b++) {
					Point point = new Point(e.getGeometry().get(b).getLatitude(), e.getGeometry().get(b).getLongitude());
					geometry.add(point);
				}
				
			}
			this.geometry.put(nodes.get(i), geometry);
			this.distance.put(nodes.get(i), distance);
			this.cost.put(nodes.get(i), cost);
		}
	}
}

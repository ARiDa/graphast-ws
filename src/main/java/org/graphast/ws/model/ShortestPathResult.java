package org.graphast.ws.model;

import java.util.ArrayList;

import org.graphast.model.Edge;
import org.graphast.model.Graph;
import org.graphast.query.route.shortestpath.model.Path;

public class ShortestPathResult {
	private ArrayList<Point> geometry;
	private ArrayList<Instruction> path;
	private double distance;
	private double cost;
	
	
	public ShortestPathResult() {
		geometry = new ArrayList<Point>();
		path = new ArrayList<Instruction>();
		distance = 0;
	}
	
	
	public ShortestPathResult(ArrayList<Point> geometry, ArrayList<Instruction> path, double distance, double cost) {
		super();
		this.geometry = geometry;
		this.path = path;
		this.distance = distance;
		this.cost = cost;
	}

	public ArrayList<Point> getGeometry() {
		return geometry;
	}
	public void setGeometry(ArrayList<Point> geometry) {
		this.geometry = geometry;
	}
	public ArrayList<Instruction> getPath() {
		return path;
	}
	public void setPath(ArrayList<Instruction> path) {
		this.path = path;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	
	public void generateResult(Graph graph, Path path) {
		//geometry, final distance and final cost
		for(int i = 0; i < path.getEdges().size(); i++) {
			Edge e = graph.getEdge(path.getEdges().get(i));
			this.distance += e.getDistance();
			if(graph.getEdgeCost(e, 0) != null) {
				this.cost += graph.getEdgeCost(e, 0);
			}
			if(e.getGeometry() != null) { 
				for(int j = 0; j < e.getGeometry().size(); j++) {
					Point p = new Point(e.getGeometry().get(j).getLatitude(),e.getGeometry().get(j).getLongitude());
					this.geometry.add(p);
				}
			}	
		}
		
		
		// instructions
		int geometryIndex = -1;
		int jAtual = -1;
		for(int i = 0; i < path.getPath().size(); i++) {
			String labelName = path.getPath().get(i).getLabel();
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
			inst.setDirection(path.getPath().get(i).getDirection());
			inst.setLabel(labelName);
			this.path.add(inst);
		}
		
		
	}

	@Override
	public String toString() {
		String result = "{\n	geometry: [ \n";
		for(int i = 0; i < geometry.size(); i++) {
			result = result + "	{" + geometry.get(i).toString() + "}, \n";
		}
		result = result + "], \n" +
					"path: [ \n";
		for(int i = 0; i < path.size(); i++) {
			result = result + "	{ \n		" + path.get(i).toString() + "\n	},";
		}
		result = result + "	], \n" +
				"	total: { \n" +
				"		distance: " + distance + ",\n" +
				"		cost: " + cost + "\n" +
				"	}\n}";
		
		return result;
	}
	
	
	
}

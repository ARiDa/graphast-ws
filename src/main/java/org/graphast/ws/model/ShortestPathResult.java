package org.graphast.ws.model;

import java.util.ArrayList;

public class ShortestPathResult {
	private ArrayList<Point> geometry;
	private ArrayList<Instruction> path;
	private double distance;
	private double cost;
	
	
	public ShortestPathResult() {
		geometry = new ArrayList<Point>();
		path = new ArrayList<Instruction>();
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

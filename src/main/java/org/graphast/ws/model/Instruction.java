package org.graphast.ws.model;

public class Instruction {
	private int direction;
	private String label;
	private double distance;
	private double cost;
	private int beginInterval;
	private int endInterval;
	
	
	public Instruction(int direction, String label, double distance, double cost, int beginInterval, int endInterval) {
		super();
		this.direction = direction;
		this.label = label;
		this.distance = distance;
		this.cost = cost;
		this.beginInterval = beginInterval;
		this.endInterval = endInterval;
	}
	
	public Instruction() {
		
	}
	
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	public int getBeginInterval() {
		return beginInterval;
	}
	public void setBeginInterval(int beginInterval) {
		this.beginInterval = beginInterval;
	}
	public int getEndInterval() {
		return endInterval;
	}
	public void setEndInterval(int endInterval) {
		this.endInterval = endInterval;
	}
	public String getInterval() {
		return "["+ beginInterval +", " + endInterval + "]"; 
	}


	@Override
	public String toString() {
		String result = "direction: " + direction + "\n" +
						"label: " + label + "\n" +
						"distance: " + distance + "\n" +
						"cost: " + cost + "\n" +
						"interval: " + getInterval();
		return result;
	}
	
}

package ge.graphspp.main;

import java.awt.Graphics2D;

public class Edge {
	
	private Node from;
	private Node to;
	private double weight;
	
	public Edge(Node from, Node to, double weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public void draw(Graphics2D g2D) {
		g2D.setColor(MainFrame.EDGE_COLOR);
		g2D.drawLine(from.getX(),from.getY(),to.getX(),to.getY());
		
	}

	public Node getFrom() {
		return from;
	}

	public void setFrom(Node from) {
		this.from = from;
	}

	public Node getTo() {
		return to;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}

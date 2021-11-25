package ge.graphspp.main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node {

	private int x;
	private int y;
	private String lable;
	private boolean isSelected = false;

	private ArrayList<Node> neighbors;
	private ArrayList<Edge> edges;

	public Node(int x, int y, String lable) {
		this.x = x;
		this.y = y;
		this.lable = lable;
		neighbors = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}

	public void draw(Graphics2D g2D) {

		if (!isSelected)
			g2D.setColor(MainFrame.NODE_COLOR);
		else
			g2D.setColor(MainFrame.NODE_COLOR_SELECTED);

		g2D.fillOval(x - MainFrame.NODE_RADIUS, y - MainFrame.NODE_RADIUS, 2 * MainFrame.NODE_RADIUS,
				2 * MainFrame.NODE_RADIUS);
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public boolean hasEdge(Edge e) {
		return edges.contains(e);
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	public boolean hasNeighbor(Node n) {
		return neighbors.contains(n);
	}

	public void addNeibor(Node n) {
		neighbors.add(n);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

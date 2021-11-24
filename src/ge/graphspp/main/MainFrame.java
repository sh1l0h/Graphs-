package ge.graphspp.main;

import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;

public class MainFrame extends JFrame {

	public final static int WIDTH = 800;
	public final static int HEIGHT = 800;
	public final static int NODE_RADIUS = 10;
	public final static Color NODE_COLOR = Color.RED;
	public final static Color NODE_COLOR_SELECTED = Color.GRAY;
	public final static Color EDGE_COLOR = Color.BLACK;

	public static MainFrame mainFrame = null;

	private Display display;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

	public MainFrame() {

		mainFrame = this;

		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();

		display = new Display();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);

		this.add(display);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		nodes.add(new Node(100, 100, "1"));
		nodes.add(new Node(200, 200, "2"));


	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
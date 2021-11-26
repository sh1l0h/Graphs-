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
	public final static String NODE_DEFAULT_LABLE = "Default Lable";
	public final static Color EDGE_COLOR = Color.BLACK;

	public static MainFrame mainFrame = null;

	private Display display;
	
	private ArrayList<Node> nodes;
	private ArrayList<Node> selectedNodes;
	private ArrayList<Edge> edges;

	public MainFrame() {

		mainFrame = this;

		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		selectedNodes  = new ArrayList<Node>();

		display = new Display();
		
		this.setTitle("Graphs++");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.add(display);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	public ArrayList<Node> getSelectedNodes() {
		return selectedNodes;
	}
	

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}

package ge.graphspp.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JPanel implements MouseInputListener, KeyListener {

	private MainFrame mainFrame = null;

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

	private boolean isOverNode = false;
	private boolean isLocked = false;
	private Node overNode = null;

	private boolean isSelected = false;
	private Node selectedNode = null;
	
	private int mouseX;
	private int mouseY;
	
	private boolean isKeyPressed = false;

	public Display() {
		setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);

		mainFrame = MainFrame.mainFrame;

		nodes = mainFrame.getNodes();
		edges = mainFrame.getEdges();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Edge i : edges)
			i.draw(g2D);

		for (Node i : nodes)
			i.draw(g2D);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON3 && overNode != null) {

			if (overNode == selectedNode) {
				selectedNode.setSelected(false);
				isSelected = false;
				selectedNode = null;
			} else if (!isSelected) {
				overNode.setSelected(true);
				isSelected = true;
				selectedNode = overNode;
			} else if (!selectedNode.hasNeighbor(overNode)){
				edges.add(new Edge(selectedNode, overNode, 1));
				selectedNode.setSelected(false);
				selectedNode.addNeibor(overNode);
				overNode.addNeibor(selectedNode);
				isSelected = false;
				selectedNode = null;
			}

			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		isLocked = e.getButton() == MouseEvent.BUTTON1 && isOverNode;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isLocked = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (isLocked) {
			overNode.setX(mouseX);
			overNode.setY(mouseY);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (overNode != null && Maths.dist(mouseX, mouseY, overNode.getX(), overNode.getY()) > 20) {
			isOverNode = false;
			overNode = null;
		}

		for (Node i : nodes) {
			if (!isOverNode && Maths.dist(mouseX, mouseY, i.getX(), i.getY()) < 20) {
				isOverNode = true;
				overNode = i;
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("yep");
		if(!isKeyPressed && e.getKeyChar() == 'a') {
			nodes.add(new Node(mouseX,mouseY,"bla"));
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		isKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

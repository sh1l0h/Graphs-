package ge.graphspp.main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
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
	private ArrayList<Node> selectedNodes;

	private int mouseX;
	private int mouseY;

	private boolean isKeyPressed = false;

	private boolean startedSelecting = false;
	private int selectingStartX;
	private int selectingStartY;
	private int selectingEndX;
	private int selectingEndY;
	private int selectingMinX;
	private int selectingMinY;
	private int selectingMaxX;
	private int selectingMaxY;

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
		selectedNodes = mainFrame.getSelectedNodes();
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

		if (startedSelecting) {
			g2D.setColor(Color.BLACK);
			g2D.drawRect(selectingMinX, selectingMinY, selectingMaxX - selectingMinX, selectingMaxY - selectingMinY);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		isLocked = e.getButton() == MouseEvent.BUTTON1 && isOverNode;

		if (e.getButton() == MouseEvent.BUTTON1 && !isOverNode) {
			deselect();
			
			startedSelecting = true;
			selectingStartX = e.getX();
			selectingStartY = e.getY();
			selectingEndX = e.getX();
			selectingEndY = e.getY();
			selectingMinX = Math.min(selectingStartX, selectingEndX);
			selectingMinY = Math.min(selectingStartY, selectingEndY);
			selectingMaxX = Math.max(selectingStartX, selectingEndX);
			selectingMaxY = Math.max(selectingStartY, selectingEndY);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isLocked = false;

		if (startedSelecting) {
			for (Node i : nodes) {
				if (i.getX() >= selectingMinX && i.getX() <= selectingMaxX && i.getY() >= selectingMinY
						&& i.getY() <= selectingMaxY) {
					i.setSelected(true);
					selectedNodes.add(i);
				}
			}
			startedSelecting = false;
			isSelected = true;
			repaint();
		}
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

		if (startedSelecting) {
			selectingEndX = mouseX;
			selectingEndY = mouseY;
			selectingMinX = Math.min(selectingStartX, selectingEndX);
			selectingMinY = Math.min(selectingStartY, selectingEndY);
			selectingMaxX = Math.max(selectingStartX, selectingEndX);
			selectingMaxY = Math.max(selectingStartY, selectingEndY);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (overNode != null && Maths.dist(mouseX, mouseY, overNode.getX(), overNode.getY()) > MainFrame.NODE_RADIUS) {
			isOverNode = false;
			overNode = null;
		}

		for (Node i : nodes) {
			if (!isOverNode && Maths.dist(mouseX, mouseY, i.getX(), i.getY()) < MainFrame.NODE_RADIUS) {
				isOverNode = true;
				overNode = i;
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed && !isOverNode && e.getKeyCode() == KeyEvent.VK_A) {
			Node newNode = new Node(mouseX, mouseY, MainFrame.NODE_DEFAULT_LABLE);
			nodes.add(newNode);
			isOverNode = true;
			overNode = newNode;
			repaint();
		} else if (!isKeyPressed && isSelected && e.getKeyCode() == KeyEvent.VK_R) {
			for (Node i : selectedNodes)
				edges.removeAll(i.getEdges());
			nodes.removeAll(selectedNodes);
			selectedNodes.clear();
			isSelected = true;
			repaint();
		} else if (!isKeyPressed && isSelected && e.getKeyCode() == KeyEvent.VK_E) {
			for (int i = 0; i < selectedNodes.size() - 1; i++) {
				Node current = selectedNodes.get(i);
				for (int j = i + 1; j < selectedNodes.size(); j++) {
					Node temp = selectedNodes.get(j);

					if(current.hasNeighbor(temp)) 
						continue;
						
					Edge newEdge = new Edge(current, temp, 1);

					current.addNeibor(temp);
					temp.addNeibor(current);

					edges.add(newEdge);
					current.addEdge(newEdge);
					temp.addEdge(newEdge);
				}
			}
			deselect();
			repaint();
		}
		
		isKeyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		isKeyPressed = false;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	public void deselect() {
		
		for(Node i : selectedNodes)
			i.setSelected(false);
		
		selectedNodes.clear();
	}

}

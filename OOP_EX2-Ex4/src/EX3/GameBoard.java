package EX3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Geom.Point3D;

/**
 * This class represents a JPanel that displays the map and all of the objects: packmans and fruits, and animates them.
 */
public class GameBoard extends JPanel
implements Runnable, MouseListener {

	private final int B_WIDTH = 1433;
	private final int B_HEIGHT = 642;
	private final int INITIAL_X = -40;
	private final int INITIAL_Y = -40;
	private final int DELAY = 0;

	private Image mapImg;
	private Image packmanImg;
	private Image fruitImg;
	private Thread animator;

	private Map map;
	public Game game;
	public Simulate simulate;
	boolean running = true;
	ArrayList<Thread> threads = new ArrayList<Thread>();
	
	Thread t1;
	
	int packOrFruit = 0;

	public GameBoard() {

		initBoard();
	}

	/**
	 * Loads the necessary images into variables
	 */
	private void loadImage() {

		ImageIcon mapIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/Ariel1.png");
		ImageIcon packmanIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/packman.png");
		ImageIcon fruitIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/fruit.png");
		mapImg = mapIcon.getImage();
		packmanImg = packmanIcon.getImage();
		fruitImg = fruitIcon.getImage();
	}

	/**
	 * Initilize the JPanel: windows size, background...
	 */
	private void initBoard() {

		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

		loadImage();

		map = new Map();
		game = new Game();
		
		
		super.addMouseListener(this);

	}
	
	/**
	 * Runs the thread that does the calculations for moving the packmans
	 */
	public void runSimulate() {
		t1 = new Thread(simulate);
		t1.start();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapImg, 0, 0, null);

		drawGame(g);
	}

	/**
	 * Draws the packmans and fruits
	 */
	private void drawGame(Graphics g) {

		drawAllPackmans(g);
		drawAllFruits(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private void drawAllPackmans(Graphics g) {
		Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
		while(pack_it.hasNext()) {
			Packman curr_pack = pack_it.next();
			drawPackman(curr_pack, g);
		}
	}

	private void drawAllFruits(Graphics g) {
		Iterator<Fruit> fruit_it = game.getFruitArrayList().iterator();
		while(fruit_it.hasNext()) {
			Fruit curr_fruit = fruit_it.next();
			drawFruit(curr_fruit, g);
		}
	}

	private void drawPackman(Packman p, Graphics g) {
		Point3D pointInPixels = map.pointToPixels(p.location);
		g.drawImage(packmanImg, (int)pointInPixels.x(), (int)pointInPixels.y(), 40, 40, null);
	}

	private void drawFruit(Fruit f, Graphics g) {
		Point3D pointInPixels = map.pointToPixels(f.location);
		if (!f.eaten) {
			g.drawImage(fruitImg, (int)pointInPixels.x(), (int)pointInPixels.y(), 40, 40, null);
		}
	}

	/**
	 * Create a thread for each packman and start it
	 */
	public void runPackmans() {
		Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
		while(pack_it.hasNext()) {
			threads.add(new Thread(pack_it.next()));
		}
		Iterator<Thread> trd_it = threads.iterator();
		while(trd_it.hasNext()) {
			trd_it.next().start();
		}
	}

	@Override
	public void run() {
		while (true) {
			repaint();	
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Adds a packman/fruit on the screen where the mouse is clicked
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		Point3D mousePixel = new Point3D(arg0.getX(), arg0.getY());
		if (packOrFruit == 0) {
			Packman new_pack = new Packman(0, map.pixelsToPoint(mousePixel), 1, 1, 1);
			game.addPackman(new_pack);
			threads.add(new Thread(new_pack));
			threads.get(threads.size()-1).start();
		}
		else{
			game.addFruit(new Fruit(0, map.pixelsToPoint(mousePixel), 1, 1));
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
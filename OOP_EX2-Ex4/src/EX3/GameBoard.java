package EX3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Geom.Point3D;

public class GameBoard extends JPanel
implements Runnable {

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
	boolean running = true;

	public GameBoard() {

		initBoard();
	}

	private void loadImage() {

		ImageIcon mapIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/Ariel1.png");
		ImageIcon packmanIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/packman.png");
		ImageIcon fruitIcon = new ImageIcon("C:/Users/Yosi/git/OOP_EX2-EX4/data/fruit.png");
		mapImg = mapIcon.getImage();
		packmanImg = packmanIcon.getImage();
		fruitImg = fruitIcon.getImage();
	}

	private void initBoard() {

		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

		loadImage();

		map = new Map();
		game = new Game();

		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		spa.findPaths();
		
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

	private void cycle() {
		if (running) {
			Iterator<Packman> it = game.getPackmanArrayList().iterator();
			while (it.hasNext()) {
				Packman curr_pack = it.next();
				Fruit destination = curr_pack.path.points.get(curr_pack.dest_id);
				curr_pack.moveInDirection(destination, DELAY);
			}
		}
		
	}

	@Override
	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			cycle();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {

				String msg = String.format("Thread interrupted: %s", e.getMessage());

				JOptionPane.showMessageDialog(this, msg, "Error", 
						JOptionPane.ERROR_MESSAGE);
			}

			beforeTime = System.currentTimeMillis();
		}
	}
}
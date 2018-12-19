package EX3;
import java.awt.Color;
/**
 * Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Coords.MyCoords;
import Geom.Point3D;
public class MyFrame2 extends JPanel{


	public void paint(Graphics g){
//		Image map = Toolkit.getDefaultToolkit().getImage("data//Ariel1.png");
		super.paintComponents(g);
		BufferedImage map_img;
		try {
			map_img = ImageIO.read(new File("data//Ariel1.png"));
			int w = map_img.getWidth();
			int h = map_img.getHeight();
			g.drawImage(map_img, 0, 0, null);
			System.out.println("paint functions");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showGame(Graphics g, Game game) throws IOException {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Map map = new Map("data//Ariel1.png", start, end);
		drawGame(game, g, map);
	}
	public void drawGame(Game game, Graphics g, Map map) throws IOException {
		BufferedImage map_img;
		map_img = ImageIO.read(new File("data//Ariel1.png"));
		g.drawImage(map_img, 0, 0, null);
		Iterator<Packman> it = game.getPackmanArrayList().iterator();
        while (it.hasNext()) {
        	Packman packman = it.next();
        	try {
				drawPackman(packman, g, map);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        Iterator<Fruit> it2 = game.getFruitArrayList().iterator();
        while (it2.hasNext()) {
        	Fruit fruit = it2.next();
        	try {
				drawFruit(fruit, g, map);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	public void drawPackman(Packman packman, Graphics g, Map map) throws IOException {
		Point3D pointInPixels = map.pointToPixels(packman.location);
//		BufferedImage mapImage = map.mapImage;
		BufferedImage packmanImage = ImageIO.read(new File("data//packman.png"));
		g.drawImage(packmanImage, (int)pointInPixels.x(), (int)pointInPixels.y(), packman.radius*30, packman.radius*30, null);
	}
	
	public void drawFruit(Fruit fruit, Graphics g, Map map) throws IOException {
		Point3D pointInPixels = map.pointToPixels(fruit.location);
//		BufferedImage mapImage = map.mapImage;
		BufferedImage packmanImage = ImageIO.read(new File("data//fruit.png"));
		g.drawImage(packmanImage, (int)pointInPixels.x(), (int)pointInPixels.y(), 30, 30, null);
	}
	
	public static void main(String[] args){
//		JFrame frame= new JFrame("JavaTutorial.net");	
//		frame.getContentPane().add(new MyFrame2());
//		frame.setSize(300, 300);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(true);
//		frame.setName("JFrame example");
	}
}
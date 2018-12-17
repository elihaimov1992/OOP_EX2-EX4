package EX3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.util.concurrent.TimeUnit;

import Geom.Point3D;

public class MyFrame {

	Map map;
	Game game;
	
	public MyFrame(Map map, Game game) {
		this.map = map;
		this.game = game;
	}
	
	public void show() throws IOException {
		BufferedImage mapImage = map.mapImage;
		BufferedImage packmanImage = ImageIO.read(new File("data//packman.png"));
		Graphics g = mapImage.getGraphics();
		ImageIcon icon=new ImageIcon(mapImage);
		JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(mapImage.getWidth()+17,mapImage.getHeight()+43);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        
        Iterator<Packman> it = game.getPackmanArrayList().iterator();
        while (it.hasNext()) {
        	Packman packman = it.next();
        	drawPackman(packman);
        }
        Iterator<Fruit> it2 = game.getFruitArrayList().iterator();
        while (it2.hasNext()) {
        	Fruit fruit = it2.next();
        	drawFruit(fruit);
        }
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        for (int i = 0; i < 1000; i++) {
//            g.drawImage(packmanImage, i, i, 30, 30, null);
//            SwingUtilities.updateComponentTreeUI(frame);
//            try {
//    			TimeUnit.MILLISECONDS.sleep(100);
//    		} catch (InterruptedException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//		}
        
        
	}
	
	public void drawPackman(Packman packman) throws IOException {
		Point3D pointInPixels = map.pointToPixels(packman.location);
		BufferedImage mapImage = map.mapImage;
		BufferedImage packmanImage = ImageIO.read(new File("data//packman.png"));
		Graphics g = mapImage.getGraphics();
		g.drawImage(packmanImage, (int)pointInPixels.x(), (int)pointInPixels.y(), packman.radius*30, packman.radius*30, null);
	}
	
	public void drawFruit(Fruit fruit) throws IOException {
		Point3D pointInPixels = map.pointToPixels(fruit.location);
		BufferedImage mapImage = map.mapImage;
		BufferedImage packmanImage = ImageIO.read(new File("data//fruit.png"));
		Graphics g = mapImage.getGraphics();
		g.drawImage(packmanImage, (int)pointInPixels.x(), (int)pointInPixels.y(), 30, 30, null);
	}
	
	public static void main(String[] args) throws IOException {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Map map = new Map("data//Ariel1.png", start, end);
		Game game = new Game("data//game0.csv");
		
		MyFrame mf = new MyFrame(map, game);
		mf.show();
	}

}

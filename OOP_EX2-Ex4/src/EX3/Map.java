package EX3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import Coords.MyCoords;
import Geom.Point3D;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Map {

	// we used the ImageJ library to resize the image
	// ariel start: 32.105716, 35.202373
	// ariel end: 32.101911, 35.212528
	
	ImagePlus img;
	Point3D start;
	Point3D end;	
	
	Map(String file_path, Point3D start, Point3D end) {
		MyCoords mc = new MyCoords();
		this.start = new Point3D(start);
		this.end = new Point3D(end);
		Point3D up_right = new Point3D(start.x(), end.y());
		Point3D down_left = new Point3D(end.x(), start.y());
		double width_meters = mc.distance3d(start, up_right);
		double height_meters = mc.distance3d(start, down_left);
		
		
		ImagePlus imp = IJ.openImage("data//Ariel1.png");
		ImageProcessor ip = imp.getProcessor();
		ImageProcessor ip2 = ip.resize(imp.getWidth(), imp.getHeight());
		imp.setProcessor(ip2);
//		imp.show();
		System.out.println("width_meters = "+width_meters);
		System.out.println("height_meters = "+height_meters);
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Point3D up_right = new Point3D(32.105716, 35.217587);
		Point3D down_left = new Point3D(32.101911, 35.202373);
		Map m = new Map("data//Ariel1.png", start, end);
		
		MyCoords mc = new MyCoords();
		System.out.println(mc.distance3d(start, up_right));
		
		
		

	
				
				
		
				
	}
	

}

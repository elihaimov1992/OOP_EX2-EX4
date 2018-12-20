package EX3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import Coords.MyCoords;
import Geom.Point3D;

public class Map {

	// we used the ImageJ library to resize the image
	// ariel start: 32.105716, 35.202373
	// ariel end: 32.101911, 35.212528
	
	BufferedImage mapImage = null;
	Point3D start;
	Point3D end;
	Game game;
	int mapWidth, mapHeight;
	
	public Map(String file_path, Point3D start, Point3D end) {
		MyCoords mc = new MyCoords();
		this.start = new Point3D(start);
		this.end = new Point3D(end);
		
		try 
		{
			mapImage = ImageIO.read(new File("data//Ariel1.png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		mapWidth = mapImage.getWidth();
		mapHeight = mapImage.getHeight();
	}
	
	public Map() {
		this.start = new Point3D(32.105716, 35.202373);
		this.end = new Point3D(32.101911, 35.212528);
		try {
			mapImage = ImageIO.read(new File("data//Ariel1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapWidth = mapImage.getWidth();
		mapHeight = mapImage.getHeight();
	}

	/**
	 * Gets a point in latitude and longitude and returns a point in pixels on the image
	 * We used stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
	 * @param coordinate
	 * @return
	 */
	public Point3D pointToPixels(Point3D latLonPoint) {
		double mapLatDiff = start.x() - end.x();
		double mapLongDiff = end.y() - start.y();
		
		double latDiff = start.x() - latLonPoint.x();
	    double longDiff = latLonPoint.y() - start.y();
	 
	    int x = (int) (mapWidth*(longDiff/mapLongDiff));
	    int y = (int) (mapHeight*(latDiff/mapLatDiff));

	    return new Point3D(x, y);
	}
	
	/**
	 * Gets a point in pixels and returns a point in latitude and longitude
	 * We used stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
	 * @param coordinate
	 * @return
	 */
	public Point3D pixelsToPoint(Point3D pixelsPoint) {
		double mapLatDiff = start.x() - end.x();
		double mapLongDiff = end.y() - start.y();

	    double latDiff = pixelsPoint.y() * mapLatDiff/mapHeight;
	    double longDiff = pixelsPoint.x() * mapLongDiff/mapWidth;
	    
	    double newLat = start.x() - latDiff;
	    double newLong = start.y() + longDiff;
	    
	    return new Point3D(newLat, newLong);
	}
	
	/**
	 * Calculates distance in meters between two pixel points on the image
	 * @param pix0
	 * @param pix1
	 * @return
	 */
	public double distanceBetweenPixels(Point3D pix0, Point3D pix1) {
		Point3D p0 = pixelsToPoint(pix0);
		Point3D p1 = pixelsToPoint(pix1);
		MyCoords mc = new MyCoords();
		return mc.distance3d(p0, p1);
	}
	
	/**
	 * Calculates azimuth (angle) between two pixel points on the image
	 * @param pix0
	 * @param pix1
	 * @return
	 */
	public double azimuthBetweenPixels(Point3D pix0, Point3D pix1) {
		Point3D p0 = pixelsToPoint(pix0);
		Point3D p1 = pixelsToPoint(pix1);
		MyCoords mc = new MyCoords();
		return mc.azimuth_elevation_dist(p0, p1)[0];
	}

	public static void main(String[] args) {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Point3D center = new Point3D(32.1038135, 35.2074505);

		Map m = new Map("data//Ariel1.png", start, end);
		Point3D centerInPixels = m.pointToPixels(center);
		Point3D startInPixels = new Point3D(0,0);
		Point3D endInPixels = new Point3D(m.mapWidth, m.mapHeight);
		System.out.println(centerInPixels);
		System.out.println(m.pixelsToPoint(centerInPixels));
		System.out.println(m.distanceBetweenPixels(startInPixels, endInPixels));
		
		
		
		

	
				
				
		
				
	}
	

}

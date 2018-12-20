package EX3;

import Coords.MyCoords;
import Geom.Point3D;

public class Packman {

	Point3D location;
	double orientation;
	int speed;
	int radius;
	int id;
	Path path;
	
	Packman(int id, Point3D location, int speed, int radius) {
		this.id = id;
		this.location = new Point3D(location);
		this.speed = speed;
		this.radius = radius;
		path = new Path();
		path.addPoint(location);
	}
	
	
	
	public void moveInDirection(Point3D destination, double delay) {
		MyCoords mc = new MyCoords();
		Map map = new Map();
		double azimuth = mc.azimuth_elevation_dist(this.location, destination)[0];
		double metersToMove = this.speed*(delay/1000);
		Point3D newPoint = mc.addMetersAzimuth(this.location, metersToMove, azimuth);
		this.move(newPoint);
		System.out.println(metersToMove);
	}
	
	public void move(Point3D new_location) {
		location = new_location;
	}
	
	public String toString() {
		return "("+id+","+location+","+speed+","+radius+")";
	}
	
	public static void main(String[] args) {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D center = new Point3D(32.1038135, 35.2074505);
		Packman pac = new Packman(1,start,2,3);
//		System.out.println(pac);
		
		MyCoords mc = new MyCoords();
		
		double R = 6378.1;
		double brng = Math.toRadians(mc.azimuth_elevation_dist(start, center)[0]);
		double d = 0.0000000000001;
		
		double lat1 = Math.toRadians(start.x());
		double lon1 = Math.toRadians(start.y());
		
		double lat2 = Math.asin( Math.sin(lat1)*Math.cos(d/R) +
				Math.cos(lat1)*Math.sin(d/R)*Math.cos(brng));
		
		double lon2 = lon1 + Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(lat1),
				Math.cos(d/R)-Math.sin(lat1)*Math.sin(lat2));

		lat2 = Math.toDegrees(lat2);
		lon2 = Math.toDegrees(lon2);
		
		System.out.println(start);
		System.out.println(new Point3D(lat2, lon2));
	}

}

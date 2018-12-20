package EX3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Coords.MyCoords;
import Geom.Point3D;

public class Packman {

	Point3D location;
	Point3D curr_destination;
	int dest_id;
	double orientation;
	int speed;
	int radius;
	int id;
	int num;
	Path path;
	
	Packman(int num, Point3D location, int speed, int radius, int id) {
		this.id = id;
		this.location = new Point3D(location);
		this.speed = speed;
		this.radius = radius;
		this.num = num;
		path = new Path();
		Fruit empty = new Fruit(-1, location, 0, -1);
		path.addPoint(empty);
		dest_id = 0;
	}
	
	
	
	public void moveInDirection(Fruit dest_fruit, double delay) {
		if (location == path.points.get(0).location) {
			path.times.add(new Date());
		}
		if (canEatFruit(dest_fruit)) {
			dest_fruit.eaten = true;
			if (dest_id < path.points.size()-2) {
				dest_id++;
				this.curr_destination = path.points.get(dest_id).location;
				Date currdate = new Date();
				path.times.add(currdate);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			}
			else {
				if (!path.saved2KML) {
					Date currdate = new Date();
					path.times.add(currdate);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Path2KML p2k = new Path2KML(path);
					p2k.toKml();
					path.saved2KML = true;
				}
				
			}
			
		}
		else {
			MyCoords mc = new MyCoords();
			Map map = new Map();
			double azimuth = mc.azimuth_elevation_dist(this.location, dest_fruit.location)[0];
			double metersToMove = this.speed*10;
			Point3D newPoint = mc.addMetersAzimuth(this.location, metersToMove, azimuth);
			this.move(newPoint);
//			System.out.println(metersToMove);
		}
	}
	
	public void move(Point3D new_location) {
		location = new_location;
	}
	
	private boolean canEatFruit(Fruit f) {
		MyCoords mc = new MyCoords();
		return mc.distance3d(this.location, f.location) <= this.radius*10;
	}
	
	public void setCurrDestination(Point3D point) {
		curr_destination = point;
	}
	
	public String toString() {
		return "("+id+","+location+","+speed+","+radius+")";
	}
	
	public static void main(String[] args) {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D center = new Point3D(32.1038135, 35.2074505);
		Packman pac = new Packman(1,start,2,3, -1);
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

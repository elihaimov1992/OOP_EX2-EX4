package EX3;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;

public class Path {
	ArrayList<Point3D> points;
	private double time_to_finish;
	
	public Path() {
		points = new ArrayList<Point3D>();
		time_to_finish = 0;
	}
	
	public void add(Point3D point) {
		points.add(point);
	}
	
	public void setTime(double time) {
		time_to_finish = time;
	}
	
	public double getTime() {
		return time_to_finish;
	}
	
	public double distance() {
		double distance = 0;
		MyCoords mc = new MyCoords();
		Iterator<Point3D> it0 = points.iterator();
		Iterator<Point3D> it1 = points.iterator();
		it1.next();
		while (it1.hasNext()) {
			Point3D curr = it0.next();
			Point3D next = it1.next();
			distance += mc.distance3d(curr, next);
		}
//		distance += mc.distance3d(points.get(points.size()-1), points.get(points.size()-2));
		return distance;
	}
	
	public static void main(String[] args) {
		Path path = new Path();
		Point3D p0 = new Point3D(32.105716, 35.202373);
		Point3D p1 = new Point3D(32.101911, 35.212528);
		Point3D p2 = new Point3D(32.103727, 35.197333);
		Point3D p3 = new Point3D(32.104481, 35.194573);
		path.add(p0); path.add(p1); path.add(p2); path.add(p3);
		System.out.println(path.distance());
	}
	
}

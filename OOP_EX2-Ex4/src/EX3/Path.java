package EX3;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;

public class Path {
	ArrayList<Point3D> points;
	ArrayList<Date> times;
	
	public Path() {
		points = new ArrayList<Point3D>();
		times = new ArrayList<Date>();
	}
	
	public void addPoint(Point3D point) {
		points.add(point);
	}
	
	public void addTime(Date time) {
		times.add(time);
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
	
	public String toString() {
		Iterator<Point3D> it = points.iterator();
		String ans = "";
		while (it.hasNext()) {
			Point3D curr_point = it.next();
			ans += curr_point + " -> ";
		}
		return ans;
	}
	
	public static void main(String[] args) {
//		Path path = new Path();
//		Point3D p0 = new Point3D(32.105716, 35.202373);
//		Point3D p1 = new Point3D(32.101911, 35.212528);
//		Point3D p2 = new Point3D(32.103727, 35.197333);
//		Point3D p3 = new Point3D(32.104481, 35.194573);
//		path.add(p0); path.add(p1); path.add(p2); path.add(p3);
//		System.out.println(path.distance());
	}
	
}

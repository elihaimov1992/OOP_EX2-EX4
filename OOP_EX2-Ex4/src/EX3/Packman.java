package EX3;

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
	}
	
	public String toString() {
		return "("+id+","+location+","+speed+","+radius+")";
	}
	
	public static void main(String[] args) {
		Point3D point = new Point3D(0,0);
		Packman pac = new Packman(1,point,2,3);
		System.out.println(pac);
	}

}

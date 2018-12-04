package EX3;

import Geom.Point3D;

public class Packman {

	Point3D location;
	double orientation;
	int speed;
	int radius;
	int id;
	
	Packman(int id, Point3D location, int speed, int radius) {
		this.id = id;
		this.location = new Point3D(location);
		this.speed = speed;
		this.radius = radius;
	}
	
	
	public static void main(String[] args) {

	}

}

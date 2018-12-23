package EX3;

import Geom.Point3D;

public class Fruit {

	Point3D location;
	int weight;
	int id;
	int num;
	boolean eaten;
	boolean chosen;
	
	public Fruit(int num, Point3D location, int weight, int id) {
		this.location = new Point3D(location);
		this.weight = weight;
		this.id = id;
		this.num = num;
		eaten = false;
		chosen = false;
	}
	
	public String toString() {
		return "("+id+","+location+","+weight+")";
	}

	public static void main(String[] args) {
		

	}

}

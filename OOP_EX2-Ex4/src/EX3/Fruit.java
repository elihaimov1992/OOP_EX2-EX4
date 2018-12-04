package EX3;

import Geom.Point3D;

public class Fruit {

	Point3D location;
	int weight;
	int id;
	
	public Fruit(int id, Point3D location, int weight) {
		this.location = new Point3D(location);
		this.weight = weight;
		this.id = id;
	}
	
	public String toString() {
		return "("+id+","+location+","+weight+")";
	}

	public static void main(String[] args) {
		

	}

}

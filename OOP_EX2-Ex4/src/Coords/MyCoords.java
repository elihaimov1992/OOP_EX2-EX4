package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{

	public static void main(String[] args) {

	}

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D new_point = new Point3D(gps);
		new_point.add(local_vector_in_meter);
		return new_point;
	}

	@Override
	/**
	 * We used the equations from this webstite:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude
	 */
	public double distance3d(Point3D gps0, Point3D gps1) {
		int radius = 6371;
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		double a = Math.pow(Math.sin(dx/2), 2) + Math.cos(gps0.x()) * Math.cos(gps1.x()) * Math.pow(Math.sin(dy/2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return radius * c;
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double x_diff = gps0.distance3D(gps1.x(),gps0.y(),gps0.z());
		double y_diff = gps0.distance3D(gps0.x(),gps1.y(),gps0.z());
		double z_diff = gps0.distance3D(gps0.x(),gps0.y(),gps1.z());
		return new Point3D(x_diff,y_diff,z_diff);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] answer = new double[3];
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		double azimuth = Math.atan2(Math.sin(dy) * Math.cos(gps1.x()), Math.cos(gps0.x()) * Math.sin(gps1.x()) - Math.sin(gps0.x())
						 * Math.cos(gps1.x()) * Math.cos(dy));
		double distance = distance3d(gps0, gps1);
		answer[0] = azimuth;
//		answer[1] = elevation;
		answer[2] = distance;
		return answer;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean con1 = (-180<=p.x() && p.x()<=180);
		boolean con2 = (-90<=p.y() && p.y()<=90);
		boolean con3 = (-450<=p.z());
		return con1 && con2 && con3;
	}

}

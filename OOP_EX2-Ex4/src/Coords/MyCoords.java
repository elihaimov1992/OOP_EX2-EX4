package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{

	public static void main(String[] args) {
		MyCoords mc = new MyCoords();
		Point3D p0 = new Point3D(32, 45);
		Point3D p1 = new Point3D(33, 46);
		System.out.println("Azimuth = " + mc.azimuth_elevation_dist(p0, p1)[0]);
		System.out.println("Elevation = " + mc.azimuth_elevation_dist(p0, p1)[1]);
		System.out.println("Distance = " + mc.azimuth_elevation_dist(p0, p1)[2]);
	}

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D new_point = new Point3D(gps);
		new_point.add(local_vector_in_meter);
		return new_point;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		int radius = 6371000;
		double lat0 = Math.toRadians(gps0.x()); double lon0 = Math.toRadians(gps0.y());
		double lat1 = Math.toRadians(gps1.x()); double lon1 = Math.toRadians(gps1.y());
		double dlat = Math.toRadians(gps1.x() - gps0.x());
		double dlon = Math.toRadians(gps1.y() - gps0.y());
		double a = Math.pow(Math.sin(dlat/2),2) + Math.cos(lat0) * Math.cos(lat1) * Math.pow(Math.sin(dlon/2),2);
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
	/**
	 * We used these websites:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude
	 * https://www.photopills.com/articles/understanding-azimuth-and-elevation
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] answer = new double[3];
		double lat0 = Math.toRadians(gps0.x()); double lon0 = Math.toRadians(gps0.y());
		double lat1 = Math.toRadians(gps1.x()); double lon1 = Math.toRadians(gps1.y());
		double dlat = Math.toRadians(gps1.x() - gps0.x());
		double dlon = Math.toRadians(gps1.y() - gps0.y());
		double azimuth = Math.atan2(Math.sin(dlon) * Math.cos(lat1), Math.cos(lat0) * Math.sin(lat1) - Math.sin(lat0)
						 * Math.cos(lat1) * Math.cos(dlon));
		double distance = distance3d(gps0, gps1);
		double elevation = Math.asin(distance3d(new Point3D(lat1, lat0), gps1)/distance3d(gps0, gps1));
		answer[0] = Math.toDegrees(azimuth);
		answer[1] = elevation;
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

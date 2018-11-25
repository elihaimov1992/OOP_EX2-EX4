package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{

	public static void main(String[] args) {
		MyCoords mc = new MyCoords();
		Point3D p0 = new Point3D(32, 45,120);
		Point3D p1 = new Point3D(60, 102,900);
		Point3D meters = new Point3D(100000000,40030);
		Point3D p2 = new Point3D(0,360);
		System.out.println("Azimuth = " + mc.azimuth_elevation_dist(p0, p1)[0]);
		System.out.println("Elevation = " + mc.azimuth_elevation_dist(p0, p1)[1]);
		System.out.println("Distance = " + mc.azimuth_elevation_dist(p0, p1)[2]);
		System.out.println(mc.add(p0,meters));
		//System.out.println(mc.vector3D(p0,p2));
	}
	
	/**
	 * We used these websites:
	 * stackoverflow.com/questions/7477003/calculating-new-longitude-latitude-from-old-n-meters
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		int radius = 6371000;
		double meter_inDegree_lat = 111000;   // 111 km  
		double meter_inDegree_lon = (((2.0 * Math.PI / 360.0) * radius * Math.cos(gps.x()))/1000.0);   
		double new_latitude= gps.x() + (1.0/meter_inDegree_lat) * local_vector_in_meter.x();
		double new_longitude= gps.y() + (1.0/meter_inDegree_lon) * local_vector_in_meter.y();
		if(new_longitude>0){
			while(new_longitude>180){
				new_longitude=new_longitude-180;
			}
		}
		else if(new_longitude<0) {
			while(new_longitude<180){
				new_longitude=new_longitude+180;
			}
		}
		if(new_latitude>0){
			while(new_latitude>90){
				new_latitude=new_latitude-90;
			}
		}
		else if(new_latitude<0) {
			while(new_latitude<90){
				new_latitude=new_latitude+90;
			}
		}
		return new Point3D(new_latitude,new_longitude);
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
		int radius = 6371000;
		double meter_inDegree_lat = 111000;   // 111 km  
		double meter_inDegree_lon = (((2.0 * Math.PI / 360.0) * radius * Math.cos(gps0.x()))/1000.0);
		double new_latitude=meter_inDegree_lat*(gps1.x()-gps0.x());
		double new_longitude=meter_inDegree_lon*(gps1.y()-gps0.y());
		return new Point3D(new_latitude,new_longitude);
	}

	@Override
	/**
	 * We used these websites:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude
	 * https://www.photopills.com/articles/understanding-azimuth-and-elevation
	 * http://tchester.org/sgm/analysis/peaks/how_to_get_view_params.html#elevation
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		int radius = 6371000;
		double[] answer = new double[3];
		double lat0 = Math.toRadians(gps0.x()); double lon0 = Math.toRadians(gps0.y());
		double lat1 = Math.toRadians(gps1.x()); double lon1 = Math.toRadians(gps1.y());
		double dlat = Math.toRadians(gps1.x() - gps0.x());
		double dlon = Math.toRadians(gps1.y() - gps0.y());
		double azimuth = Math.atan2(Math.sin(dlon) * Math.cos(lat1), Math.cos(lat0) * Math.sin(lat1) - Math.sin(lat0)
						 * Math.cos(lat1) * Math.cos(dlon));
		double distance = distance3d(gps0, gps1);
		double elevation = Math.toDegrees(((gps1.z()-gps0.z())/distance)-(distance/(2*radius)));
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

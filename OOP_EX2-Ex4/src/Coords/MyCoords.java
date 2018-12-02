package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{


	/**
	 * We used these websites:
	 * stackoverflow.com/questions/7477003/calculating-new-longitude-latitude-from-old-n-meters
	 */
	
	
	/**
	 * This function add two points together
	 * @return new point
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
	

	/**
	 * This function calculate the distance between two points
	 * @return distance
	 */
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

	/**
	 * This function returns a point in meters that when we add it to gps0 we will arrive at gps1
	 * @return the vector
	 */
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
	 * this function calculates the azimuth, elevation and distance between two points.
	 * We used these web sites:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude
	 * https://www.photopills.com/articles/understanding-azimuth-and-elevation
	 * http://tchester.org/sgm/analysis/peaks/how_to_get_view_params.html#elevation
	 * @return an array containing the three answers
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
	/**
	 * this function check if the point is valid.
	 * @return true - if yes, else false.
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		boolean con1 = (-180<=p.x() && p.x()<=180);
		boolean con2 = (-90<=p.y() && p.y()<=90);
		boolean con3 = (-450<=p.z());
		return con1 && con2 && con3;
	}

}

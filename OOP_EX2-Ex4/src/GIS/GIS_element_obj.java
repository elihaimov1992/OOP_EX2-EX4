package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class GIS_element_obj implements GIS_element{

	private Meta_data_obj data = new Meta_data_obj();
	
	@Override
	public Geom_element getGeom() {
		return data.getPoint();
	}

	@Override
	public Meta_data getData() {
		return data;
	}
	
	public void setData(String name, String bssid, String capabilities, String dateString, double currentLatitude, double currentLongitude,
			double altitudeMeters) {
		data.set_meta_data(name, bssid, capabilities, dateString, currentLatitude, currentLongitude, altitudeMeters);
	}

	/**
	 * This function moves a point by a given vector
	 */
	@Override
	public void translate(Point3D vec) {
		MyCoords mc = new MyCoords();
		mc.add(data.getPoint(), vec);
	}
	
}

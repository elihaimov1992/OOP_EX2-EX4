package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;
//import gapchenko.llttz.Converter;
//import gapchenko.llttz.IConverter;
//import gapchenko.llttz.stores.TimeZoneListStore;

public class Meta_data_obj implements Meta_data{

	private Point3D point = new Point3D(0,0,0);
	private String name;
	private String bssid;
	private String capabilities;
	private int frequency;
	private long timestamp;
	private Date date;
	private double currentAltitude;
	private double currentLongitude;
	private double altitudeMeters;
	
	public void set_meta_data(String name, String bssid, String capabilities, int frequency, long timestamp, String dateString,
			double currentAltitude, double currentLongitude, double altitudeMeters) {
		this.name = name;
		this.bssid = bssid;
		this.capabilities = capabilities;
		this.frequency = frequency;
		this.timestamp = timestamp;
		this.currentAltitude=currentAltitude;
		this.currentLongitude=currentLongitude;
		this.altitudeMeters=altitudeMeters;
		setDate(dateString);
	}
	
	public Point3D getPoint() {
		return point;
	}
	
	@Override
	/**
	 * This function uses the library Llttz
	 * https://github.com/agap/llttz
	 */
//	public long getUTC() {
//		IConverter iconv = Converter.getInstance(TimeZoneListStore.class);
//		TimeZone tz = iconv.getTimeZone(point.x(), point.y());
//		return tz.getRawOffset();
//	}
	
	public long getUTC() {
		return timestamp;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	private void setDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse (dateString);
			this.date = date;
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
	}

}

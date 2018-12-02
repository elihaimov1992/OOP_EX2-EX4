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
	private long timestamp;
	private Date date;
	private double currentLatitude;
	private double currentLongitude;
	
	public String getName() {
		return name;
	}

	public String getBssid() {
		return bssid;
	}

	public String getCapabilities() {
		return capabilities;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Date getDate() {
		return date;
	}

	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public double getAltitudeMeters() {
		return altitudeMeters;
	}

	private double altitudeMeters;
	
	public void set_meta_data(String name, String bssid, String capabilities, String dateString,
			double currentLatitude, double currentLongitude, double altitudeMeters) {
		this.name = name;
		this.bssid = bssid;
		this.capabilities = capabilities;
		this.currentLatitude=currentLatitude;
		this.currentLongitude=currentLongitude;
		this.altitudeMeters=altitudeMeters;
		setDate(dateString);
		this.timestamp = date.getTime();
	}
	
	public Point3D getPoint() {
		return point;
	}
	
	@Override
	/**
	 * This function returns the timezone the point is in
	 * This function uses the library Llttz
	 * https://github.com/agap/llttz
	 */
//	public long getUTC() {
//		IConverter iconv = Converter.getInstance(TimeZoneListStore.class);
//		TimeZone tz = iconv.getTimeZone(point.x(), point.y());
//		return tz.getRawOffset();
//	}
	
	/**
	 * Returns the time in milliseconds the passed from 1.1.1970
	 */
	public long getUTC() {
		return timestamp;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	/**
	 * Helper function that takes the date string from the csv file and turns it into a java date object
	 * @param dateString
	 */
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

package GIS;

import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;
import gapchenko.llttz.Converter;
import gapchenko.llttz.IConverter;
import gapchenko.llttz.stores.TimeZoneListStore;

public class Meta_data_obj implements Meta_data{

	private Point3D point = new Point3D(0,0,0);
	private String mac;
	private String ssid;
	private String authMode;
	private Date firstSeen;
	private int channel;
	private int rssi;
	private double currentAltitude;
	private double currentLongitude;
	private double altitudeMeters;
	private double accuracyMeters;
	private String type;
	
	public void set_meta_data(String mac, String ssid, String authMode, Date firstSeen, int channel, int rssi,
			double currentAltitude, double currentLongitude, double altitudeMeters, double accuracyMeters, String type) {
		this.mac = mac;
		this.ssid = ssid;
		this.authMode = authMode;
		this.firstSeen = firstSeen;
		this.channel= channel;
		this.rssi=rssi;
		this.currentAltitude=currentAltitude;
		this.currentLongitude=currentLongitude;
		this.altitudeMeters=altitudeMeters;
		this.accuracyMeters=accuracyMeters;
		this.type=type;
	}
	
	public Point3D getPoint() {
		return point;
	}
	
	@Override
	/**
	 * This function uses the library Llttz
	 * https://github.com/agap/llttz
	 */
	public long getUTC() {
		IConverter iconv = Converter.getInstance(TimeZoneListStore.class);
		TimeZone tz = iconv.getTimeZone(point.x(), point.y());
		return tz.getRawOffset();
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

}

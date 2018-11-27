package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class Test_MyCoords {

	@Test
	void testDistance3D(){
		double eps = 5;
		MyCoords mc = new MyCoords();
		Point3D ninth_building = new Point3D(32.10332, 35.20904);
		Point3D hummus_building = new Point3D(32.10635, 35.20523);
		double dist = mc.distance3d(ninth_building, hummus_building);
		if (dist < 493.0523 - eps || dist > 493.0523 + eps) {
			fail("NO!");
		}
	}
	
	@Test
	void testAzimuth_elevation_dist()
	{
		double eps = 0.2;
		MyCoords mc = new MyCoords();
		Point3D p0 = new Point3D(32, 45,120);
		Point3D p1 = new Point3D(60, 102,900);
		
		double ans = mc.azimuth_elevation_dist(p0, p1)[0];
		double relans = 35.4;
		if (ans < relans - eps || ans > relans + eps) {
			fail("error azimuth is wrong");
		}
	}
	
	@Test
	void testIsValid_GPS_Point()
	{
		MyCoords mc = new MyCoords();
		Point3D p2 = new Point3D(500,700);
		
		if(mc.isValid_GPS_Point(p2))
		{
			fail("error point isn't valid");
		}
		
	}

}

package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class Test_MyCoords {

	@Test
	void testDistance3D() {
		double eps = 5;
		MyCoords mc = new MyCoords();
		Point3D ninth_building = new Point3D(32.10332, 35.20904);
		Point3D hummus_building = new Point3D(32.10635, 35.20523);
		double dist = mc.distance3d(ninth_building, hummus_building);
		if (dist < 493.0523 - eps || dist > 493.0523 + eps) {
			fail("NO!");
		}
	}

}

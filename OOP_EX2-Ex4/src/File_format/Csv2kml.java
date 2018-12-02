package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Algorithms.MultiCsv;
import GIS.*;
import File_format.Csv2Gis;

public class Csv2kml {

	/**
	 * turn a single csv file into kml file.
	 * @param filename
	 */
	public static void singleCsv2kml(String filename) {
		Csv2Gis c2g = new Csv2Gis();
		MultiCsv mcsv = new MultiCsv();
		GIS_layer_obj layer = c2g.csv2GisLayer(filename);
		GIS_project_obj project = new GIS_project_obj();
		project.add(layer);
		mcsv.GisProject2kml(project, filename);
		
		
	}

}

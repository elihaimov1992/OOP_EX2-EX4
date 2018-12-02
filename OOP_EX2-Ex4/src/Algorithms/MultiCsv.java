package Algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import File_format.Csv2Gis;
import GIS.*;

public class MultiCsv {

	/**
	 * This function finds all csv files in given folder and combines them into a kml file.
	 * @param folder_path folder to search in
	 */
	public static void csvFiles2kml(String folder_path) {
		GIS_project_obj project = csvFiles2project(folder_path);
		GisProject2kml(project, folder_path);
	}
	
	/**
	 * This function finds all csv files in folder and creates a GIS_project object.
	 * used folder reading example from stack overflow
	 * https://stackoverflow.com/questions/4852531/find-files-in-a-folder-using-java
	 * @param folder_path
	 * @return GIS_project object
	 */
	public static GIS_project_obj csvFiles2project(String folder_path) {
		GIS_project_obj project = new GIS_project_obj();
		
		File folder = new File(folder_path);
		File[] csvFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith("csv");
		    }
		});
		
		Csv2Gis c2g = new Csv2Gis();
		for (int i = 0; i < csvFiles.length; i++) {
			GIS_layer_obj layer = c2g.csv2GisLayer(csvFiles[i].getAbsolutePath());
			project.add(layer);
		}
		
		return project;
	}
	
	/**
	 * This function takes a GIS_project and converts it into a kml file.
	 * @param project project to be converted
	 * @param folder_path folder to save kml file in
	 */
	public static void GisProject2kml(GIS_project_obj project, String folder_path) {
		ArrayList<String> content = new ArrayList<String>();
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
	    content.add(kmlstart);
	    String kmlend = "</Folder>\n" + "</Document></kml>";
        
        try{
        	folder_path = substringBeforeLast(folder_path, "\\");
        	int kml_name = 0;
        	
        	boolean check = new File(folder_path, "multiCsv" + Integer.toString(kml_name)+ ".kml").exists();
        	while (check) {
        		kml_name++;
        		check = new File(folder_path, "multiCsv" + Integer.toString(kml_name)+ ".kml").exists();
        	}
        	
	        FileWriter fw = new FileWriter(folder_path +"\\multiCsv" + kml_name + ".kml");
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<GIS_layer> layer_it = project.iterator();
	        while(layer_it.hasNext()) {
	        	GIS_layer layer = layer_it.next();
	        	Iterator<GIS_element> elem_it = layer.iterator();
	        	while(elem_it.hasNext()) {
	        		GIS_element_obj elem = (GIS_element_obj) elem_it.next();
		        	Meta_data_obj metadata = (Meta_data_obj)elem.getData();
		            String kmlelement ="<Placemark>\n" +
		                    "<name><![CDATA["+metadata.getName()+"]]></name>\n" +
		                    "<description>"+"<![CDATA[BSSID: <b>"+metadata.getBssid()+"</b><br/>Capabilities: <b>"+metadata.getCapabilities()+"</b><br/>Timestamp: <b>"+metadata.getTimestamp()+"</b><br/>Date: <b>"+metadata.getDate()+"</b>]]>"+"</description><styleUrl>#red</styleUrl>\n" +
		                    "<Point>\n" +
		                    "<coordinates>"+metadata.getCurrentLongitude()+","+metadata.getCurrentLatitude()+"</coordinates>" +
		                    "</Point>\n" +
		                    "</Placemark>\n";
		            content.add(kmlelement);
	        	}
	        }
	        content.add(kmlend);
	        String csv = content.toString().replaceAll(", ", "").replace("[<", "<").replace("l>]", "l>");
	        bw.write(csv);
	        bw.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Function that gets an absolute path to some location on the computer,
	 * if the path leads to a folder, it returns it as is.
	 * if it leads to a file, returns only the path to the folder the file is in.
	 * @param str filename
	 * @param separator
	 * @return
	 */
	private static String substringBeforeLast(String str, String separator) {
	      if (str.isEmpty() || separator.isEmpty()) {
	          return str;
	      }
	      int pos = str.lastIndexOf(separator);
	      if (pos == -1) {
	          return str;
	      }
	      return str.substring(0, pos);
	  }
}


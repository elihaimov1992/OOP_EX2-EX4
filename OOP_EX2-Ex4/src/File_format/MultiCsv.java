package File_format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.*;

public class MultiCsv {

	/**
	 * used folder reading example from stack overflow
	 * https://stackoverflow.com/questions/4852531/find-files-in-a-folder-using-java
	 * @param folder_path
	 * @return
	 */
	public static void csvFiles2kml(String folder_path) {
		GIS_project_obj project = csvFiles2project(folder_path);
		GisProject2kml(project, folder_path);
	}
	
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
	
	public static void GisProject2kml(GIS_project_obj project, String folder_path) {
		ArrayList<String> content = new ArrayList<String>();
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
	    content.add(kmlstart);
	    String kmlend = "</Folder>\n" + "</Document></kml>";
        
        try{
        	int kml_name = 0;
        	boolean check = new File(folder_path, Integer.toString(kml_name)).exists();
        	while (check) {
        		kml_name++;
        		check = new File(folder_path, Integer.toString(kml_name)).exists();
        	}
	        FileWriter fw = new FileWriter(folder_path +"//multiCsv" + kml_name + ".kml");
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
		                    "<coordinates>"+metadata.getCurrentLatitude()+","+metadata.getCurrentLongitude()+"</coordinates>" +
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
	
}

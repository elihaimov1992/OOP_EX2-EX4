package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import GIS.*;

public class Gis2kml {
	public static void GisProject2kml(GIS_project_obj project) {
		ArrayList<String> content = new ArrayList<String>();
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
	    content.add(kmlstart);
	    String kmlend = "</Folder>\n" + "</Document></kml>";
        
        try{
	        FileWriter fw = new FileWriter(csvFile.substring(0, csvFile.length()-3) + "kml");
	        BufferedWriter bw = new BufferedWriter(fw);
	        for (int i = 0; i < csvArrayList.size(); i++) {
	            String[] s = csvArrayList.get(i);
	            String kmlelement ="<Placemark>\n" +
	                    "<name><![CDATA["+s[1]+"]]></name>\n" +
	                    "<description>"+"<![CDATA[BSSID: <b>"+s[0]+"</b><br/>Capabilities: <b>"+s[2]+"</b><br/>Date: <b>"+s[3]+"</b>]]>"+"</description><styleUrl>#red</styleUrl>\n" +
	                    "<Point>\n" +
	                    "<coordinates>"+s[5]+","+s[4]+"</coordinates>" +
	                    "</Point>\n" +
	                    "</Placemark>\n";
	            content.add(kmlelement);
	        }
	        content.add(kmlend);
	        String csv = content.toString().replaceAll(", ", "").replace("[<", "<").replace("l>]", "l>");
//	        String csv = content.toString();
	        bw.write(csv);
	        bw.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}

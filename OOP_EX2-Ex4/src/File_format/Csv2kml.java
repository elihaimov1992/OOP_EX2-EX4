package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GIS_layer_obj;
import File_format.Csv2Gis;

public class Csv2kml {

	public static void main(String[] args) 
    {	
        String filename = "dubi.csv";
        Csv2Gis c2g = new Csv2Gis();
        GIS_layer_obj layer = c2g.csv2GisLayer(filename);
       

    }

	
//	static void writeFileKML(ArrayList<String[]> a, String output) {
//	    ArrayList<String> content = new ArrayList<String>();
//	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
//	    content.add(kmlstart);
//
//	    String kmlend = "</kml>";
//	    try{
//	        FileWriter fw = new FileWriter(output);
//	        BufferedWriter bw = new BufferedWriter(fw);
//	        for (int i = 1; i < a.size(); i++) {
//	            String[] s = a.get(i);
//	            String kmlelement ="<Placemark>\n" +
//	                    "<name>"+s[6]+"</name>\n" +
//	                    "<description>"+s[6]+"</description>\n" +
//	                    "<Point>\n" +
//	                    "<coordinates>"+s[3]+","+s[2]+"</coordinates>" +
//	                    "</Point>\n" +
//	                    "</Placemark>\n";
//	            content.add(kmlelement);
//	        }
//	        content.add(kmlend);
//	        String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
//	        bw.write(csv);
//	        bw.close();
//	    } 
//	    catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
}

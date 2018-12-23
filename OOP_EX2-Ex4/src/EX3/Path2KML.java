package EX3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.sun.jmx.snmp.Timestamp;

/**
 * This class represents an object that creates a kml file from a path object
 */
public class Path2KML {

	Path path;
	
	public Path2KML(Path path) {
		this.path = path;
	}
	
	/**
	 * Creates a kml file from the path object
	 */
	public void toKml() {
		ArrayList<String> content = new ArrayList<String>();
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Packman Path</name>\n";
	    content.add(kmlstart);
	    String kmlend = "</Folder>\n" + "</Document></kml>";
        
        try{
        	int kml_name = 0;
        	
        	boolean check = new File("data/path" + Integer.toString(kml_name)+ ".kml").exists();
        	while (check) {
        		kml_name++;
        		check = new File("data/path" + Integer.toString(kml_name)+ ".kml").exists();
        	}
        	
	        FileWriter fw = new FileWriter("data/path" + Integer.toString(kml_name) + ".kml");
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<Fruit> fruit_it = path.points.iterator();
	        int i = 0;
	        while(fruit_it.hasNext()) {
	        	Fruit curr_fruit = fruit_it.next();
	        	Date eatenDate = path.times.get(i);
	        	long time = eatenDate.getTime();
	            Timestamp ts = new Timestamp(time);
	            String kmlelement ="<Placemark>\n" +
	                    "<name><![CDATA[Fruit"+curr_fruit.id+"]]></name>\n" +
	                    "<description>"+"<![CDATA[Timestamp: <b>"+ts+"</b><br/>Date: <b>"+eatenDate+"</b>]]>"+"</description><styleUrl>#red</styleUrl>\n" +
	                    "<Point>\n" +
	                    "<coordinates>"+curr_fruit.location.y()+","+curr_fruit.location.x()+"</coordinates>" +
	                    "</Point>\n" +
	                    "</Placemark>\n";
	            content.add(kmlelement);
	            i++;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

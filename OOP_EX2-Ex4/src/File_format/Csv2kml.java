package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Csv2kml {
	/**
	 * We based our code on Yael's CSV reader file from the moodle,
	 * and csv to kml code we found on stackoverflow:
	 * https://stackoverflow.com/questions/47183982/converting-csv-file-to-kml
	 * @param args
	 */
	public static void main(String[] args) 
    {	
        String csvFile = "WigleWifi_20171201110209.csv";
        String line = "";
        String cvsSplitBy = ",";

        ArrayList<String[]> csvArrayList = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader("data//"+csvFile))) 
        {
        	br.readLine();
        	br.readLine();
        	int i = 0;
            while ((line = br.readLine()) != null) 
            {
                String[] userInfo = line.split(cvsSplitBy);
                String[] temp = {"","","","","",""};
                csvArrayList.add(temp);
                csvArrayList.get(i)[0] = userInfo[0]; //BSSID
                csvArrayList.get(i)[1] = userInfo[1]; //NAME
                csvArrayList.get(i)[2] = userInfo[2]; //CAPABILITIES
                csvArrayList.get(i)[3] = userInfo[3]; //DATE
                csvArrayList.get(i)[4] = userInfo[6]; //LAT
                csvArrayList.get(i)[5] = userInfo[7]; //LON
                i++;
//                System.out.println("Name: " + userInfo[1] + ", CurrentLatitude: " + userInfo[6] + ", CurrentLongitude: " + userInfo[7]);
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
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

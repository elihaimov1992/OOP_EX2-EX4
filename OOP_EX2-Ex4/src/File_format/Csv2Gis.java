package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import GIS.GIS_element;
import GIS.GIS_element_obj;
import GIS.GIS_layer;
import GIS.GIS_layer_obj;
import GIS.Meta_data_obj;

public class Csv2Gis {

	
	/**
	 * this takes csv file and converts it into Gis layer.
	 * @param csvFile filename
	 * @return Gis layer object
	 */
	public static GIS_layer_obj csv2GisLayer(String csvFile) {
		String line = "";
        String cvsSplitBy = ",";
		
		GIS_layer elementLayer = new GIS_layer_obj();
		
	    ArrayList<String[]> csvArrayList = new ArrayList<String[]>();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
	    {
	    	br.readLine();
	    	br.readLine();
	    	int i = 0;
	        while ((line = br.readLine()) != null) 
	        {
	        	GIS_element_obj element = new GIS_element_obj();
	            String[] userInfo = line.split(cvsSplitBy);
	            String[] temp = {"","","","","","", ""};
	            csvArrayList.add(temp);
	            csvArrayList.get(i)[0] = userInfo[0]; //BSSID
	            csvArrayList.get(i)[1] = userInfo[1]; //NAME
	            csvArrayList.get(i)[2] = userInfo[2]; //CAPABILITIES
	            csvArrayList.get(i)[3] = userInfo[3]; //DATE
	            csvArrayList.get(i)[4] = userInfo[6]; //LAT
	            csvArrayList.get(i)[5] = userInfo[7]; //LON
	            csvArrayList.get(i)[6] = userInfo[8]; //ALT
	            
	            element.setData(userInfo[1], userInfo[0], userInfo[2], userInfo[3], Double.parseDouble(userInfo[6]), Double.parseDouble(userInfo[7]), Double.parseDouble(userInfo[8]));
	            elementLayer.add(element);
	            
	            i++;
//	            System.out.println("Name: " + userInfo[1] + ", CurrentLatitude: " + userInfo[6] + ", CurrentLongitude: " + userInfo[7]);
	        }

	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return (GIS_layer_obj)elementLayer;
	}
}

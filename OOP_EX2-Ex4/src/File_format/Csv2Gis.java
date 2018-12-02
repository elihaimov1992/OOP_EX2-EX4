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
	
	public static GIS_layer_obj csv2GisElement(String[] csvLineArr) {
		String line = "";
        String cvsSplitBy = ",";
		Date time = new Date(csvLineArr[3]);
		
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
	            String[] temp = {"","","","","",""};
	            csvArrayList.add(temp);
	            csvArrayList.get(i)[0] = userInfo[0]; //BSSID
	            csvArrayList.get(i)[1] = userInfo[1]; //NAME
	            csvArrayList.get(i)[2] = userInfo[2]; //CAPABILITIES
	            csvArrayList.get(i)[3] = userInfo[3]; //DATE
	            csvArrayList.get(i)[4] = userInfo[6]; //LAT
	            csvArrayList.get(i)[5] = userInfo[7]; //LON
	            
	            element.setData(userInfo[1], userInfo[0], userInfo[2], i, i, cvsSplitBy, i, i, i);
	            
	            i++;
//	            System.out.println("Name: " + userInfo[1] + ", CurrentLatitude: " + userInfo[6] + ", CurrentLongitude: " + userInfo[7]);
	        }

	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
	public static convertToGis(String csvFile) {
	    
	}
}

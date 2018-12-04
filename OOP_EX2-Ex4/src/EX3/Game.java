package EX3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GIS_element_obj;
import GIS.GIS_layer;
import GIS.GIS_layer_obj;
import Geom.Point3D;

public class Game {

	ArrayList<Packman> packmans = new ArrayList<Packman>();
	ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	
	
//	Game g = new Game();
//	Packman p1 = new Packman(Point, speed);
//	g.addPackman(p1);

	public Game(String csvFile) {
		ArrayList<String[]> lines = readCsv(csvFile);
		for (int i = 0; i < lines.size(); i++) {
			String type = lines.get(i)[0];
			int id = Integer.parseInt(lines.get(i)[1]);
			double lat = Double.parseDouble(lines.get(i)[2]);
			double lon = Double.parseDouble(lines.get(i)[3]);
			double alt = Double.parseDouble(lines.get(i)[4]);
			int speed_weight = Integer.parseInt(lines.get(i)[5]);
			Point3D location = new Point3D(lat, lon, alt);
			if (type == "P") {
				int radius = Integer.parseInt(lines.get(i)[6]);
				packmans.add(new Packman(id, location, speed_weight, radius));
			}
			else if (type == "F"){
				fruits.add(new Fruit(id, location, speed_weight));
			}
		}
	}
	
	public void addPackman(Packman pkmn) {
		packmans.add(pkmn);
	}
	
	public void addFruit(Packman frt) {
		packmans.add(frt);
	}


	public static void main(String[] args) {

	}

	private ArrayList<String[]> readCsv(String csvFile) {
		String line = "";
        String cvsSplitBy = ",";
			
	    ArrayList<String[]> csvArrayList = new ArrayList<String[]>();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
	    {
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

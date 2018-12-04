package EX3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Game {

	ArrayList<Packman> packmans = new ArrayList<Packman>();
	ArrayList<Fruit> fruits = new ArrayList<Fruit>();

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
			if (type.equals("P")) {
				int radius = Integer.parseInt(lines.get(i)[6]);
				packmans.add(new Packman(id, location, speed_weight, radius));
			}
			else if (type.equals("F")){
				fruits.add(new Fruit(id, location, speed_weight));
			}
		}
	}
	
	public void addPackman(Packman pkmn) {
		packmans.add(pkmn);
	}
	
	public void addFruit(Fruit frt) {
		fruits.add(frt);
	}
	
	public ArrayList<Packman> getPackmanArrayList() {
		return packmans;
	}
	
	public ArrayList<Fruit> getFruitArrayList() {
		return fruits;
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
	            String[] line_values = line.split(cvsSplitBy);
	            csvArrayList.add(line_values);
	        }

	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return csvArrayList;
	}
	
	public void writeCsv() {
		ArrayList<String> content = new ArrayList<String>();
		String folder_path = "data//";
	    String csvstart = "Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+packmans.size()+","+fruits.size()+"\n";
	    content.add(csvstart);
        
        try{
        	int csv_name = 0;
        	
        	boolean check = new File(folder_path, "game" + Integer.toString(csv_name)+ ".csv").exists();
        	while (check) {
        		csv_name++;
        		check = new File(folder_path, "game" + Integer.toString(csv_name)+ ".csv").exists();
        	}
        	
	        FileWriter fw = new FileWriter(folder_path +"game" + csv_name + ".csv");
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<Packman> pac_it = packmans.iterator();
	        while(pac_it.hasNext()) {
	        	Packman pac = pac_it.next();
	        	String line = "P,"+pac.toString().substring(1, pac.toString().length()-1)+"\n";
		        content.add(line);
	        }
	        Iterator<Fruit> frt_it = fruits.iterator();
	        while(frt_it.hasNext()) {
	        	Fruit frt = frt_it.next();
	        	String line = "F,"+frt.toString().substring(1, frt.toString().length()-1)+"\n";
		        content.add(line);
	        }
	        String csv = content.toString().replaceAll(", ", "").replace("[", "").replace("]", "");
	        bw.write(csv);
	        bw.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		Game game = new Game("data//game_1543684662657.csv");
		game.writeCsv();
	}
}

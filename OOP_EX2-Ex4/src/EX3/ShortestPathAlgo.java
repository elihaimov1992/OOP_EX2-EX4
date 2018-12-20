package EX3;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;

public class ShortestPathAlgo {

	Game game;
	ArrayList<Path> paths = new ArrayList();
	double[] totalSeconds;
	int availableFruits;
	
	public ShortestPathAlgo(Game game) {
		this.game = game;
		availableFruits = game.getFruitArrayList().size();
		totalSeconds = new double[game.getPackmanArrayList().size()];
	}
	
	/**
	 * creates an array of time in seconds from a given packman to each of the fruits
	 * ex: [5, 10, 67]
	 * @param packman
	 * @return
	 */
	public double[] timeFromOnePackmanToFruits(Packman packman) {
		// returns an array with times the packman will take to get to each fruit
		// [5, 10, 67]
		// [45, 20, 7]
		// [10, 12, 1]
		// [ [5, 10, 67], [45, 20, 7], [10, 12, 1] ]
		// [ [inf(5), 5+43, 5+7], [inf, 20, 7], [inf, 12, 1] ]
		// [ [inf, inf, 5+7], [inf, inf, 7], [inf, inf(12), 12+30] ]
		// [ [inf, inf, inf], [inf, inf, inf(7)], [inf, inf, inf] ]
		double[] times = new double[game.getFruitArrayList().size()];
		Iterator<Fruit> fruit_it = game.getFruitArrayList().iterator();
		int i = 0;
		while (fruit_it.hasNext()) {
			Fruit curr_fruit = fruit_it.next();
			if (!curr_fruit.eaten) {
				double secondsToFruit = secondsFromPackmanToFruit(packman, curr_fruit);
				times[i] = secondsToFruit;
			}
			i++;
		}
		return times;
	}
	
	/**
	 * creates a path for each of the packmans in order to get an optimal final time
	 */
	public void findPaths() {
		ArrayList<double[]> times = new ArrayList<>();
		Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
		while (pack_it.hasNext()) {
			Packman curr_pack = pack_it.next();
			times.add(timeFromOnePackmanToFruits(curr_pack));
		}
		for (int i = 0; i < game.getFruitArrayList().size(); i++) {
			int pack_index = indexOfMinArray(times, i);
			Fruit curr_fruit = game.getFruitArrayList().get(i);
			Packman chosen_pack = game.getPackmanArrayList().get(pack_index);
			chosen_pack.path.addPoint(curr_fruit);
			chosen_pack.move(curr_fruit.location);
			curr_fruit.eaten = true;
			times.set(pack_index, timeFromOnePackmanToFruits(chosen_pack));
		}
		pack_it = game.getPackmanArrayList().iterator();
		while (pack_it.hasNext()) {
			Packman curr_pack = pack_it.next();
			invertPath(curr_pack);
			Point3D original_point = curr_pack.path.points.get(0).location;
			curr_pack.move(original_point);
			curr_pack.setCurrDestination(curr_pack.path.points.get(1).location);
		}
		Iterator<Fruit> fruit_it = game.getFruitArrayList().iterator();
		while (fruit_it.hasNext()) {
			Fruit curr_fruit = fruit_it.next();
			curr_fruit.eaten = false;
		}
		
	}
	
	private void invertPath(Packman pack) {
		ArrayList<Fruit> inverted_path = new ArrayList();
		for (int i = pack.path.points.size()-1; i >= 0; i--) {
			inverted_path.add(pack.path.points.get(i));
		}
		pack.path.points = inverted_path;
	}
	
//	private void eatIfStartOnFruit() {
//		Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
//		while (pack_it.hasNext()) {
//			Packman curr_pack = pack_it.next();
//			if (curr_pack.)
//		}
//	}
	
	/**
	 * returns the index of the packman closest to a given fruit
	 * @param arrays
	 * @param fruit_index
	 * @return
	 */
	public int indexOfMinArray(ArrayList<double[]> arrays, int fruit_index) {
		double minTime = Double.MAX_VALUE;
		int indexOfMinArray = 0;
		int i = 0;
		Iterator<double[]> it_arrs = arrays.iterator();
		while (it_arrs.hasNext()) {
			double[] arr = it_arrs.next();
			if (arr[fruit_index] < minTime) {
				minTime = arr[fruit_index];
				indexOfMinArray = i;
			}
			i++;
		}
		return indexOfMinArray;
	}
	
	/**
	 * calculates the time in seconds it will take to a given packman to get to a given fruit
	 * @param packman
	 * @param fruit
	 * @return
	 */
	public double secondsFromPackmanToFruit(Packman packman, Fruit fruit) {
		MyCoords mc = new MyCoords();
		double distance = mc.distance3d(packman.location, fruit.location) - packman.radius;
		double speed = packman.speed;
		double seconds = distance / speed;
		return seconds + totalSeconds[packman.id];
	}
	
	public static void main(String[] args) {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Map map = new Map("data//Ariel1.png", start, end);
		Game game = new Game("data//game0.csv");
		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		spa.findPaths();
		
		Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
		while (pack_it.hasNext()) {
			Packman curr_pack = pack_it.next();
			System.out.println(curr_pack.path);
		}

	}

}

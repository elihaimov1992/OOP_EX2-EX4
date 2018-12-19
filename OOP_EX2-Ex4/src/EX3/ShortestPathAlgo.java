package EX3;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;

public class ShortestPathAlgo {

	Game game;
	ArrayList<Path> paths = new ArrayList<Path>();
	int availableFruits = game.getFruitArrayList().size();
	
//	public void findPaths() {
//		MyCoords mc = new MyCoords();
//		Fruit nearest_fruit = null;
//		double time_to_nearest = Double.MAX_VALUE;
//		while (!game.getFruitArrayList().isEmpty()) {
//			Iterator<Packman> pack_it = game.getPackmanArrayList().iterator();
//			while (pack_it.hasNext()) {
//				Packman curr_pack = pack_it.next();
//				Iterator<Fruit> fruit_it = game.getFruitArrayList().iterator();
//				while (fruit_it.hasNext()) {
//					Fruit curr_fruit = fruit_it.next();
//					double distance = mc.distance3d(curr_pack.location, curr_fruit.location);
//					double time_needed = (distance - curr_pack.radius)/curr_pack.speed;
//					if (time_needed < time_to_nearest) {
//						nearest_fruit = curr_fruit;
//						time_to_nearest = time_needed;
//					}
//				}
//				curr_pack.path.add(nearest_fruit.location);
//				curr_pack.path.setTime(curr_pack.path.getTime() + time_to_nearest);
//				game.getFruitArrayList().remove(nearest_fruit);
//			}
//			
//		}
//		
//	}
	
	public double[] timeFromOnePackmanToFruits(Packman packman) {
		// returns an array with times the packman will take to get to each fruit
		// [5, 10, 67]
		// [45, 20, 7]
		// [10, 12, 1]
		// [ [5, 10, 67], [45, 20, 7], [10, 12, 1] ]
		// [ [inf(5), 5+43, 5+7], [inf, 20, 7], [inf, 12, 1] ]
		// [ [inf, inf, 5+7], [inf, inf, 7], [inf, inf(12), 12+30] ]
		// [ [inf, inf, inf], [inf, inf, inf(7)], [inf, inf, inf] ]
	}
	
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
			chosen_pack.path.add(curr_fruit.location);
			curr_fruit.eaten = true;
			times.set(pack_index, timeFromOnePackmanToFruits(chosen_pack));
		}
	}
	
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
	
	public static void main(String[] args) {
		

	}

}

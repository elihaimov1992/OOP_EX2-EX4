package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GIS_layer_obj implements GIS_layer{

	ArrayList<GIS_element> elements = new ArrayList<GIS_element>();
	
	/**
	 * Adds an element to the elements arraylist if it does not already exist there
	 * @return true if the arraylist was changed, false otherwise
	 */
	@Override
	public boolean add(GIS_element arg0) {
		boolean ans = false;
		if (!elements.contains(arg0)) {
			elements.add(arg0);
			ans = true;
		}
		return ans;
	}

	/**
	 * Adds the given collection to our arraylist
	 * @return true if the arraylist was changed, false otherwise
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		boolean ans = false;
		Iterator it = arg0.iterator();
		while (it.hasNext()) {
			GIS_element elem = (GIS_element) it.next();
			ans = add(elem);
		}
		return ans;
	}

	@Override
	public void clear() {
		elements.clear();
		
	}

	/**
	 * Checks if our arraylist contains an object
	 * @return true if contains, else false
	 */
	@Override
	public boolean contains(Object arg0) {
		return elements.contains(arg0);
	}

	/**
	 * Checks if our arraylist contains objects from a collection
	 * @return true if contains at least one element from the collection, else false
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return elements.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return elements.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return elements.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return elements.retainAll(arg0);
	}

	@Override
	public int size() {
		return elements.size();
	}

	/**
	 * Turns our layer object into an array
	 * @return returns the array
	 */
	@Override
	public Object[] toArray() {
		GIS_element[] arr = new GIS_element[elements.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = elements.get(i);
		}
		return arr;
	}

	/**
	 * Turns our layer object into an array
	 * @return returns the array
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		ArrayList<GIS_element> elements_copy = new ArrayList<GIS_element>(elements);
		ArrayList<GIS_element> arg0_list = new ArrayList<GIS_element>();
		for (int i = 0; i < arg0.length; i++) {
			arg0_list.add((GIS_element) arg0[i]);
		}
		elements_copy.retainAll(arg0_list);
		GIS_element[] arr = new GIS_element[elements_copy.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = elements_copy.get(i);
		}
		return (T[]) arr;
	}

	@Override
	public Meta_data get_Meta_data() {
		return elements.get(0).getData();
	}

}

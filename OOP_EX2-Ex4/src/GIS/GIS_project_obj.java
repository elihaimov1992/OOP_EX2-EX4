package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GIS_project_obj implements GIS_project{

	ArrayList<GIS_layer> layers = new ArrayList<GIS_layer>();
	
	@Override
	public boolean add(GIS_layer e) {
		boolean ans = false;
		if (!layers.contains(e)) {
			layers.add(e);
			ans = true;
		}
		return ans;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		boolean ans = false;
		Iterator it = c.iterator();
		while (it.hasNext()) {
			GIS_layer lay = (GIS_layer) it.next();
			ans = add(lay);
		}
		return ans;
	}

	@Override
	public void clear() {
		layers.clear();
	}

	@Override
	public boolean contains(Object o) {
		return layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return layers.retainAll(c);
	}

	@Override
	public int size() {
		return layers.size();
	}

	@Override
	public Object[] toArray() {
		GIS_layer[] arr = new GIS_layer[layers.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = layers.get(i);
		}
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		ArrayList<GIS_layer> layers_copy = new ArrayList<GIS_layer>(layers);
		ArrayList<GIS_layer> a_list = new ArrayList<GIS_layer>();
		for (int i = 0; i < a.length; i++) {
			a_list.add((GIS_layer) a[i]);
		}
		layers_copy.retainAll(a_list);
		GIS_layer[] arr = new GIS_layer[layers_copy.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = layers_copy.get(i);
		}
		return (T[]) arr;
	}

	@Override
	public Meta_data get_Meta_data() {
		return layers.get(0).get_Meta_data();
	}

}

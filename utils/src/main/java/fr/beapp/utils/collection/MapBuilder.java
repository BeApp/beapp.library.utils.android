package fr.beapp.utils.collection;


import java.util.HashMap;

public class MapBuilder<K, V> extends HashMap<K, V> {

	public static <K, V> MapBuilder<K, V> builder() {
		return new MapBuilder<>();
	}

	public MapBuilder<K, V> add(K key, V value) {
		if (value != null) {
			put(key, value);
		}
		return this;
	}

}

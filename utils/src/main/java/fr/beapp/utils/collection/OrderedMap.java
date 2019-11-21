package fr.beapp.utils.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderedMap<K, V> extends HashMap<K, V> {

	private final List<K> keys = new LinkedList<>();

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(keys.size());
		for (K key : keys) {
			out.writeObject(key);
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			keys.add((K) in.readObject());
		}
	}

	@Override
	public V put(@NonNull K key, @Nullable V value) {
		return put(size(), key, value);
	}

	@Nullable
	public V put(int index, @NonNull K key, @Nullable V value) {
		V previous = super.put(key, value);
		if (previous == null) {
			keys.add(index, key);
		}
		return previous;
	}

	@Nullable
	@Override
	@SuppressWarnings({"unchecked", "RedundantCast"})
	public V remove(Object key) {
		V removed = super.remove(key);
		if (removed != null) {
			keys.remove((K) key);
		}
		return removed;
	}

	@Nullable
	public V removeAt(int position) {
		return position < 0 || position >= size() ? null : remove(keys.get(position));
	}

	@Override
	public void clear() {
		super.clear();
		keys.clear();
	}

	@NonNull
	public List<K> findKeys(@Nullable V value) {
		if (value == null)
			return Collections.emptyList();

		List<K> keys = new LinkedList<>();
		for (Entry<K, V> entry : entrySet()) {
			if (entry.getValue().equals(value)) {
				keys.add(entry.getKey());
			}
		}
		return keys;
	}

	@NonNull
	public List<V> orderedValues() {
		List<V> values = new ArrayList<>(size());
		for (K key : keys) {
			values.add(get(key));
		}
		return values;
	}

	@Nullable
	public K getKeyAt(int position) {
		return position < 0 || position >= size() ? null : keys.get(position);
	}

	@Nullable
	public V getValueAt(int position) {
		return position < 0 || position >= size() ? null : get(keys.get(position));
	}

	public int indexOfKey(@Nullable K key) {
		return keys.indexOf(key);
	}

}

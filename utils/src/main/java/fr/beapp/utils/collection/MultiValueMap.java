package fr.beapp.utils.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Extension of {@link HashMap} class to work with {@link List} as values.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MultiValueMap<K, V> extends HashMap<K, List<V>> {

	public static <K, V> MultiValueMap<K, V> build() {
		return new MultiValueMap<>();
	}

	/**
	 * Add a new value at the given key.
	 * <p/>
	 * If no entry was previously added for this key, a new {@link LinkedList} will be prepare to hold the value
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the instance of MultiValueMAp
	 */
	@NonNull
	public MultiValueMap<K, V> add(@Nullable K key, @Nullable V value) {
		List<V> values = get(key);
		if (values == null) {
			values = new LinkedList<>();
			put(key, values);
		}
		values.add(value);

		return this;
	}

	/**
	 * Add a new value at a specific index in the list at the given key.
	 * <p/>
	 * If no entry was previously added for this key, a new {@link LinkedList} will be prepare to hold the value
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param index index at which the specified element is to be inserted
	 * @param value value to be associated with the specified key
	 * @return the instance of MultiValueMAp
	 */
	@NonNull
	public MultiValueMap<K, V> add(@Nullable K key, int index, @Nullable V value) {
		List<V> values = get(key);
		if (values == null) {
			values = new LinkedList<>();
			put(key, values);
		}
		values.add(index, value);

		return this;
	}

	@Nullable
	@Override
	public List<V> put(@NonNull K key, @Nullable List<V> value) {
		return super.put(key, value);
	}

	/**
	 * Remove a value from the list at the given key
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to search and remove
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	public boolean removeValue(@Nullable K key, @Nullable V value) {
		List<V> values = get(key);
		return values != null && values.remove(value);
	}

	@Nullable
	@Override
	public List<V> get(@Nullable Object key) {
		return super.get(key);
	}

	/**
	 * Retrieve all values associated to a given key
	 *
	 * @param key           key with which the values is to be associated
	 * @param defaultValues default value to return in case there is no value at the given key
	 * @return all values or <code>defaultValues</code>
	 */
	@NonNull
	public List<V> get(@Nullable K key, @NonNull List<V> defaultValues) {
		List<V> values = get(key);
		return values != null ? values : defaultValues;
	}

	/**
	 * Retrieve the value at the given index for the given key, if any
	 *
	 * @param key   key with which the values is to be associated
	 * @param index the index to look for value
	 * @return the searched value, or <code>null</code>
	 */
	@Nullable
	public V get(@Nullable K key, int index) {
		List<V> values = get(key);
		if (values == null || index < 0 || index > values.size())
			return null;
		return values.get(index);
	}

	/**
	 * Retrieve the value at the given index for the given key, if any
	 *
	 * @param key          key with which the values is to be associated
	 * @param index        the index to look for value
	 * @param defaultValue the default value if there is none at the given key or index
	 * @return the searched value, or <code>defaultValue</code>
	 */
	@NonNull
	public V get(@Nullable K key, int index, @NonNull V defaultValue) {
		V value = get(key, index);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieve all values stored in this map.
	 * <p/>
	 * Values will be sorted by :
	 * <ol>
	 * <li>Key order</li>
	 * <li>Value insertion order</li>
	 * </ol>
	 *
	 * @return a list of all values
	 */
	@NonNull
	public List<V> getAllValues() {
		return getAllValues(null);
	}

	/**
	 * Retrieve all values stored in this map.
	 * <p/>
	 * Values will be sorted by :
	 * <ol>
	 * <li>Key order</li>
	 * <li>Value insertion order</li>
	 * </ol>
	 *
	 * @param keyComparator the comparator to use for key sorting
	 * @return a list of all values
	 */
	@NonNull
	public List<V> getAllValues(@Nullable Comparator<K> keyComparator) {
		TreeSet<K> orderedKeys = new TreeSet<>(keyComparator);
		orderedKeys.addAll(keySet());

		List<V> allValues = new ArrayList<>(fullSize());
		for (K key : orderedKeys) {
			List<V> values = get(key);
			if (values != null) {
				allValues.addAll(values);
			}
		}
		return allValues;
	}

	/**
	 * Retrieve the number of values for the given key
	 *
	 * @param key key to use to retrieve the values
	 * @return size of the list at the given key
	 */
	public int size(K key) {
		List<V> values = get(key);
		return values != null ? values.size() : 0;
	}

	/**
	 * Retrieve the number of all values
	 *
	 * @return size of all key's lists
	 */
	public int fullSize() {
		int count = 0;
		for (Entry<K, List<V>> entry : entrySet()) {
			count += size(entry.getKey());
		}
		return count;
	}

}
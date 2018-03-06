package dataStructures;

import adt.HashTable;
import unit.SLNode;
import unit.Entry;
import java.util.HashSet;
import java.util.Set;

public class ClosedBucketHashTable<KEY, VALUE>
		implements HashTable<KEY, VALUE> {
	private SLNode<Entry<KEY, VALUE>>[] buckets;
	private int capacity; // hash table capacity
	private int count; // number of key-value pairs
	
	@SuppressWarnings("unchecked")
	public ClosedBucketHashTable(int m) {
		buckets = (SLNode<Entry<KEY, VALUE>>[]) new SLNode[m];
		capacity = m;
		count = 0;
	}
	
	@Override
	public int size() {
		return count;
	}
	
	@Override
	public boolean isEmpty() {
		return count == 0;
	}
	
	@Override
	public int hash(KEY key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	@Override
	public VALUE search(KEY key) {
		if (key  == null)
			throw new IllegalArgumentException("null argument");
		int i = hash(key);
		SLNode<Entry<KEY, VALUE>> node = buckets[i];
		if (node == null) 
			return null;
		while (node != null) {
			Entry<KEY, VALUE> entry = node.data;
			if (key.equals(entry.key)) {
				return entry.value;
			}
			node = node.link;
		}
		return null;
	}

	@Override
	public void insert(KEY key, VALUE value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("null argument");
		// TODO implement resizable (if count >= capacity)
		Entry<KEY, VALUE> newEntry = new Entry<>(key, value);
		int i = hash(key);
		SLNode<Entry<KEY, VALUE>> node = buckets[i];
		while (node != null) {
			if (newEntry.equals(node.data)) {
				node.data = newEntry;
				return;
			}
			node = node.link;
		}
		buckets[i] = new SLNode<>(newEntry, buckets[i]);
		count++;
	}

	@Override
	public boolean delete(KEY key) {
		if (key  == null) 
			throw new IllegalArgumentException("null argument");
		int i = hash(key);
		if (i < 0 || i >= capacity)
			return false;
		SLNode<Entry<KEY, VALUE>> node = buckets[i];
		if (key.equals(node.data.key)) {
			node = node.link;//???? 
			count--;
		}
		while (node.link != null) {
			if (key.equals(node.link.data.key)) {
				node.link = node.link.link;
				count--;
				return true;
			}
			node = node.link;
		}
		return false;
	}

	public Set<Entry<KEY, VALUE>> entrySet() {
		Set<Entry<KEY, VALUE>> result = new HashSet<>();
		for (SLNode<Entry<KEY, VALUE>> node : buckets) {
			while (node != null) {
				result.add(node.data);
				node = node.link;
			}
		}
		return result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (SLNode<Entry<KEY, VALUE>> node : buckets) {
			sb.append(index++);
			sb.append(". ");
			if (node != null) {
				while (node != null) {
					sb.append(node.data);
					node = node.link;
				}
			} else
				sb.append("null");
			sb.append("\n");
		}
		return sb.toString();
	}
}

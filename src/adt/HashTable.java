package adt;

public interface HashTable<KEY, VALUE> extends Dictionary<KEY, VALUE> {
	
	public int hash(KEY key);
	
}

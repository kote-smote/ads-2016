package adt;

import unit.Entry;
import java.util.Set;

public interface Dictionary<KEY, VALUE> {

	public VALUE search(KEY key);
	
	public void insert(KEY key, VALUE value);
	
	public boolean delete(KEY key);
	
	public int size();
	
	public boolean isEmpty();

	public Set<Entry<KEY, VALUE>> entrySet();
	
}

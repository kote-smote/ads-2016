package unit;

public class Entry<KEY, VALUE> {
	public KEY key;
	public VALUE value;
	
	public Entry(KEY key, VALUE value) {
		this.key = key;
		this.value = value;
	}
	
	public String toString() {
		return String.format("<%s, %s>", key.toString(), value.toString());
	}
	
	public boolean equals(Entry<KEY, VALUE> other) {
		return this.key.equals(other.key);
	}

}

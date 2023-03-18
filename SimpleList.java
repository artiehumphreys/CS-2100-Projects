package vector;

public interface SimpleList<T> {
	
	/**
	 * Returns the number of items in this Vector
	 * @return number of items in this Vector
	 */
	public int size();
	
	/**
	 * Changes the Vector so that it contains zero items
	 */
	public void clear();
	
	/**
	 * Inserts new item at the end of the Vector
	 * @param data item to be inserted
	 */
	public void insertAtTail(T item);
	
	/**
	 * Inserts item at the beginning of the Vector
	 * @param item item to be inserted
	 */
	public void insertAtHead(T item);
	
	/**
	 * Inserts item such that index becomes the position of the newly inserted item
	 * @param item item to be inserted
	 * @param index location of insertion
	 */
	public void insertAt(int index, T item);
	
	/**
	 * Remove item at start and end of Vector respectively (two separate methods),
	 * @return item that is removed
	 */
	public T removeAtTail();
	public T removeAtHead();
	
	
	/**
	 * Searches for item in Vector 
	 * @param item to be found
	 * @return index of first occurrence of the item in the list, or -1 if not present
	 */
	public int find(T item);
	
	/**
	 * Returns the item at the given index, null if anything goes wrong (index out of bounds, empty list, etc.)
	 * @param index position to be retrieved
	 * @return item at the given index, null if anything goes wrong
	 */
	public T get(int index);
	
}

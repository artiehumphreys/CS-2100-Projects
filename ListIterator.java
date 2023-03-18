// Name: Artie Humphreys
// Computing ID: bsg6vr@virginia.edu
// Homework Name: Homework 6: Linked List
// Resources used: None

package list;

public class ListIterator<T> {
	
	/* Current node (of type ListNode) */
	protected ListNode<T> curNode; 
	
/* ListIterator constructor. Accepts the current node. */
	public ListIterator(ListNode<T> currentNode) {
		/**
		 * Default constructor
		 */
		/* TODO: Implement this method */
		this.curNode = currentNode;
	}
	
	/**
	 * These two methods tell us if the iterator has run off
	 * the list on either side
	 */
	public boolean isPastEnd() { 
		/* TODO: Implement this method */
		//CHECK FOR IF THE LIST IS INITIALIZED AND NOT LENGTH 0
		if (curNode == null) return true;
		if (curNode.next == null && curNode.prev != null) return true; //if the next node is null and the previous
		//one is valid
		else return false;
	}
	
	public boolean isPastBeginning() { 
		/* TODO: Implement this method */
		//CHECK FOR IF THE LIST IS INITIALIZED AND NOT LENGTH 0
		if (curNode == null) return true;
		if (curNode.prev == null && curNode.next != null) return true; //if the previous node is null and the next one isn't
		else return false;
	}
	
	/**
	 * Get the data at the current iterator position
	 * Return the data if appropriate, otherwise return null
	 */
	public T value() {
		/**
		 * @return the value of the current node (given it's valid)
		 */
		/* TODO: Implement this method */
		T value = null;
		if (!isPastBeginning() && !isPastEnd()) value = curNode.getData();
		return value;
	}
	
	/**
	 * These two methods move the cursor of the iterator
	 * forward / backward one position
	 */
	public void moveForward() { 
		/* TODO: Implement this method */
		/*       (Otherwise, do not move at all) */
		if (!isPastEnd()) curNode = curNode.next;
		else {}
	}
	
	public void moveBackward() { 
		/* TODO: Implement this method */
		/*       (Otherwise, do not move at all) */
		if (!isPastBeginning()) curNode = curNode.prev;
		else {}
	}
}



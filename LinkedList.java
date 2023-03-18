// Name: Artie Humphreys
// Computing ID: bsg6vr@virginia.edu
// Homework Name: Homework 6: Linked List
// Resources used: None

package list;

/**
 * 
 * A custom-built linked list
 * T here is the type the list stores
 */
public class LinkedList<T> implements SimpleList<T>{

	/* Dummy head and tail */
	private ListNode<T> head, tail;
	private int size;
	
	public LinkedList() {
		/**
		 * Default Constructor
		 */
		/* TODO: Implement this method */
		head = new ListNode<T>(null);
		tail = new ListNode<T>(null); //initializing head and tail
		size = 0;
		head.next = tail; //no nodes in between
		tail.prev = head;
	}

	/**
	 * Returns the size of this list, i.e., the number
	 * of nodes currently between the head and tail
	 * @return number of elements in the list
	 */
	public int size() {
		int count = 1;
		/* TODO: Implement this method */  
		if (head.next == tail) { //no items in between
			size = 0;
			return 0;
		}
		else {
			for (int i = 0; i < size-1; i ++){
				if (head.next == tail.prev){ //check to see if the end of the LinkedList has been reached
					//count += 1;
					break;
				}
				count +=1;
			}
		}
		size = count;
		return count;
	}
	
	/**
	 * Clears out the entire list
	 */
	public void clear() {
		/* TODO: Implement this method */
		size = 0;
		head.next = tail;
		tail.prev = head;

	}
	
	/**
	 * Inserts new data at the end of the list (i.e., just before the dummy tail node)
	 * @param data
	 */
	public void insertAtTail(T data) {
		/* TODO: Implement this method */
		if (size == 0) { //edge case
			size = 1;
			ListNode newNode = new ListNode<>(data);
			head.next = newNode;
			tail.prev = newNode; //pointing at the new item
			newNode.next = tail;
			newNode.prev = head;
		}
		else {
			ListNode newNode = new ListNode<>(data);
			ListNode prev = tail.prev; //last node in the linked list
			newNode.prev = prev;
			newNode.next = tail; //pointers for new node

			prev.next = newNode; //pointers to the new node
			tail.prev = newNode;
			size += 1;
		}
	}
	
	/**
	 * Inserts data at the front of the list (i.e., just after the dummy head node
	 * @param data
	 */
	public void insertAtHead(T data) {
		/* TODO: Implement this method */
		if (size == 0){ //edge case
			ListNode newNode = new ListNode<>(data);
			head.next = newNode;
			tail.prev = newNode;
			newNode.next = tail;
			newNode.prev = head;
			size = 1;
		}
		else {
			ListNode newNode = new ListNode<>(data);
			ListNode next = head.next; //first item in linked list (this will be the second item)

			newNode.next = next;
			newNode.prev = head;

			next.prev = newNode;
			head.next = newNode;
			size += 1;
		}
	}
	
	/**
	 * Inserts node such that index becomes the position of the newly inserted data
	 * @param data
	 * @param index
	 */
	public void insertAt(int index, T data) {
		/* TODO: Implement this method */
		if (index < 0 || index > size){ //checking for a valid input
			return;
		}
		else if (size == 0){ //edge case
			size = 1;
			ListNode newNode = new ListNode<>(data);
			head.next = newNode;
			tail.prev = newNode;
			newNode.next = tail;
			newNode.prev = head;
		}
		else {
			int currentIndex = 0;
			ListNode current = head.next;
			while (index != currentIndex){ //using this to get the  item in the linked link at the specified index
				current = current.next;
				currentIndex+=1;
			}
			ListNode newNode = new ListNode(data);
			ListNode prev = current.prev;
			newNode.next = current; //assigning pointers
			newNode.prev = prev;
			prev.next = newNode;
			current.prev = newNode;
			size += 1;
		}
	}
	
	/**
	 * Inserts data after the node pointed to by iterator
	 */
	public void insert(ListIterator<T> it, T data) {
		/* TODO: Implement this method */
		if (it.isPastEnd() != true){ //checking to see if the iterator is at a valid position
			ListNode newNode = new ListNode(data);
			ListNode next = (it.curNode.next);
			it.curNode.next = newNode; //assignment
			next.prev = newNode;
		}
		size += 1;
	}
	
	public T removeAtTail(){
		/**
		 * Removes an item from the end of the linked list
		 * @return the value of the removed item
		 */
		/* TODO: Implement this method */
		T value = null; //the return value; it returns null if there isn't a valid value
		if (size == 0){}
		else if (size == 1){ //kinda edge case
			value = (head.next).getData();
			head.next = tail;
			tail.prev = head;
		} else {
			ListNode last = tail.prev; //for linked lists with a size of 2 or more
			value = (T) last.getData(); //data for return
			ListNode prev = last.prev;
			prev.next = tail;
			tail.prev = prev;
		}
		size -= 1;
		return value;
	}
	
	public T removeAtHead(){
		/**
		 * Removes an item from the beginning of the linked list
		 * @return the value of the removed item
		 */
		/* TODO: Implement this method */
		T value = null;
		if (size == 0){} //same cases as removeAtTail
		if (size == 1){
			value = head.next.getData();
			head.next = tail;
			tail.prev = head;
			size -= 1;
		} else {
			ListNode first = head.next;
			value = (T) first.getData();
			ListNode next = first.next;
			next.prev = head;
			head.next = next;
			size -= 1;
		}
		return value;
	}
	
	/**
	 * Remove based on Iterator position
	 * Sets the iterator to the node AFTER the one removed
	 */
	public T remove(ListIterator<T> it) {
		/* TODO: Implement this method */
		ListNode curr = it.curNode;
		ListNode next = curr.next;
		ListNode prev = curr.prev;
		T currData = (T) curr.getData(); //for return
		if (!it.isPastEnd()){ //if the current node is at a valid position
			prev.next = next;
			next.prev = prev;
			it.curNode = next;
		}
		size -= 1;
		return currData;
	}
	
	/**
	 * Returns index of first occurrence of the data in the list, or -1 if not present
	 * @param data
	 * @return
	 */
	public int find(T data) {
		/* TODO: Implement this method */
		ListNode node = head.next;
		T nodeValue = (T) node.getData();
		if (data == null){ //for if the input is null
			for (int i = 0; i < size; i++){
				if (node == data) return i;
				node = node.next;
				nodeValue = (T) node.getData();
			}
		}
		for (int j = 0; j < size; j++){ //if input is not null
			if (nodeValue == null){ //if the item in the linked list is null
				if (nodeValue == data) return j;
			} else if (nodeValue.equals(data)) return j; //if both aren't null
			node = node.next;
			nodeValue = (T) node.getData();
		}
		return -1; //if there isn't a match
	}
	
	/**
	 * Returns the data at the given index, null if anything goes wrong (index out of bounds, empty list, etc.)
	 * @param index
	 * @return
	 */
	public T get(int index) { 
		/* TODO: Implement this method */
		ListNode node = head.next;
		if (size == 0) return null;
		else if (index > size || index < 0) return null; //out of bounds cases
		else if (size == 1) return (T) node.getData(); //had some trouble with linked lists of size 1
		else {
			for (int i = 0; i < index; i++) {
				node = node.next; //iterating through the list based on the given index
			}
		}
		return (T) node.getData();
	}
	
	/**
	 * Returns the list as space separated values
	 */
	public String toString() {
		String toRet = "[";
		
		ListNode<T> curNode = head.next;
		while(curNode != tail) {
			toRet += curNode.getData() + ", ";
			curNode = curNode.next;
		}
		
		return toRet + "]";
	}
	
	/* Return iterators at front and end of list */
	public ListIterator<T> front(){
		/**
		 * @return an iterator at the front of the linked list
		 */
		/* TODO: Implement this method */
		ListIterator curr = new ListIterator(head.next);
		return curr;
	}

	public ListIterator<T> back(){
		/**
		 * @return an iterator at the end of the linked list
		 */
		/* TODO: Implement this method */
		ListIterator curr = new ListIterator(tail.prev);
		return curr;
	}
	
	
}

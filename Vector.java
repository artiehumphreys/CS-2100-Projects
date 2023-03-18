package vector;

import java.lang.reflect.Array;
import java.util.Arrays;
// Name: Artie Humphreys
// Computing ID: e.g. bsg6vr@virginia.edu
// Homework Name: e.g. Homework 5 - Vectors
// Resources used: Piazza (if that counts)

	public class Vector<T> implements SimpleList<T> {

		private T[] itemArray;
		private int size = 0;
		private static final int INITIAL_CAPACITY = 100;

		public Vector() {
			this(INITIAL_CAPACITY);  // calls the other constructor that takes an int parameter
		}

		@SuppressWarnings("unchecked")
		public Vector(int capacity) {
			/**
			 * Constructor
			 * @param the size of the object array
			 */
			this.itemArray = (T[]) new Object[capacity];
			this.size = 0;
		}

		public int capacity() {
			/**
			 * returns the length of the item array (I chose not to use it)
			 */
			return this.itemArray.length;
		}

		/**
		 * When the underlying array fills up, we need to somehow resize it to accommodate the
		 * Vectors's elements.
		 * Ignores the request if the requested new capacity is too small to fit the elements
		 * already in the Vector
		 */
		public void resize(int newCapacity) {
			/**
			 * Re-sizing the vector
			 * @param the new size of the Vector (more specifically, its object array)
			 */
			// TODO: Implement this method
			if (newCapacity >= size) {
				T[] placeHolder = (T[]) new Object [newCapacity]; //used for keeping the items, then putting them back into the array after resizing
				if (newCapacity >= this.itemArray.length) { //this is-else if pair is for mapping the values to the correct length of the itemArray.
					for (int i = 0; i < this.itemArray.length; i++) { //i < this.itemArray.length
						placeHolder[i] = this.itemArray[i];
					}
				}
				else if (newCapacity < this.itemArray.length){
					for (int i = 0; i < newCapacity; i++) { //i < newCapacity
						placeHolder[i] = this.itemArray[i];
					}
				}
				this.itemArray = (T[]) new Object[newCapacity]; //declaring the itemarray as a fresh itemarray with the given capacity
				for (int i = 0; i < this.itemArray.length; i++) { //pasting back into the new array
					T t = placeHolder[i];
					this.itemArray[i] = t;
				}
			}
			else {}
			size = this.size(); //resizing

		}

		@Override
		public int size() {
			/**
			 * @return the amount of non-null items in the Vector
			 */
			// TODO: Implement this method
			int count = 0;
			for (int i = 0; i < this.itemArray.length; i++){
				if (this.get(i) != null) count ++;
			}
			return count;
		}

		@Override
		public void clear() {
			/**
			 * Removes all of the elements from this vector
			 */
			// TODO: Implement this method
			for (int i = 0; i < this.itemArray.length; i++){
				this.itemArray[i] = null;
			}
			size = 0;
		}
		@Override
		public void insertAtTail(T item) {
			/**
			 * @param the desired item to be added to the Vector
			 */
			if (this.size == this.capacity()) {
				// Array is full
				this.resize(2 * this.capacity());
			}
			// Insert the new item at the end of the array
			this.itemArray[this.size] = item;
			this.size++;
		}

		@Override
		public void insertAtHead(T item) {
			/**
			 * Inserting an item to the beginning of the vector
			 * @param item: the item to be added to the beginning of the vector
			 */
			// TODO: Implement this method
			T[] placeHolder = (T[]) new Object [itemArray.length];
			for (int i = 0; i < this.capacity(); i++) placeHolder[i] = itemArray[i];
			T placeHolderLastNum = this.itemArray[itemArray.length-1];
			//System.out.println(placeHolderLastNum);
			if(this.size() >= this.capacity())this.resize(this.size()+1);
			for (int i = 1; i < this.itemArray.length; i++) this.itemArray[i] = placeHolder[i-1];
			this.itemArray[0] = item;
			//this.itemArray[itemArray.length-1] = placeHolderLastNum;
			size = size +1;
		}

		@Override
		public void insertAt(int index, T item) {
			/**
			 * Inserting an item at any point in the vector
			 * @param index: the index that the item will be added to
			 * @param item: the item to be added to the vector
			 */
			// TODO: Implement this method
			if (index > this.size || index < 0) { //invalid index
				return;
			}
			if (capacity() == 0){
				this.resize(index+1);
			}
			if (this.size == this.capacity()) {
				this.resize(1 + this.capacity()); //this could be better optimized
			}
			for (int i = this.size - 1; i >= index; i--) {//shift elements from index to the end one position to the right
				this.itemArray[i + 1] = this.itemArray[i];
			}
			//inserting the item
			this.itemArray[index] = item;
			this.size = this.size + 1;
		}

		@Override
		public T removeAtTail() {
			/**
			 * Removing the last item from the Vector
			 * @return the item removed from the Vector
			 */
			// TODO: Implement this method
			T t = this.itemArray[this.size()-1];
			T[] placeholder = (T[]) new Object [this.capacity()-1]; //Making the placeholder 1 size smaller than the Vector
			for (int i = 0; i < this.size()-1; i++) placeholder[i] = this.itemArray[i];
			this.itemArray = (T[]) new Object[placeholder.length]; //instantiating as a new itemarray
			for (int i = 0; i < placeholder.length; i++) this.itemArray[i] = placeholder[i];
			this.size = this.size - 1;
			return t;
		}

		@Override
		public T removeAtHead() {
			/**
			 * Removing the first item from the Vector
			 * @return the item removed from the Vector
			 */
			// TODO: Implement this method
			T t = this.itemArray[0];
			T[] placeholder = (T[]) new Object [this.capacity()-1];


			for (int i = 1; i < this.size(); i++) placeholder[i-1] = this.itemArray[i]; //shifting over the list by 1 index
			this.itemArray = (T[]) new Object[placeholder.length];
			for (int i = 0; i < placeholder.length; i++) this.itemArray[i] = placeholder[i];
			this.size = this.size - 1;
			return t;
		}

		@Override
		public int find(T item) {
			/**
			 * finding the index of an item given an item
			 * @param the item to find in the Vector
			 * @return the index of the item
			 */
			// TODO: Implement this method
			if (size == 0) return -1;
			if(item == null) {
				for (int i = 0; i < this.itemArray.length; i++) {
					if (this.itemArray[i] == item) {
						return i;
					}
				}
			}
			for (int i = 0; i < this.itemArray.length; i++){
				if (this.itemArray[i] == null){
					if (this.itemArray[i] == (item)){
						return i;
					}
				}
				else if (this.itemArray[i].equals(item)){
					return i;
				}
			}
			return -1;
		}

		@Override
		public T get(int index) {
			/**
			 * get() method
			 * @param index: the index of the desired item
			 * @return the item in the Vector of the inputted index
			 */
			// TODO: Implement this method
			if (size == 0) return null; //edge case
			return (this.itemArray[index]);
		}

		/*
		 * This toString() method allow you to print a nicely formatted version of your Vector. E.g.
		 *     System.out.println( myVector );
		 * It uses utility methods in the Java Arrays library.
		 */
		@Override
		public String toString() {
			/**
			 * toString() method
			 * @return the vector as a string
			 */
//		return Arrays.toString(this.itemArray); // prints entire array from 0 to capacity-1
			return Arrays.toString(Arrays.copyOfRange(this.itemArray, 0, this.size)); // prints from 0 to size-1
		}

	}


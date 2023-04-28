// Name: Artie Humphreys

// Computing ID: bsg6vr@virginia.edu

// Homework Name: HW 13-Sorting Algorithms

// Resources used: https://www.geeksforgeeks.org/merge-sort/ https://www.tutorialspoint.com/data_structures_algorithms/quick_sort_algorithm.htm

import java.util.Arrays;

public class SortingAlgorithms {

	/*
	 * Swaps the elements and indices i and j in list
	 * */
	private static <T> void swap(T[] list, int i, int j) {
		if (i < 0 || i >= list.length)
			return;
		if (j < 0 || j >= list.length)
			return;
		T temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	// ####################
	// ## INSERTION SORT ## ----------------------------------------------------------------------
	// ####################

	/**
	 * Usually a slow sorting algorithm. Insertion sort.
	 * @param list - An array of items
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] list) {
		for (int i = 1; i < list.length; i++) { //fixing the bounds
			T val = list[i];
			int j = i-1;
			while (j >= 0 && val.compareTo(list[j]) < 0) { //need to check first element (instead of > 0 use >= 0)
				list[j+1] = list[j];
				j--;
			}
			list[j+1] = val;
		}
	}

	//=================================================================================


	// ################
	// ## MERGE SORT ## ----------------------------------------------------------------------
	// ################	
	/**
	 * Fully recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the array
	 * (i.e., index i to j inclusive) that we want to sort.
	 *
	 * @param list - An array of items
	 */
	public static<T extends Comparable<T>> void mergeSort(T[] list) {
		mergeSort(list, 0, list.length - 1);
	}
	public static<T extends Comparable<T>> void mergeSort(T[] list, int i, int j) {
		//TODO: write the body of this method
		if (i < j) { //recursive calls to get every element
			int mid = (i + j) / 2;
			mergeSort(list, i, mid);
			mergeSort(list, mid + 1, j);
			merge(list, i, mid, j);
		}
	}

	/**
	 * Merge method for Merge Sort algorithm.
	 * Your mergeSort algorithm will call this method as appropriate to do the merging.
	 * @param list - An array of items
	 * @param i - lower bound index
	 * @param mid - middle index
	 * @param j - upper bound index
	 */
	public static<T extends Comparable<T>> void merge(T[] list, int i, int mid, int j) {
		//TODO: write the body of this method

		//Reminder: when using a generic type, to create a new array to hold items of type T,
		//  you do something like the following:
		@SuppressWarnings("unchecked")
		T[] merged = (T[]) new Comparable[j-i+1]; // space for new merged list to hold (sorted) sublist
		if (j <= i){
			return;
		}
		@SuppressWarnings("unchecked")
//		T left[] = (T[]) new Comparable[mid-i+1];
//		T right[] = (T[])  new Comparable[j-mid];
//		for (int h = 0; h <= mid-i; h++){
//			left[h] = list[i+h];
//		}
//		for(int k = 0; k < j-mid; k++){
//			right[k] = list[k+mid+1];
//		}

		int leftpos = i;
		int rightpos = mid+1;
		int mergedpos = 0;

		while (leftpos <= mid && rightpos <= j){ //within the bounds of the subarray
			if (list[leftpos].compareTo(list[rightpos]) <= 0){ //left is less than
				merged[mergedpos] = list[leftpos];
				leftpos = leftpos + 1; //incrementing position
				//System.out.println("after left add: " + leftpos);
			} else { //left is greater than
				merged[mergedpos] = list[rightpos];
				rightpos = rightpos + 1;
				//System.out.println("after right add: " + rightpos);
			}
			mergedpos += 1;

		}
//		System.out.println("leftpos is "+leftpos);
//		System.out.println("rightpos is "+rightpos);
		while(leftpos <= mid){ //any elements left over
			merged[mergedpos] = list[leftpos];
			leftpos++;
			mergedpos++;
		}
		while(rightpos <= j){
			merged[mergedpos] = list[rightpos];
			rightpos++;
			mergedpos++;
		}
		for (int k = 0; k < merged.length; k++) { //updating list
			list[i + k] = merged[k];
		}
	}

	//=================================================================================

	// #######################
	// ## HYBRID MERGE SORT ## ----------------------------------------------------------------------
	// #######################
	/**
	 * Hybrid recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the array
	 * (i.e., index i to j inclusive) that we want to sort.
	 * For this implementation, when the size of the portion of the array
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the array.
	 *
	 *
	 * @param list - An array of items
	 */
	public static<T extends Comparable<T>> void mergeSortHybrid(T[] list) {
		mergeSortHybrid(list, 0, list.length - 1);
	}
	public static<T extends Comparable<T>> void mergeSortHybrid(T[] list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if (list.length < 100) insertionSort(list);
		else mergeSort(list, i, j);
	}

	//=================================================================================
	
	// ###############
	// ## QUICKSORT ## ----------------------------------------------------------------------
	// ###############	
	/**
	 * Fully recursive Quicksort and associated helper method.
	 * The second method below provides the portion of the array
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like. 
	 * 
	 * @param list - An array of items
	 */
	public static<T extends Comparable<T>> void quickSort(T[] list) {
		quickSort(list, 0, list.length - 1);
	}
	public static<T extends Comparable<T>> void quickSort(T[] list, int i, int j) {
		//TODO: write the body of this method
		if (i < j){ //recursive calls
			int partition = partition(list, i, j);

			quickSort(list, i, partition-1);
			quickSort(list, partition+1, j);
		}
	}
	
	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An array of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partition(T[] list, int i, int j) {	
		//TODO: write the body of this method
		T pivot = list[j]; //last element as pivot
		int l = i-1; //index
		for (int h = i; h < j; h++){
			if (list[h].compareTo(pivot) <= 0){ //less than pivot
				l++;
				swap(list, l, h);
			}
		}
//		T temp = list[l+1];
//		list[l+1] = list[j];
//		list[j] = temp;
		swap(list, l+1, j); //put pivot in

		return l+1;
	}
	
	//=================================================================================

    // ######################
	// ## HYBRID QUICKSORT ## ----------------------------------------------------------------------
	// ######################
	/**
	 * Hybrid Quicksort and associated helper method.
	 * The second method below provides the portion of the array
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like.
	 * For this implementation, when the size of the portion of the array
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the array.
	 *
	 * @param list - An array of items
	 */
	public static<T extends Comparable<T>> void quickSortHybrid(T[] list) {
		quickSortHybrid(list, 0, list.length - 1);
	}
	public static<T extends Comparable<T>> void quickSortHybrid(T[] list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if (list.length < 100) insertionSort(list);
		else quickSort(list, i, j);
	}

	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An array of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partitionHybrid(T[] list, int i, int j) {
		//TODO: write the body of this method
		return partition(list,i,j);
	}

}
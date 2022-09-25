package project5package;

import java.util.Arrays;

public class Sort {

	// Below are all the methods required for MergeSort.

	public static void merge(int array[], int left, int middle, int right) {
		int subArraySize1 = middle - left + 1; // First, determines the size of the left and right sub-arrays.
		int subArraySize2 = right - middle;
		int leftArray[] = new int[subArraySize1]; // Creates the left and right sub-arrays.
		int rightArray[] = new int[subArraySize2];
		for (int i = 0; i < subArraySize1; i++) // Adds the appropriate values to each sub-array.
			leftArray[i] = array[left + i];
		for (int j = 0; j < subArraySize2; j++)
			rightArray[j] = array[middle + 1 + j];
		int x = 0; // x represents the index of the first sub-array.
		int y = 0; // y represents the index of the second sub-array.
		int z = left; // z represents the index of the original array.
		while (x < subArraySize1 && y < subArraySize2) {
			if (leftArray[x] >= rightArray[y]) { // Checks which sub-array has the greater value and adds it.
				array[z] = leftArray[x];
				x++;
			} else {
				array[z] = rightArray[y];
				y++;
			}
			z++;
		}
		while (x < subArraySize1) { // If applicable, adds remaining elements of the left sub-array.
			array[z] = leftArray[x];
			x++;
			z++;
		}
		while (x < subArraySize2) { // If applicable, adds remaining elements of the right sub-array.
			array[z] = rightArray[y];
			y++;
			z++;
		}
	}

	public static void mergeSort(int array[], int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2; // Determines the index of the middle element.
			mergeSort(array, left, middle); // Recursively sorts sub-arrays.
			mergeSort(array, middle + 1, right);
			merge(array, left, middle, right); // Lastly, merges the arrays into one.
		}
	}

	// Below are all the methods required for QuickSort.

	public static void swap(int[] array, int i, int j) { // Swaps the elements in indexes i and j in an array.
		int placeholder = array[i];
		array[i] = array[j];
		array[j] = placeholder;
	}

	public static int partition(int[] array, int left, int right) {
		int pivotPoint = array[right]; // Determines the pivot point.
		int i = (left - 1); // index of the larger element
		for (int j = left; j < right; j++) {
			if (array[j] > pivotPoint) { // Checks if the current element is larger than the pivot point.
				i++; // Increments i by 1 and swaps.
				swap(array, i, j);
			}
		}
		swap(array, i + 1, right); // Swaps i + 1, the index of the smallest element, to the end
		return (i + 1);
	}

	public static void quickSort(int[] array, int left, int right) {
		if (left < right) { // Determines the partition index.
			int partitionIndex = partition(array, left, right);
			quickSort(array, left, partitionIndex - 1); // Sorts elements before partitioning.
			quickSort(array, partitionIndex + 1, right); // Sorts elements after partitioning.
		}
	}

	// Below are all the methods required for InsertionSort.

	public static void insertionSort(int array[]) {
		int length = array.length;
		for (int i = 1; i < length; i++) {
			int key = array[i]; // references each index of the array
			int j = i - 1;
			while (j >= 0 && array[j] < key) { // Sorts elements in descending order.
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}

	// Below are all the methods required for Select. This algorithm also uses the
	// "partition" and "swap" methods above.

	public static int mySelect(int[] array, int left, int right, int k) {
		if (k >= 0 && k <= right - left + 1) {
			int key = partition(array, left, right); // Makes swaps to the array as necessary to determine the key.
			if (key - left == k) {
				return array[key]; // Method ends once the kth largest element is found.
			}
			if (key - left > k) {
				return mySelect(array, left, key - 1, k); // Runs method again if the key is larger than k.
			}
			return mySelect(array, key + 1, right, k - key + left - 1);
		} // Runs method again if the key is smaller than k.
		return 0;
	}

	public static int select(int[] array, int k) {
		if (k >= array.length) { // Checks if the input k is larger than the array length
			System.out.println("There are less than " + k + " elements in this array.");
			return -1;
		}
		int selection = mySelect(array, 0, array.length - 1, k); // Executes select on the array.
		System.out.println("The " + k + "th largest element in the array is " + selection + ".");
		return selection;
	}

	// Below are all the methods required for UpgradedQuickSort.

	public static void myUpgradedQuickSort(int[] array, int first, int last, int d, int k) {
		if (last - first < k) { // Executes insertion sort if subarray has less than k values.
			insertionSort(array);
			return;
		}
		if (d == 0) { // Executes merge sort once d is reached.
			mergeSort(array, first, last);
			return;
		}
		int partitionPoint = partition(array, first, last); // Recursively calls the method changing first, last, and d.
		myUpgradedQuickSort(array, first, partitionPoint - 1, d - 1, k);
		myUpgradedQuickSort(array, partitionPoint + 1, last, d - 1, k);
	}

	public static void upgradedQuickSort(int[] array, int d, int k) {
		myUpgradedQuickSort(array, 0, array.length - 1, d, k);
	}

	// Main tester method.

	public static void main(String[] args) {
		System.out.println("THE TESTS ARE NOW RUNNING.");
		System.out.println("");
		System.out.println("First, we create an array with an even number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray = new int[10];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		mergeSort(newArray, 0, newArray.length - 1);
		System.out.println(Arrays.toString(newArray));
		System.out.println("");
		System.out.println("Next, we create an array with an odd number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray2 = new int[9];
		for (int i = 0; i < newArray2.length; i++) {
			newArray2[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray2));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		mergeSort(newArray2, 0, newArray2.length - 1);
		System.out.println(Arrays.toString(newArray2));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in ascending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] ascendingArray = {1, 3, 5, 17, 54, 69, 71, 80, 90, 92, 95, 100};
		System.out.println(Arrays.toString(ascendingArray));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		mergeSort(ascendingArray, 0, ascendingArray.length - 1);
		System.out.println(Arrays.toString(ascendingArray));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in descending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] descendingArray = {99, 88, 77, 69, 63, 23, 12, 10, 8, 4, 2, 0};
		System.out.println(Arrays.toString(descendingArray));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		mergeSort(descendingArray, 0, descendingArray.length - 1);
		System.out.println(Arrays.toString(descendingArray));
		System.out.println("");
		System.out.println("Now, we create a new array with an even number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray3 = new int[10];
		for (int i = 0; i < newArray3.length; i++) {
			newArray3[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray3));
		System.out.println("");
		System.out.println("Testing QuickSort on the array. The array is below.");
		quickSort(newArray3, 0, newArray3.length - 1);
		System.out.println(Arrays.toString(newArray3));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in ascending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] ascendingArray2 = {1, 3, 5, 17, 54, 69, 71, 80, 90, 92, 95, 100};
		System.out.println(Arrays.toString(ascendingArray2));
		System.out.println("");
		System.out.println("Testing QuickSort on the array. The array is below.");
		quickSort(ascendingArray2, 0, ascendingArray2.length - 1);
		System.out.println(Arrays.toString(ascendingArray2));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in descending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] descendingArray2 = {99, 88, 77, 69, 63, 23, 12, 10, 8, 4, 2, 0};
		System.out.println(Arrays.toString(descendingArray2));
		System.out.println("");
		System.out.println("Testing QuickSort on the array. The array is below.");
		mergeSort(descendingArray2, 0, descendingArray2.length - 1);
		System.out.println(Arrays.toString(descendingArray2));
		System.out.println("");
		System.out.println("Next, we create a new array with an odd number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray4 = new int[9];
		for (int i = 0; i < newArray4.length; i++) {
			newArray4[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray4));
		System.out.println("");
		System.out.println("Testing QuickSort on the array. The array is below.");
		quickSort(newArray4, 0, newArray4.length - 1);
		System.out.println(Arrays.toString(newArray4));
		System.out.println("");
		System.out.println("Now, we create a new array with an even number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray5 = new int[10];
		for (int i = 0; i < newArray5.length; i++) {
			newArray5[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray5));
		System.out.println("");
		System.out.println("Testing InsertionSort on the array. The array is below.");
		insertionSort(newArray5);
		System.out.println(Arrays.toString(newArray5));
		System.out.println("");
		System.out.println("Next, we create a new array with an odd number of elements.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray6 = new int[9];
		for (int i = 0; i < newArray6.length; i++) {
			newArray6[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray6));
		System.out.println("");
		System.out.println("Testing InsertionSort on the array. The array is below.");
		insertionSort(newArray6);
		System.out.println(Arrays.toString(newArray6));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in ascending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] ascendingArray3 = {1, 3, 5, 17, 54, 69, 71, 80, 90, 92, 95, 100};
		System.out.println(Arrays.toString(ascendingArray3));
		System.out.println("");
		System.out.println("Testing InsertionSort on the array. The array is below.");
		insertionSort(ascendingArray3);
		System.out.println(Arrays.toString(ascendingArray3));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in descending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] descendingArray3 = {99, 88, 77, 69, 63, 23, 12, 10, 8, 4, 2, 0};
		System.out.println(Arrays.toString(descendingArray3));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		insertionSort(descendingArray3);
		System.out.println(Arrays.toString(descendingArray));
		System.out.println("");
		System.out.println("Now, we create a new array.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray7 = new int[10];
		for (int i = 0; i < newArray7.length; i++) {
			newArray7[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray7));
		System.out.println("");
		System.out.println("Testing Select on the array.");
		select(newArray7, 0);
		select(newArray7, 1);
		select(newArray7, 2);
		select(newArray7, 3);
		select(newArray7, 4);
		select(newArray7, 5);
		select(newArray7, 6);
		select(newArray7, 7);
		select(newArray7, 8);
		select(newArray7, 9);
		select(newArray7, 10);
		select(newArray7, 38201);
		System.out.println("");
		System.out.println("Now, we create a new array.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray8 = new int[25];
		for (int i = 0; i < newArray8.length; i++) {
			newArray8[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray8));
		System.out.println("");
		System.out.println("Testing UpgradedQuickSort on the array.");
		upgradedQuickSort(newArray8, 8, 10);
		System.out.println(Arrays.toString(newArray8));
		System.out.println("");
		System.out.println("Now, we create a new array and test the method with different d and k values.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] newArray9 = new int[25];
		for (int i = 0; i < newArray9.length; i++) {
			newArray9[i] = (int) (Math.random() * Math.random() * 100);
		}
		System.out.println(Arrays.toString(newArray9));
		System.out.println("");
		System.out.println("Testing UpgradedQuickSort on the array.");
		upgradedQuickSort(newArray9, 12, 4);
		System.out.println(Arrays.toString(newArray9));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in ascending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] ascendingArray4 = {1, 3, 5, 17, 54, 69, 71, 80, 90, 92, 95, 100};
		System.out.println(Arrays.toString(ascendingArray4));
		System.out.println("");
		System.out.println("Testing UpgradedQuickSort on the array. The array is below.");
		upgradedQuickSort(ascendingArray4, 5, 8);
		System.out.println(Arrays.toString(ascendingArray4));
		System.out.println("");
		System.out.println("Next, we create an array that is already sorted in descending order.");
		System.out.println("");
		System.out.println("The array is below.");
		int[] descendingArray4 = {99, 88, 77, 69, 63, 23, 12, 10, 8, 4, 2, 0};
		System.out.println(Arrays.toString(descendingArray4));
		System.out.println("");
		System.out.println("Testing MergeSort on the array. The array is below.");
		mergeSort(descendingArray4, 10, 2);
		System.out.println(Arrays.toString(descendingArray4));
		System.out.println("");
		System.out.println("THE TESTS ARE NOW COMPLETE.");
	}
}
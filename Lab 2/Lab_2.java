import java.util.Arrays;
import java.util.Scanner;

/*
 *  The interpolated search performed better than the linear search. This is because the linear search needs to perform a 
 *  sequential scan of the array whereas the interpolated search estimates the position based on the values of the sorted array. 
 *  Therefore, the time complexity of linear search is O(n) whereas the time complexity of interpolated search is usually O(log(log(n))), given
 *  it's not a worse case scenario.
 * 
 */

/*
 * To improve the linear search running time, I moved the found element to the front of the array to reduce future search times. 
 * This helps improve performance of the next search by reducing number of iterations. Thus, the found search key will always be at index 0
 * if included in the array.
 * 
 */

public class Lab2 {
	

    public static int linearSearch(int array[], int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key)
                return i;
        }
        return -1;
    }

    public static int interpolationSearch(int[] array, int low, int high, int key) {
        if (low <= high && key >= array[low] && key <= array[high]) {
            int pos = low + (((high - low) / (array[high] - array[low])) * (key - array[low]));

            if (array[pos] == key)
                return pos;

            if (array[pos] < key)
                return interpolationSearch(array, pos + 1, high, key);

            return interpolationSearch(array, low, pos - 1, key);
        }
        return -1;
    }
    
    public static int linearSearchImproved(int array[], int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                if (i != 0) {
                    int temp = array[i];
                    System.arraycopy(array, 0, array, 1, i);
                    array[0] = temp;
                }
                return 0;  
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the elements in the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);

        System.out.println("Enter the search key: ");
        int key = scanner.nextInt();

        // Linear Search
        long startTime = System.nanoTime();
        int linearSearchResult = linearSearch(array, key);
        long endTime = System.nanoTime();
        long linearSearchTime = endTime - startTime;
        System.out.println();
        System.out.println("Using Linear Search:");
        if (linearSearchResult != -1) {
            System.out.println("Search key FOUND at index " + linearSearchResult + ".");
        } else {
            System.out.println("Search key NOT FOUND.");
        }
        System.out.println("Linear Search Execution Time: " + linearSearchTime + " nanoseconds.");

        // Interpolation Search
        startTime = System.nanoTime();
        int interpolationSearchResult = interpolationSearch(sortedArray, 0, sortedArray.length - 1, key);
        endTime = System.nanoTime();
        long interpolationSearchTime = endTime - startTime;
        System.out.println();
        System.out.println("Using Interpolation Search:");
        if (interpolationSearchResult != -1) {
            System.out.println("Search key FOUND at index " + interpolationSearchResult + " in the sorted array.");
        } else {
            System.out.println("Search key NOT FOUND.");
        }
        System.out.println("Interpolation Search Execution Time: " + interpolationSearchTime + " nanoseconds.");
        
        // Improved Linear Search
        long start = System.nanoTime();
        int linearSearchResultImproved = linearSearchImproved(array, key);
        long end = System.nanoTime();
        System.out.println();
        System.out.println("Using Improved Linear Search:");
        if (linearSearchResultImproved != -1) {
            System.out.println("Search key FOUND at index 0.");
        } else {
            System.out.println("Search key NOT FOUND.");
        }
        System.out.println("Improved Linear Search Execution Time: " + (end - start) + " nanoseconds.");
   
    }
}
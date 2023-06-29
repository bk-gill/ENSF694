
import java.util.Scanner;
public class Lab2 {
	
	static int binarySearchIterative(int[] array, int key)
	{
	int low = 0, mid, high = array.length-1;
	while (low <= high)
	{
	mid = (low + high)/2;
	if(key < array[mid])
	high = mid - 1;
	else if(array[mid] < key)
	low = mid + 1;
	else
	return mid; 
	}
	return -1; 
	}

	
	
	
	public static void main(String[] args) {
		
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter the number of elements in the array: ");
		int number_elements = myObj.nextInt();
		System.out.println(number_elements);
	}

}

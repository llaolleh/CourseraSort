


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {

	
	private static long totalCompares = 0;
	private static int[] array;
	private static int[] test1;
	private static int[] test2;
	private static int[] test3;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
	quickSortMedian(10000, "quickSort.txt");
		//array = readFile(10, "10.txt");
		//array = new int[]{2,3,1,5,2,3};
		//medianCheck(array, 0, 9);
		//System.out.println();
		//for(int i: test1)
		//	System.out.print(i+ " ");
		//medianCheck(array, 0, 6);
	}
	public static void quickSortFront(int length, String filename) throws FileNotFoundException
	{
		test1 = readFile(length, filename);
		quickSort(test1, 0, length);
		System.out.println(totalCompares);
		
	}
	
	public static void quickSortBack(int length, String filename) throws FileNotFoundException
	{
		test1 = readFile(length, filename);
		quickSort2(test1, 0, length);
		System.out.println(totalCompares);
		
	}
	public static void quickSortBack2(int length, String filename) throws FileNotFoundException
	{
		test1 = readFile(length, filename);
		quickSortTwo(test1, 0, length);
		System.out.println(totalCompares);
		
	}
	
	public static void quickSortMedian(int length, String filename) throws FileNotFoundException
	{
		test1 = readFile(length, filename);
		quickSortMiddle(test1, 0, length);
		System.out.println(totalCompares);
		
	}
	
	
	
	
	public static int[] readFile(int arraySize, String filename) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(filename));
		int i = 0;
		int[] arr = new int[arraySize];
		while(scanner.hasNextInt())
		{
		     arr[i] = scanner.nextInt();
		     //System.out.println(arr[i]);
		     i++;
		}
		scanner.close();
		return arr;
	}
	
	
	public static void quickSort(int[] array, int left, int right)
	{
		if(right-left <= 1)
			return;
		int p = left;
		totalCompares += right-left-1;
		int pivot= partition(array,p, left,right);
		//System.out.println();
		quickSort(array, left, pivot-1);
		quickSort(array, pivot, right);
	}
	public static void quickSortMiddle(int[] array, int left, int right)
	{
		if(right-left <= 1)
			return;
		int p = left;
		totalCompares += right-left-1;
		int pivot= partition3(array,p, left,right);
		//System.out.println();
		//for( int i: array)
		//	System.out.print(i+ " ");
		//System.out.println("left "+ left +" p "+ p+ " pivot "+ + pivot+ " right "+right);
		
		quickSortMiddle(array, left, pivot-1);
		quickSortMiddle(array, pivot, right);
	}
	
	
	
	
	public static void quickSort2(int[] array, int left, int right)
	{
		if(right-left <= 1)
			return;
		int p = right-1;
		totalCompares += right-left-1;
		int pivot= partition2(array, p, left,right);
		/*System.out.println();
		for( int i: array)
			System.out.print(i+ " ");
		System.out.println("left "+ left +" p "+ p+ " pivot "+ + pivot+ " right "+right);
		*/
		quickSort2(array, left, pivot);
		quickSort2(array, pivot+1, right);
		
	}
	public static void quickSortTwo(int[] array, int left, int right)
	{
		if(right-left <= 1)
			return;
		int p = left;
		totalCompares += right-left-1;
		int pivot= partitionTwo(array, p, left,right);
		/*System.out.println();
		for( int i: array)
			System.out.print(i+ " ");
		System.out.println("left "+ left +" p "+ p+ " pivot "+ + pivot+ " right "+right);
		*/
		quickSortTwo(array, left, pivot-1);
		quickSortTwo(array, pivot, right);
		
	}
public static int partitionTwo(int[] array, int p, int left, int right){
		
	
	int temp = array[left];
	array[left] = array[right-1];
	array[right-1] = temp;
	int i = left+1;
	
	for(int j = i; j < right; j++)
	{
		if(array[j] < array[p])
		{
			temp = array[j];
			array[j]= array[i];
			array[i]=temp;
			i++;
		}
		
	}
	//totalCompares += right-left-1;
	temp = array[i-1];
	array[i-1] = array[p];
	array[p] = temp;
	return i;
	
	
}
	
	
	
	public static int partition2(int[] array, int p, int left, int right){
		
		int i = left;
		//save some unnecessary work
		while(array[i] < array[p])
			i++;
		
		for(int j = i ; j < right-1; j++)
		{
			if(array[j] < array[p])
			{
				int temp = array[j];
				array[j]= array[i];
				array[i]=temp;
				i++;
			}
			
		}
		
		//totalCompares += right-left-1;
		int temp = array[i];
		array[i] = array[p];
		array[p] = temp;
		return i;
		
		
		
	}
	
	public static int partition(int[] array,int p, int left, int right){
		int i = left+1;
		
		for(int j = i; j < right; j++)
		{
			if(array[j] < array[p])
			{
				int temp = array[j];
				array[j]= array[i];
				array[i]=temp;
				i++;
			}
			
		}
		//totalCompares += right-left-1;
		int temp = array[i-1];
		array[i-1] = array[p];
		array[p] = temp;
		return i;
		//remember, you shouldn't put the pivot back into either the left side or the right side
		// think carefully about how i is going to flow down the program
		
		
	}
	
	
	public static int findMiddle(int[] array, int left, int right)
	{
		//do initial swapping for best pivot
				int length = right -left;
				int middle;
				if( length % 2 == 0)
					middle = (right +left)/2 -1;
				else
					middle = (right + left)/2;
				
				return middle;
				
		
	}
	
	public static int findMedian(int[] array, int left, int right)
	{
		int middle = findMiddle(array, left, right);
		int median= middle;
		int[] tempArr= new int[2];
		if(array[middle] > array[left])
		{
			tempArr[0]=left;
			tempArr[1]=middle;
		}
		else
		{
			tempArr[0]=middle;
			tempArr[1]=left;
		}
		if(array[right-1] <=array[tempArr[0]])
			median = tempArr[0];
		else if(array[right-1]<= array[tempArr[1]])
			median =right -1;
		else if(array[right-1] > array[tempArr[1]])
			median = tempArr[1];
		//for( int i: array)
		//	System.out.print(" " +i);
		//System.out.println(" left: "+ left + " right: ," + right + " middle: ," + middle + " median index ," + median);
		return median;
		
		
	}
	
	
	public static int partition3(int[] array,int p, int left, int right){
		
		int median = findMedian(array, left, right); //index of the element that is the median
		
		int temp = array[left];
		array[left] = array[median];
		array[median] = temp;
		
		
		//do swap later, as in problem 2
		int i = left+1;
		for(int j = i; j < right; j++)
		{
			if(array[j] < array[p])
			{
				temp = array[j];
				array[j]= array[i];
				array[i]=temp;
				i++;
			}
			
		}
		//totalCompares += right-left-1;
		temp = array[i-1];
		array[i-1] = array[p];
		array[p] = temp;
		return i;
		//remember, you shouldn't put the pivot back into either the left side or the right side
		// think carefully about how i is going to flow down the program
		
		
	}
	

}

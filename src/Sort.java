

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sort {

	
	private static long totalInversions = 0;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		int[] array =readFile(100000);
		int[] test = { 9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0 };
		int[] test2 =  { 37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16, 48, 1, 39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29, 18, 3, 19, 0, 32, 46, 27, 31, 25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45 };
		int[] test3 = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 };
		int[] result =mergeCountAndSort(array, 0, 100000);
		//System.out.println();
		for (int x=990000; x<result.length; x++)
			System.out.print(result[x]+ " ");
		System.out.println();
		System.out.println(totalInversions);
	}
	
	public static int[] readFile(int arraySize) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("IntegerArray.txt"));
		int [] array = new int [arraySize];
		int i = 0;
		while(scanner.hasNextInt())
		{
		     array[i] = scanner.nextInt();
		     //System.out.println(array[i]);
		     i++;
		}
		scanner.close();
		return array;
	}
	
	
	public static int[] mergeCountAndSort(int[] array,int start, int length)
	{
		if(array.length < 1)
			return null;
		else if(array.length == 1)
			return array;
		
		else if( length % 2 ==0 )
		{
		int[] left = new int[length/2];
		int[] right = new int[length/2];
		//for (int i: array)
		//	System.out.print(i +" ");
		//System.out.println("");
		System.arraycopy(array, 0, left,0,length/2);
		System.arraycopy(array, length/2, right, 0, length/2);
		left = mergeCountAndSort(left, 0, length/2);
		right = mergeCountAndSort(right, 0, length/2);
		return countAndSort(left, right);
		}
		else
		{
			int[] left = new int[length/2];
			int[] right = new int[length/2+1];
			//for (int i: array)
			//	System.out.print(i +" ");
			//System.out.println("");
			System.arraycopy(array, 0, left,0,length/2);
			System.arraycopy(array, length/2, right, 0, length/2+1);
			left = mergeCountAndSort(left, 0, length/2);
			right = mergeCountAndSort(right, 0, length/2+1);
			return countAndSort(left, right);
		}
		
		
	}
	
	
	public static int[] countAndSort(int[] left, int[] right){
		int i = 0;
		int j = 0;
		int totalLength = left.length + right.length;
		//System.out.println("total length:" +totalLength);
		int[] newArr = new int[totalLength];
		for(int x = 0; x < totalLength; x++)
		{
			// check for index out of bounds
			if(i >= left.length)
			{
				newArr[x] = right[j];
				j++;
			}
			else if( j >= right.length)
			{
				newArr[x] = left[i];
				i++;
			}	
			else if(left[i] < right[j])
			{
				newArr[x] = left[i];
				i++;
			}
			else
			{
				if(left[i] > right[j]){
					totalInversions += left.length -i;
					//System.out.println(""+i+ " "+j);
					//System.out.println("total inversion:"+totalInversions);
					//for(int a: left)
					//	System.out.print(a+" ");
					//System.out.println();
					//for(int b: right)
					//	System.out.print(b+" ");
				//System.out.println();
							
					newArr[x] = right[j];
				j++;
			}
				else{
					i++;
				}
				}
			}
		
		
		return newArr;
		
		
		
	}
	
	public int[] mergeSort(){
		
		
		
		
		return null;
	}
}

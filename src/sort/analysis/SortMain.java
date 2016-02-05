package sort.analysis;

import java.util.Random;

public class SortMain {

	public static void main(String[] args) {
		
		int start = 1000;
		int power = 100;
		
		int[] arrSS = null;
		int[] arrIS = null;
		int[] arrMS = null;
		int[] arrQS = null;

		
	    Random randomGenerator = new Random();
	    
	    
		System.out.println("Time for sorting ");
		System.out.println("Array Length\tSelection Sort\tInsertion Sort\tMerge Sort\tQuick Sort");

		
		for(int increment = 0; increment < power; increment++) {
			arrSS = new int[(int)(start + start*increment)];
			arrIS = new int[(int)(start + start*increment)];
			arrMS = new int[(int)(start + start*increment)];
			arrQS = new int[(int)(start + start*increment)];
			
			for(int i = 0; i < (start + start*increment); i++) {
			      int randomInt = Math.abs(randomGenerator.nextInt());
			      arrSS[i] = randomInt;
			      arrIS[i] = randomInt;
			      arrMS[i] = randomInt;
			      arrQS[i] = randomInt;
			}
			long startTime;
			long endTime;
			double milli;
			
			startTime = System.nanoTime();
			doSelectionSort(arrSS);
			endTime = System.nanoTime();
			milli = (double)(endTime - startTime)/1000000;
			System.out.print(arrSS.length + "\t" + milli);
			
			startTime = System.nanoTime();
			int arr2[] = doInsertionSort(arrIS);
			endTime = System.nanoTime();
			milli = (double)(endTime - startTime)/1000000;
			System.out.print("\t" + milli);

			startTime = System.nanoTime();
			doMergeSort(arrMS,0,arrMS.length-1);
			endTime = System.nanoTime();
			milli = (double)(endTime - startTime)/1000000;
			System.out.print("\t" + milli);
			System.out.print("\n");

			
			
		}

	}

	static int[] doSelectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for(int j = i; j < arr.length; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
				
			}
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}
	
	static int[] doInsertionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for(int j = i; j > 0; j--) {
				if(arr[j - 1] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j-1] = temp;
				}
				
			}
		}
		return arr;
	}
	
	private static int[] doMergeSort(int[] array, int lowerIndex, int higherIndex) {
		if(lowerIndex < higherIndex) {
			int middleIndex = lowerIndex + (higherIndex - lowerIndex)/2;
			doMergeSort(array, lowerIndex, middleIndex);
			doMergeSort(array, middleIndex + 1, higherIndex);
            mergeParts(array, lowerIndex, middleIndex, higherIndex);

		}
		return array;
	}
	
	
	
	
	private static void mergeParts(int[] array, int lowerIndex, int middleIndex, int higherIndex) {
		
        int[] tempMergArr = new int[higherIndex + 1];
        for(int index = lowerIndex; index <= higherIndex; index ++) {
        	tempMergArr[index] = array[index];
        }
        
        int i = lowerIndex;
        int j = middleIndex + 1;
        int k = lowerIndex;
        
        while(i <= middleIndex && j <= higherIndex) {
        	if(tempMergArr[i] < tempMergArr[j]) {
        		array[k] = tempMergArr[i];
        		i++;
        	} else {
        		array[k] = tempMergArr[j];
        		j++;
        	}
    		k++;

        }
        while(i <= middleIndex) {
        	array[k] = tempMergArr[i];
            i++;
            k++;
        }
        while(j <= higherIndex) {
        	array[k] = tempMergArr[j];
            j++;
            k++;
        }

		
	}
	

}

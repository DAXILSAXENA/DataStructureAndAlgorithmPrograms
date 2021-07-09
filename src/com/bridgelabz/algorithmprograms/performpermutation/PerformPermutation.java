package com.bridgelabz.algorithmprograms.performpermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//Class Performing Permutation Using Recursion technique and returning ArrayList

class PermutationUsingRecursion {
	static ArrayList<String> array = new ArrayList<String>();

	public static ArrayList<String> permute(String str, int startIndex, int lastIndex) {
		if (startIndex == lastIndex) {
			array.add(str);
		} else {
			for (int i = startIndex; i <= lastIndex; i++) {
				str = swap(str, startIndex, i);
				permute(str, startIndex + 1, lastIndex);
				str = swap(str, startIndex, i);
			}
		}
		return array;
	}

	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
}

//Class Performing Permutation Using Iteration technique and returning ArrayList

class PermutationUsingIteration {
	static ArrayList<String> array = new ArrayList<String>();

	public static ArrayList<String> permute(String str) {

		char[] temp = str.toCharArray();
		Arrays.sort(temp);
		array.add(String.valueOf(temp));

		int index = 0;
		int lowest = 0;
		while (true) {
			for (int i = 0; i < temp.length - 1; i++) {
				if (temp[i] < temp[i + 1]) {
					lowest = i;
				}
			}
			index = lowest;
			int j = findCeiling(temp, index);
			if (j == index)
				break;

			swap(temp, index, j);

			String a = String.valueOf(temp);
			char[] b = a.substring(index + 1).toCharArray();
			Arrays.sort(b);
			a = a.substring(0, index + 1) + String.valueOf(b);
			temp = a.toCharArray();
			array.add(String.valueOf(temp));
		}
		return array;
	}

	public static int findCeiling(char[] temp, int index) {
		int k = index;
		char test = temp[index];
		while (k < temp.length - 1) {
			if (temp[index] < temp[k + 1]) {
				index = k + 1;
				break;
			}
			k++;
		}
		k = index;
		while (k < temp.length - 1) {
			if ((temp[index] > temp[k + 1]) && (temp[k + 1] > test)) {
				index = k + 1;
			}
			k++;
		}
		return index;
	}

	private static void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
}

public class PerformPermutation {

	// Main Function to input String from User and call methods and compare results
	// obtained by Recursion and Iteration methods

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the string: ");
		String str = input.next();
		input.close();
		int lengthOfString = str.length();

		ArrayList<String> arrayRecursion = new ArrayList<>();
		arrayRecursion = PermutationUsingRecursion.permute(str, 0, lengthOfString - 1);

		// Sorting Array Obtained Using Recursion

		Collections.sort(arrayRecursion);
		System.out.print("Permutation using Recursion: ");
		for (int i = 0; i < arrayRecursion.size(); i++)
			System.out.print(arrayRecursion.get(i) + " ");

		System.out.println();

		ArrayList<String> arrayIteration = new ArrayList<>();
		arrayIteration = PermutationUsingIteration.permute(str);

		// Sorting Array Obtained Using Iteration

		Collections.sort(arrayIteration);
		System.out.print("Permutation using Iteration: ");
		for (int i = 0; i < arrayIteration.size(); i++)
			System.out.print(arrayIteration.get(i) + " ");

		System.out.println();

		// Comparing Resulting Array obtained by Recursion and Iteration

		if (arrayRecursion.equals(arrayIteration))
			System.out.println("Values in both the arraylist is equal.");
		else
			System.out.println("Values in both the arraylist is not equal.");
	}
}

package com.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * * Java Program to show how to add comma to numbers in Java e.g. integers. *
 * Normally numbers which is used to represent monetary value e.g. * price,
 * amount etc require * commas to be added to improve readability and follow
 * conventions. * This article shows step by * step example to add commas on
 * numbers. * * @author Javin Paul
 */
public class NumberFormatExample {
	public static void main(String args[]) {
		// Example 1 - by using NumberFormat class
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		// this will also round numbers, 3 // decimal places
		double[] numbers = { 1.16763, 443330, 3, 517827.17 };
		System.out.println("adding commas to number in Java using NumberFormat class");
		for (double d : numbers) {
			System.out.println(myFormat.format(d));
		} // Example 2 - By using DecimalFormat class 
		DecimalFormat decimalFormat = new DecimalFormat("#.##"); decimalFormat.setGroupingUsed(true);
			decimalFormat.setGroupingSize(3);
			System.out.println("adding commas to number in Java using DecimalFormat class"); 
			for (double d : numbers) {
			System.out.println(decimalFormat.format(d));
			} 
			} 
	

}

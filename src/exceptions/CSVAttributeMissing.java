package exceptions;

//---------------------------------------------------------------
//Assignment 3
//Part: 1
//COMP 249
//Written by: Liam Daigle (40207583), Gabriel D'Alesio (40208808)
//Due: Friday, March 25th 2022
//---------------------------------------------------------------

/**
 * Class that contains methods for CSVAttributeMissing exception handler
 * 
 * @author Liam Daigle, Gabriel D'Alesio
 * @version 1.0
 */

public class CSVAttributeMissing extends Exception {
	
	private String message;
	/**
	 * Default constructor that prints default error message
	 */
	public CSVAttributeMissing() {
		
		message = "Error: Input row cannot be parsed due to missing information";
	}
	/**
	 * Parameterized constructor that receives custom error message
	 * 
	 * @param msg String that representings custom error message
	 */
	public CSVAttributeMissing(String msg) {
		
		message = msg;
	}
	/**
	 * Method that returns error message
	 */
	public String getMessage() {
		
		return message;
	}

}

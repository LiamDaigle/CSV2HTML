package exceptions;

//---------------------------------------------------------------
//Assignment 3
//Part: 1
//COMP 249
//Written by: Liam Daigle (40207583), Gabriel D'Alesio (40208808)
//Due: Friday, March 25th 2022
//---------------------------------------------------------------

/**
* Class that contains methods for CSVDataMissing exception handler
* 
* @author Liam Daigle, Gabriel D'Alesio
* @version 1.0
*/

public class CSVDataMissing extends Exception {

	private String message;
	/**
	 * Default constructor that prints default error message
	 */
	public CSVDataMissing() {
		
		message = "Error: Input row cannot be parsed due to missing information"; //Add default message for default constructor
	}
	/**
	 * Parameterized constructor that receives custom error message
	 * 
	 * @param msg String that representings custom error message
	 */
	public CSVDataMissing(String msg) {
		
		message = msg;
	}
	/**
	 * Method that returns error message
	 */
	public String getMessage() {
		return message;
	}
}

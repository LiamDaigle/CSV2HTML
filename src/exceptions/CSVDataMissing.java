package exceptions;

public class CSVDataMissing extends Exception {

	private String message;
	
	public CSVDataMissing() {
		
		message = "Error: Input row cannot be parsed due to missing information"; //Add default message for default constructor
	}
	
	public CSVDataMissing(String msg) {
		
		message = msg;
	}
	
	public String getMessage() {
		return message;
	}
}

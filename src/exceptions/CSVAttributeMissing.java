package exceptions;

public class CSVAttributeMissing extends Exception {
	
	private String message;
	
	public CSVAttributeMissing() {
		
		message = "Error: Input row cannot be parsed due to missing information";
	}
	
	public CSVAttributeMissing(String msg) {
		
		message = msg;
	}
	
	public String getMessage() {
		
		return message;
	}

}

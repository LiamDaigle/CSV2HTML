package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import exceptions.CSVAttributeMissing;
import exceptions.CSVDataMissing;

public class Main {

	public static void main(String[] args) {
		
		
		
	}
	
	public static void convertCSVtoHTML(/*Need to decide which parameters are necessary*/) throws CSVAttributeMissing, CSVDataMissing {
		
		
		PrintWriter output;
		try {
			output = new PrintWriter(file);
			output.println("<!DOCTYPE HTML>");
			output.println("<html>");
			output.println("<head><title> HTML Tables </title></head>");
			output.println("<body>");
			output.println("<table bgcolor = \"black\" align = \"center\" width = \"700\">");
			
			for(int i = 0; i < array.length; i++) {
				output.println("<tr bgcolor = \"grey\" align = \"center\" width = \"200\">");
				for(int j = 0; j < array[i].length; j++) {
					
					output.println("<td>" + array[i][j] + "</td>");
				}
				output.println("</tr>");
			}
			
			output.println("</table>");
			output.println("</body>");
			output.println("</html>");
			
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

}

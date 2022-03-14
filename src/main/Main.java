package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import exceptions.CSVAttributeMissing;
import exceptions.CSVDataMissing;

public class Main {

	public static void main(String[] args) {
		
		
		
	}
	
	public static void convertCSVtoHTML(File file) throws CSVAttributeMissing, CSVDataMissing {
		
		Scanner sc = null;
		int count = 0;
		
		try {
			sc = new Scanner(new FileInputStream(file));
		}
		catch(Exception e) {
			System.out.print("File not found. Terminating program.");
			System.exit(0);
		}
		
		while(sc.hasNextLine()) {
			count++;
		}
		
		String[][] array = new String[count][4];
		
		array[0][0] = sc.nextLine();
		
		sc.useDelimiter(",");
		for(int i  = 1; i < count; i++) {
				for(int j  = 0; j < 4; j++) {
					if(sc.next() == "Note:") {
						array[count][0] = sc.nextLine(); 
					}
					array[i][j] = sc.next();
			}	
		}
		
		try {
			
		}
		
		
		
		
		
		
		
		
		
		
			
		PrintWriter output = null;
				
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

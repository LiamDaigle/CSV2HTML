package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import exceptions.CSVAttributeMissing;
import exceptions.CSVDataMissing;

//---------------------------------------------------------------
//Assignment 3
//Part: 1
//COMP 249
//Written by: Liam Daigle (40207583), Gabriel D'Alesio (40208808)
//Due: Friday, March 25th 2022
//---------------------------------------------------------------

/**
 * Class that will receive a CSV file, read it, and then display it in a table on an automatically generated webpage.
 * 
 * @author Liam Daigle, Gabriel D'Alesio
 * @version 1.0
 */

public class Main {
	static String filePath = "C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\csv-files";
	public static void main(String[] args) {
		
		
		File covidStats = new File(filePath + "\\covidStatistics-CSV format.csv");
		File doctorList = new File(filePath + "\\doctorList-CSV format.csv");
		
		convertCSVtoHTML(covidStats);
		convertCSVtoHTML(doctorList);
		
	}
	@SuppressWarnings("resource")
	/**
	 * Method that converts CSV file to HTML document.
	 * 
	 * @param f CSV file object
	 * @throws CSVAttributeMissing Exception handler that verifies that all the needed attributes are present, and displays a message in an exception log if one is missing.
	 * @throws CSVDataMissing Exception handler that verifies that all the needed data points are present, and displays a message in an exception log if one is missing.
	 */
	public static void convertCSVtoHTML(File f){
		
		File htmlFile;
		Scanner sc = null;
		PrintWriter pw = null;
		int row = 1;
		
		try {
			
			htmlFile = new File("C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\main\\" + f.getName().replace(".csv", ".html"));
			sc = new Scanner(new FileInputStream(f)).useDelimiter(",");
			pw = new PrintWriter(htmlFile);
			// Variable that will hold the line for the title
			String title = sc.nextLine();
			boolean tableClosed = false;
			// Formating for the HTML document
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html>");
			pw.println("<head><title> HTML Tables </title></head>");
			pw.println("<body>");
			pw.println("<table width = \"500 \">");
			pw.println("<tr width = 500><caption>" + title.replace(",", "") +"</caption></tr>");
			
			
			int count = 0;
			// Message informing the user which file is being read and the title of the HTML file that is being written to
			System.out.println("Reading from " + f.getName() + "...");
			System.out.println("Writing to " + htmlFile.getName() +"...");
			// While loop that verifies that the file has a next line using the Scanner object
			while(sc.hasNextLine()) {
				String token;

				if(count == 3)
					token = sc.nextLine();
				else
					token = sc.next();
				
				if(count == 4) {
					pw.println("</tr>");
					row++;
					count = 0;
				}
				if(count == 0)
					pw.println("<tr align = \"center\">");
				// if statement that verifies that the attributes have no missing variables, and if they do, throws a CSVAttributeMissing error
				if(row == 1) {
					if(token.equals(" ") || token.equals("")) {
						throw new CSVAttributeMissing("ERROR: In file " + f.getName() + ". Missing attribute. File not converted to HTML.");
					}
					else
						pw.println("<th>" + token.replace(",", "") + "</th>");
				}
				// if statement that verifies that the data values have no missing variables, and if they do, throws a CSVDataMissing error
				if(row != 1) {
					if(token.equals(" ") || token.equals("")) {
						throw new CSVDataMissing("ERROR: In file " + f.getName() + ". Line " + row + " is missing a value. File not converted to HTML.");
					}
					else if(token.length() > 5 && token.substring(0,5).equals("Note:")) {
						pw.println("</table>");
						pw.println("<span>" + token + " </span>");
						tableClosed = true;
						break;
					}
					else
						pw.println("<td>" + token.replace(",", "") + "</td>");
				}
				count++;
			}
			
			if(tableClosed == false)
				pw.println("</table>");
			pw.println("</body>");
			pw.println("</html>");
		}
		// Instructions of FileNotFound exception
		catch(FileNotFoundException e) {
			System.out.println("Could not open input file " + f.getName() + " for reading.");
			System.out.println("Please check that the file exists and is readable. This program will terminate after closing any opened files.");
			System.exit(0);
		}
		// Instructions of CSVAttributeMissing exception
		catch(CSVAttributeMissing a) {
			
			System.out.println("Catch");
			try {
				pw = new PrintWriter(new FileOutputStream(filePath + "Exceptions.log"), true);
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found. Terminating program");
				System.exit(0);
			}
			
			pw.println(a.getMessage());
			// Deleting the file
			f.delete();
			// Closing the PrintWriter object for Exceptions.log
			pw.close();
		}
		// Instructions of CSVDataMissing exception
		catch(CSVDataMissing d) {
			
			try {
				pw = new PrintWriter(new FileOutputStream(filePath + "Exceptions.log"), true);
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found. Terminating program");
				System.exit(0);
			}
			
			pw.println(d.getMessage());
			// Deleting the file
			f.delete();
			// Closing the PrintWriter object for Exceptions.log
			pw.close();
		}
		// Closing the Scanner object
		sc.close();
		// Closing the PrintWriter object for HTML file
		pw.close();
		
	 }  

}

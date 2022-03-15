package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import exceptions.CSVAttributeMissing;
import exceptions.CSVDataMissing;

public class Main {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\csv-files";
		
		File covidStats = new File(filePath + "\\covidStatistics-CSV format.csv");
		File doctorList = new File(filePath + "\\doctorList-CSV format.csv");
		
		try {
			convertCSVtoHTML(covidStats);
			convertCSVtoHTML(doctorList);
		} catch (CSVAttributeMissing e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSVDataMissing e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public static void convertCSVtoHTML(File f) throws CSVAttributeMissing, CSVDataMissing {
		
		BufferedReader br = null;
		Scanner sc = null;
		int count = 0;
		
		try {
			br = new BufferedReader(new FileReader(f));
			sc = new Scanner(new FileInputStream(f));
			while(br.readLine() != null) {
				count++;
				}
			br.close();
			}
		catch(Exception e) {
			System.out.print("File not found. Terminating program.");
			System.exit(0);
		}
		
		
		
		
		
		String[][] array = new String[count][4];
		
		array[0][0] = sc.nextLine();
		
		sc.useDelimiter(",");
		for(int i  = 1; i < count; i++) {
				for(int j  = 0; j < 4; j++) {
					if(sc.next() == "Note:") {
						array[count - 1][0] = sc.nextLine();
					}
					array[i][j] = sc.next();
			}	
		}
		try {
			for(int k = 0; k < array.length; k++) {
				for(int l = 0; l < array[k].length; l++) {
					if(array[1][l] == " ") {
						throw new CSVAttributeMissing("ERROR: In file " + f.getName() + ". Missing attribute. File not converted to HTML.");
					}
					if(k != 1 && array[k][l] == " ") {
						throw new CSVDataMissing("ERROR: In file " + f.getName() + ". Line " + k + " is missing a value. File not converted to HTML."
								+ " Missing data in " + (String) array[1][k] + ".");
					}
				}
			}
		}
		catch(CSVAttributeMissing a) {
			System.out.print(a.getMessage());
		}
		catch(CSVDataMissing d) {
			System.out.print(d.getMessage());
		}
		
		PrintWriter output;
				
		try {
			output = new PrintWriter("C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\generated-html-files\\" + f.getName() + ".html");
			output.println("<!DOCTYPE HTML>");
			output.println("<html>");
			output.println("<head><title> HTML Tables </title></head>");
			output.println("<body>");
			output.println("<table bgcolor = \"black\" align = \"center\" width = \"700\">");
			
			boolean titleWritten = false;
			String noteTest = array[array.length - 1][0].substring(0,6);
			boolean hasNote = noteTest.equals("Note:");
			
			for(int i = 0; i < array.length; i++) {
				
				output.println("<tr bgcolor = \"grey\" align = \"center\" width = \"200\">");
				
				for(int j = 0; j < array[i].length; j++) {
					
					if(i == 0 && titleWritten == false) {
						output.println("<caption>" + array[i][j] + "</caption>");
						titleWritten = true;
					}
					
					else if(i == 0 || array[i][j].equals(null))
						continue;
					
					else if(i == 1) {
						output.println("<th>" + array[i][j] + "</th>");
					}
					
					else if(i == array.length && hasNote == true)
						output.println("<span>" + array[i][j] + "</span>");
					
					else {
						output.println("<td>" + array[i][j] + "</td>");
					}
					
				}
				output.println("</tr>");
			}
			
			output.println("</table>");
			output.println("</body>");
			output.println("</html>");
			
			output.close();
			
			System.out.println("Program gets to the end without any errors!");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

}

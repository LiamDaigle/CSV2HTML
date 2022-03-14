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
		
	}
	
	public static void convertCSVtoHTML(File f) throws CSVAttributeMissing, CSVDataMissing {
		
		BufferedReader br = null;
		Scanner sc = null;
		int count = 0;
		
		try {
			br = new BufferedReader(new FileReader(f));
			sc = new Scanner(new FileInputStream(f));
			}
		catch(Exception e) {
			System.out.print("File not found. Terminating program.");
			System.exit(0);
		}
		
		while(br.readLine() != null) {
			count++;
		}
		
		br.close();
		
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
			for(int k = 0; k < array.length; k++) {
				for(int l = 0; l < array[k].length; l++) {
					if(array[1][l] == " ") {
						throw new CSVAttributeMissing("Attribute missing.");
					}
					if(k != 1 && array[k][l] == " ") {
						throw new CSVDataMissing("Data Field missing.");
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

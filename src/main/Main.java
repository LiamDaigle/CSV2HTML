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

public class Main {
	static String filePath = "C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\csv-files";
	public static void main(String[] args) {
		
		
		File covidStats = new File(filePath + "\\covidStatistics-CSV format.csv");
		File doctorList = new File(filePath + "\\doctorList-CSV format.csv");
		
		convertCSVtoHTML(covidStats);
		convertCSVtoHTML(doctorList);
		
	}
	@SuppressWarnings("resource")
	public static void convertCSVtoHTML(File f){
		
		File htmlFile;
		Scanner sc = null;
		PrintWriter pw = null;
		int row = 1;
		
		try {
			
			htmlFile = new File("C:\\Users\\liamd\\Documents\\GitHub\\CSV2HTML\\src\\main\\" + f.getName().replace(".csv", ".html"));
			sc = new Scanner(new FileInputStream(f)).useDelimiter(",");
			pw = new PrintWriter(htmlFile);
			
			String title = sc.nextLine();
			boolean tableClosed = false;
			
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html>");
			pw.println("<head><title> HTML Tables </title></head>");
			pw.println("<body>");
			pw.println("<table width = \"500 \">");
			pw.println("<tr width = 500><caption>" + title.replace(",", "") +"</caption></tr>");
			
			
			int count = 0;
			System.out.println("Reading from " + f.getName() + "...");
			System.out.println("Writing to " + htmlFile.getName() +"...");
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
				if(row == 1) {
					if(token.equals(" ") || token.equals("")) {
						throw new CSVAttributeMissing("ERROR: In file " + f.getName() + ". Missing attribute. File not converted to HTML.");
					}
					else
						pw.println("<th>" + token.replace(",", "") + "</th>");
				}
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
		catch(FileNotFoundException e) {
			System.out.print("File not found. Terminating program.");
			System.exit(0);
		}
		catch(CSVAttributeMissing a) {
			
			System.out.println("Catch");
			try {
				pw = new PrintWriter(new FileOutputStream(filePath + "Exceptions.log"));
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found. Terminating program");
				System.exit(0);
			}
			
			pw.println(a.getMessage());
			pw.close();
		}
		catch(CSVDataMissing d) {
			
			try {
				pw = new PrintWriter(new FileOutputStream(filePath + "Exceptions.log"));
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found. Terminating program");
				System.exit(0);
			}
			
			pw.println(d.getMessage());
			pw.close();
		}
	
		sc.close();
		pw.close();
		
		
		
		
	
		
		
	}

}

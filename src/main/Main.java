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

	public static void main(String[] args) {
		String filePath = "C:\\Users\\indig\\OneDrive\\Documents\\GitHub\\CSV2HTML\\src\\csv-files";
		
		File covidStats = new File(filePath + "\\covidStatistics-CSV format.csv");
		File doctorList = new File(filePath + "\\doctorList-CSV format.csv");
		
		try {
			convertCSVtoHTML(covidStats);
			convertCSVtoHTML(doctorList);
		} catch (CSVAttributeMissing e) {
			e.printStackTrace();
		} catch (CSVDataMissing e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("resource")
	public static void convertCSVtoHTML(File f) throws CSVAttributeMissing, CSVDataMissing {
		
		Scanner sc = null;
		PrintWriter pw = null;
		int row = 1;
		
		try {
			sc = new Scanner(new FileInputStream(f)).useDelimiter(",");
			
			System.out.println(sc.nextLine());
			
			int count = 0;
			while(sc.hasNextLine()) {
				String token = sc.next();
				System.out.print(token + " ");
				count++;
				if(count == 4) {
					row++;
					count = 0;
				}
				if(row == 1) {
					if(token == " ")
						throw new CSVAttributeMissing("ERROR: In file " + f.getName() + ". Missing attribute. File not converted to HTML.");
				}
				if(row != 1 && token == " ")
					throw new CSVDataMissing("ERROR: In file " + f.getName() + ". Line " + row + " is missing a value. File not converted to HTML.");
			
			}
		}
		catch(FileNotFoundException e) {
			System.out.print("File not found. Terminating program.");
			System.exit(0);
		}
		catch(CSVAttributeMissing a) {
			
			try {
				pw = new PrintWriter(new FileOutputStream("Exceptions.log"));
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
				pw = new PrintWriter(new FileOutputStream("Exceptions.log"));
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found. Terminating program");
				System.exit(0);
			}
			
			pw.println(d.getMessage());
			pw.close();
		}
	
		sc.close();
		
		
		
		
		
	
		
		
	}

}

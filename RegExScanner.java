import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class RegExScanner {
	
	//private ArrayList<String> theList;

	public ArrayList<String> scanRegs(File file){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//ArrayList<String> list = new ArrayList<String>();
			
			while ((strLine = br.readLine()) != null) {
				list.add(strLine);
				
			}
			
			in.close();
			
			
		}
		catch(Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
		finally {
			System.out.println("Scanning...");
		}
		return list;
	}
	
	public scanRegLine(ArrayList<String> regList){
		
	}
	
}

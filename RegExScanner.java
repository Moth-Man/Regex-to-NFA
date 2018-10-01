import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class RegExScanner {
	
	//Takes in file of regex and saves each line to an arrayList of strings.
	public ArrayList<String> scanRegexFile(File file){
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
}
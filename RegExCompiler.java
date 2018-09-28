import java.util.ArrayList;

public class RegExCompiler {

	public static void main(String args[]){
		RegExScanner scan = new RegExScanner();
		ArrayList<String> regEx = scan.scanRegs('regex.txt');
	}
}

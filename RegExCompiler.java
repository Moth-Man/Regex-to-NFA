import java.util.ArrayList;
import java.io.File;

public class RegExCompiler {

	public static void main(String args[]){
		if(args.length > 0){
			File file =  new File(args[0]);
			RegExScanner scan = new RegExScanner();
			ArrayList<String> regEx = scan.scanRegexFile(file);
		}
	}
}

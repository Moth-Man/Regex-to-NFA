import java.util.ArrayList;
import java.io.File;

public class RegExCompiler {

	/*
	public static void main(String args[]){
		if(args.length > 0){
			File file =  new File(args[0]);
			RegExScanner scan = new RegExScanner();
			ArrayList<String> regEx = scan.scanRegexFile(file);
		}
	}
	
	*/
	
	//Test compiler
	public static void main(String args[]) {
		char[] ch = new char[3];
		NFA nfa = new NFA("Q1", "Q2", 3, ch);
		nfa.stateGenerator(nfa);
	}
}

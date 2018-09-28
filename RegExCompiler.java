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
		NFA nfa = new NFA(5, ch);
		nfa.stateGenerator(nfa);
		nfa.createCharNFA('a');
	}
}

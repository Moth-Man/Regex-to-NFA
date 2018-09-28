import java.util.ArrayList;
import java.io.File;

public class RegExCompiler {

	/*
	public static void main(String args[]){
		if(args.length > 0){
			File file =  new File(args[0]);
			char[] ch = new char[0];
			//Dummy NFA object to activate analyzeNFA method
			NFA nfa = new NFA(0, ch);
			RegExScanner scan = new RegExScanner();
			ArrayList<String> regExList = scan.scanRegexFile(file);
			for(String regex: regExList){
				nfa.analyzeNFA(regex);
			}
		}
		else {
		System.out.print("ERRROR: No regex file recovered.");
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
//}

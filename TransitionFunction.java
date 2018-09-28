import java.util.ArrayList;

public class TransitionFunction {

	private NFA nfa;
	
	public TransitionFunction(NFA nfa){
		this.nfa = nfa;
	}
	
	public ArrayList<String> generateTransitionFunction(NFA nfa){
		char[] links = nfa.getLinks();
		String[] states = nfa.getStates();
		
	}
}

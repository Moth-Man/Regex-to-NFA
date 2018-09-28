

public class NFA {

	private String startState;
	private String endState;
	private int numStates;
	private char[] links;
	private String[] states;
	private TransitionFunction tranFunc;
	private NFAStack stack = new NFAStack(1000);
	
	public NFA(String startState, String endState, int numStates, char[] link){
		this.startState = startState;
		this.endState = endState;
		this.numStates = numStates;
		this.links = links;
		this.states = states;
		this.stack = stack;
		this.tranFunc = tranFunc;
		
	}
	
	//Setters & Getters
	public String getStartState() {
		return startState;
	}

	public void setStartState(String startState) {
		this.startState = startState;
	}

	public String getEndState() {
		return endState;
	}

	public void setEndState(String endState) {
		this.endState = endState;
	}

	public int getNumStates() {
		return numStates;
	}

	public void setNumStates(int numStates) {
		this.numStates = numStates;
	}

	public TransitionFunction getTranFunc() {
		return tranFunc;
	}

	public void setTranFunc(TransitionFunction tranFunc) {
		this.tranFunc = tranFunc;
	}
	
	public String[] stateGenerator(NFA nfa){
		int stNum = nfa.getNumStates();
		for(int i = 0; i < stNum; i++){
			states[i] = "q" + i;
		}
		return states;
	}
	//Creates NFA from single element of ArrayList of regexes
	public void analyzeNFA(String regex){
		int length = regex.length();
		for(int i = 0; i < length; i++){
			char c = regex.charAt(i);
			if(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e'){
				NFA nFAc = createCharNFA(c);
				stack.push(nFAc);
			}
			else if(c == '&'){
				NFA nFA2 = stack.pop();
				NFA nFA1 = stack.pop();
				stack.push(createConcatNFA(nFA2,nFA1));
			}
			else if(c == '|'){
				NFA nFA2 = stack.pop();
				NFA nFA1 = stack.pop();
				stack.push(createConcatNFA(nFA1, nFA2));
			}
			else if(c == '*'){
				NFA nFA = stack.pop();
				stack.push(createKleeneNFA(nFA));
			}
		}
	}
	
	public NFA createCharNFA(char c){
		char[] ch = new char[1];
		ch[0] = c;
		NFA charNFA = new NFA("q1", "q2", 2, ch);
		return charNFA;
	}
	
	public NFA createUnionNFA(NFA nFA1, NFA nFA2){
		NFA unionNFA = new NFA();
	}
	
	public NFA createConcatNFA(NFA nFA1, NFA nFA2){
		
	}
	
	public NFA createKleeneNFA(NFA nFA){
		
	}
}

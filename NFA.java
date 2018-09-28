

public class NFA {

	private String startState;
	private String endState;
	private int numStates;
	private char link;
	private TransitionFunction tranFunc;
	private NFAStack stack;
	
	public NFA(String startState, String endState, int numStates, char link){
		this.startState = startState;
		this.endState = endState;
		this.numStates = numStates;
		this.link = link;
		//this.tranFunc = tranFunc;
		
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
				nFA2 = pop();
				nFA1 = pop();
				createConcatNFA(nFA2,nFA1);
				push()
			}
		}
	}
	
	public NFA createCharNFA(char c){
		NFA charNFA = new NFA("q1", "q2", 2, c);
		return charNFA;
	}
	
	public void createUnionNFA(){
		
	}
	
	public NFA createConcatNFA(NFA nFA1, NFA nFA2){
		concatNFA = new NFA();
	}
	
	public void createKleeneNFA(){
		
	}
}
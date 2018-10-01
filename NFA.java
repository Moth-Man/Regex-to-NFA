import org.apache.commons.lang3.ArrayUtils;

public class NFA {

	private int startState;
	private int endState;
	private int numStates;
	private char[] links;
	private int[] states = new int[numStates];
	private TransitionFunction tranFunc;
	public NFAStack stack = new NFAStack(1000);
	
	public NFA(int numStates, char[] links){
		this.numStates = numStates;
		this.links = links;
		
		
	}
	
	//Setters & Getters
	public int getStartState() {
		return startState;
	}

	public void setStartState(int startState) {
		this.startState = startState;
	}

	public int getEndState() {
		return endState;
	}

	public void setEndState(int endState) {
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
	
	public int[] getStates() {
		return states;
	}
	
	public void setStates(int[] states){
		this.states = states;
	}
	
	public char[] getLinks() {
		return links;
	}
	
	public void setLinks(char[] links){
		this.links = links;
	}
	
	//stateGenerator : Generates an array of integers represeting the state number.
	//The true state number corresponding to an NFA is really the state number minus 1.
	public int[] stateGenerator(NFA nfa){
		int stNum = nfa.getNumStates();
		int[] st8 = new int[stNum];
		nfa.setStates(st8);
		for(int i = 0; i < stNum; i++){
			st8[i] = i;
		}
		return states;
	}
	
	//analyzeNFA : Creates NFA from single element of ArrayList of regexes
	public void analyzeNFA(String regex){
		int length = regex.length();
		for(int i = 0; i < length; i++){
			char c = regex.charAt(i);
			if(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'E'){
				NFA nFAc = createCharNFA(c);
				stack.push(nFAc);
			}
			else if(c == '&'){
				NFA nFA2 = stack.pop();
				NFA nFA1 = stack.pop();
				stack.push(createConcatNFA(nFA1,nFA2));
			}
			else if(c == '|'){
				NFA nFA2 = stack.pop();
				NFA nFA1 = stack.pop();
				stack.push(createUnionNFA(nFA1, nFA2));
			}
			else if(c == '*'){
				NFA nFA = stack.pop();
				stack.push(createKleeneNFA(nFA));
			}
			else{
				System.out.println("ERROR: Character of regex not recognized in Sigma-Alphabet.");
			}
		}
		NFA finalNFA = stack.pop();
		System.out.println(regex);
		tranFunc = finalNFA.getTranFunc();
		tranFunc.generateTransitionFunction();
	}
	
	//createCharNFA : Takes in a char and creates a start and end state with a link
	//of that char.
	public NFA createCharNFA(char c){
		char[] ch = new char[1];
		ch[0] = c;
		NFA charNFA = new NFA(2, ch);
		int[] st8 = charNFA.stateGenerator(charNFA);
		
		charNFA.setStartState(st8[0]);
		charNFA.setEndState(st8[st8.length-1]);
		
		TransitionFunction tf = new TransitionFunction(charNFA.getNumStates());
		tf.addEdge(st8[0], st8[st8.length-1], c);
		charNFA.setStartState(0);
		charNFA.setEndState(charNFA.getNumStates()-1);
		charNFA.setTranFunc(tf);
	
		
		return charNFA;
	}
	
	//createUnionNFA : Takes in two NFA and creates a new start and end state that
	//links to the old start and end states of the previous NFAs and links them with
	//the empty string.
	public NFA createUnionNFA(NFA nFA1, NFA nFA2){
		int nFA3numStates = nFA1.getNumStates() + nFA2.getNumStates() + 2;
		char[] nFA3Links = (char[])ArrayUtils.addAll(nFA1.getLinks(), nFA2.getLinks());
		
		NFA nFA3 = new NFA(nFA3numStates, nFA3Links);
		TransitionFunction tf = new TransitionFunction(nFA3.getNumStates());
		
		tf.addEdge(0, 1, 'E');
		tf.addEdge(0, nFA1.getNumStates()+1, 'E');
		tf.addEdge(nFA1.getNumStates(), nFA3.getNumStates()-1, 'E');
		tf.addEdge(nFA3.getNumStates()-2, nFA3.getNumStates()-1, 'E');

		for(int i = 1; i < nFA1.getNumStates(); i++){
			if(i + 1 <= nFA1.getNumStates()){
				tf.addEdge(i, i+1, nFA3Links[i-1]);
			}
		
		}
		
		char[] nFA2Links = nFA2.getLinks();
		for(int j = nFA1.getNumStates()+1, k = 0; j < nFA3numStates && k < nFA2Links.length; j++, k++){
			if(j + 1 <= nFA3numStates){
				tf.addEdge(j, j+1, nFA2Links[k]);
			}
			
		}
		
		
		nFA3.setStartState(0);
		nFA3.setEndState(nFA3numStates-1);
		nFA3.setTranFunc(tf);
		return nFA3;
	}
	
	//createConcatNFA : Links two NFA together with the empty string.
	public NFA createConcatNFA(NFA nFA1, NFA nFA2){
		int nFA3numStates = nFA1.getNumStates() + nFA2.getNumStates();
		char[] emptySet = {'E'};
		//char[] temp = (char[])ArrayUtils.addAll(emptySet, nFA1.getLinks());
		//char[] temp2 = (char[])ArrayUtils.addAll(temp, nFA2.getLinks());
		//char[] nFA3Links = (char[])ArrayUtils.addAll(temp2, emptySet);

		char[] temp = (char[])ArrayUtils.addAll(nFA1.getLinks(), emptySet);
		char[] nFA3Links = (char[])ArrayUtils.addAll(temp, nFA2.getLinks());
		
		NFA nFA3 = new NFA(nFA3numStates, nFA3Links);
		int[] st8 = nFA3.stateGenerator(nFA3);
		nFA3.setStartState(st8[0]);
		nFA3.setEndState(st8[st8.length-1]);
		
		TransitionFunction tf = new TransitionFunction(nFA3.getNumStates());
		
		for(int i = 0; i < nFA1.getNumStates(); i++){
			if(i+1 <= nFA1.getNumStates()){
				tf.addEdge(i, i+1, nFA3Links[i]);
			}
		
		}
		
		for(int j = nFA1.getNumStates(); j < nFA3numStates; j++){
			if(j+1 < nFA3numStates){
				tf.addEdge(j, j+1, nFA3Links[j]);
			}
			
		}
		
		nFA3.setStartState(0);
		nFA3.setEndState(nFA3numStates-1);
		nFA3.setTranFunc(tf);
		return nFA3;
		
	}
	
	//createKleeneNFA : Creates a new NFA with new start state linked to old start state
	//with the empty string. Old end state links to new start state with empty string.
	//New start state becomes new end state as well.
	public NFA createKleeneNFA(NFA nfa){
		int start = nfa.getStartState();
		int end = nfa.getEndState();
		TransitionFunction tf = new TransitionFunction(nfa.getNumStates()+1);
		char[] links = nfa.getLinks();
		NFA kleeneNFA = new NFA(nfa.getNumStates()+1, links);
		

		tf.addEdge(start, start+1, 'E');
		tf.addEdge(end+1, start, 'E');
		
		
		for(int i = 1; i < kleeneNFA.getNumStates(); i++){
			if(i+1 <= kleeneNFA.getNumStates()-1)
				tf.addEdge(i, i+1, links[i-1]);
		}
		
		kleeneNFA.setStartState(start);
		kleeneNFA.setEndState(start);
		kleeneNFA.setTranFunc(tf);
		return kleeneNFA;
		
	}
}

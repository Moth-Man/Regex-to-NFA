import java.util.LinkedList;

public class TransitionFunction {

	
	static class Edge {
		int sourceState;
		int destState;
		char link;
		
		public Edge(int sourceState, int destState, char link){
			this.sourceState = sourceState;
			this.destState = destState;
			this.link = link;
		}

		public int getSourceState() {
			return sourceState;
		}

		public void setSourceState(int sourceState) {
			this.sourceState = sourceState;
		}

		public int getDestState() {
			return destState;
		}

		public void setDestState(int destState) {
			this.destState = destState;
		}

		public char getLink() {
			return link;
		}

		public void setLink(char link) {
			this.link = link;
		}
		
		
	}
	
	int stateNum;
	LinkedList<Edge> [] adjacencyList;
	
	public TransitionFunction(int stateNum)  {
		//stateNum a.k.a. vertices
		this.stateNum = stateNum;
		adjacencyList = new LinkedList[stateNum];
		
		for(int i = 0; i < stateNum; i++){
			adjacencyList[i] = new LinkedList<>();
		}
	}
	
	//Getters & Setters
	public int getStateNum() {
		return stateNum;
	}

	public void setStateNum(int stateNum) {
		this.stateNum = stateNum;
	}

	public LinkedList<Edge>[] getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(LinkedList<Edge>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	//Add edge to Transition Function i.e. Weighted Graph
	public void addEdge(int sourceState, int destState, char link){
		Edge edge = new Edge(sourceState, destState, link);
		adjacencyList[sourceState].addFirst(edge);
	}
	
	public void generateTransitionFunction(){
		for(int i = 0; i < stateNum; i++){
			LinkedList<Edge> list = adjacencyList[i];
			for(int j = 0; j < list.size(); j++){
				System.out.println("(q" + i + ", " + list.get(j).link + ") --> q" + list.get(j).destState );
			}
		}
	}
	
	public void adjustEdges(int newSrcStart, int newDestStart){
		for(int i = 0; i < stateNum; i++){
			LinkedList<Edge> thaList = adjacencyList[i];
			for(int j = 0; j < thaList.size(); j++){
				thaList.get(i).setSourceState(newSrcStart);
				newSrcStart++;
				thaList.get(j).setDestState(newDestStart);
				newDestStart++;
				
			}
		}
	}
	

	
}

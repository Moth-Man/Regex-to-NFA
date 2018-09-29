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
	}
	
	int stateNum;
	LinkedList<Edge> [] adjacencyList;
	
	public TransitionFunction(int stateNum)  {
		this.stateNum = stateNum;
		adjacencyList = new LinkedList[stateNum];
		
		for(int i = 0; i < stateNum; i++){
			adjacencyList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int sourceState, int destState, char link){
		Edge edge = new Edge(sourceState, destState, link);
		adjacencyList[sourceState].addFirst(edge);
	}
	
	public void generateTransitionFunction(){
		for(int i = 0; i < stateNum; i++){
			LinkedList<Edge> list = adjacencyList[i];
			for(int j = 0; j < list.size(); j++){
				System.out.println("(q" + i + ", " + list.get(j).link + ") --> " + list.get(j).destState );
			}
		}
	}
	

	
}

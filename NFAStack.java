
public class NFAStack {

	int maxSize;
	int top;
	NFA NFAarray[];
	
	public NFAStack(int n) {
		maxSize = n;
		NFAarray = new NFA[maxSize];
		top = 0;
		
	}
	
	public boolean empty(){
		
		if(top == 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void push(NFA nfa){
		if(top < maxSize){
			NFAarray[top] = nfa;
			top++;
		}
		else {
			System.out.println("Stack Overflow");
		}
	}
	
	public NFA pop(){
		if(!this.empty()){
			NFA temp = this.peek();
			NFAarray[top-1] = null;
			top--;
			return temp;
		}
		else {
			return null;
		}
		
	}
	
	public NFA peek(){
		if(top > 0){
		 return NFAarray[top-1];
		}
		else {
			return null;
		}
	}
	
}

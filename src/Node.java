public class Node {
	int line,column;
	Node next;
	Node prev;
	Node up;
	Node down;
	public Node(int n, int m, Node next, Node prev, Node up, Node down){
		this.line = n;
		this.column = m;
		this.next = next;
		this.prev = prev;
		this.up = up;
		this.down = down;
	}
	public int getLine(){
		return line;
	}
	public int getColumn(){
			return column;
	}
}

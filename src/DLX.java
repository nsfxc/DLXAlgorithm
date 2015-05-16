import java.util.*;

public class DLX{
	private Node[] column;
	private int m;
	private Set<Integer> select;
	private Set<Integer> covered;
	
	public DLX(int n, int m, boolean[][] g){
		this.m = m;
		Node head = new Node(-1,0,null,null,null,null);
		column = new Node[m];
		Node[] coltail = new Node[m];
		for(int i = 0; i < m; i++){
			column[i] = head;
			coltail[i] = column[i];
			Node temp = new Node(-1,i,head,null,null,null);
			head.next = temp;
			head = head.next;
		}
		head.next = column[0];
		for(int i = 0; i < n; i++){
			Node temp = makeRow(i,m,g[i]);
			if (temp!=null){
				int eh = temp.getColumn();
				coltail[eh].down = temp;
				coltail[eh].down.up = coltail[eh];
				coltail[eh] = coltail[eh].down;
				temp = temp.next;
				int et = temp.getColumn();
				while(et != eh){
					coltail[et].down = temp;
					coltail[et] = coltail[et].down;
					coltail[et].down.up = coltail[et];
					temp = temp.next;
					et = temp.getColumn();
				}
			}
		}
		for(int j = 0; j < m; j++){
			column[j].up = coltail[j];
			coltail[j].down = column[j];
		}
		select = new HashSet<Integer>();
		covered = new HashSet<Integer>();
	}
	
	public Node makeRow(int r,int n,boolean[] a){
		int i = 0;
		while (i<n && !a[i]){
			i++;
		}
		if(i != n){
			Node head = new Node(r,i,null,null,null,null);
			Node q = head;
			for(int j = i+1; j < n; j++){
				if (a[j]){
					Node temp = new Node(r,j,null,head,null,null);
					head.next = temp;
					head = temp;
				}
			}
			head.next = q;
			head = head.next;
			return head;
		}else{
			return null;
		}
	}
	
	public void cover(int c){
		covered.add(c);
		if (covered.size() == m){
			print();
		}
		column[c].prev.next = column[c].next;
		column[c].next.prev = column[c].prev;
		Node d = column[c].down;
		while(d != column[c]){
			Node r = d.next;
			while(r != d){
				r.up.down = r.down;
				r.down.up = r.up;
				r = r.next;
			}
			d = d.down;
		}
	}
	
	public void recover(int c){
		covered.remove(c);
		column[c].prev.next = column[c];
		column[c].next.prev = column[c];
		Node d = column[c].down;
		while(d != column[c]){
			Node r = d.next;
			while(r != d){
				r.up.down = r;
				r.down.up = r;
				r = r.next;
			}
			d = d.down;
		}
	}
	
	public void search(){
		for(int i = 0; i < m; i++){
			if (!covered.contains(i)){
				Node d = column[i].down;
				while( d != column[i]){
					select.add(d.getLine());
					Node r = d.next;
					cover(d.getColumn());
					while(r != d){
						cover(r.getColumn());
						r = r.next;
					}
					search();
					select.remove(d.getLine());
					r = r.next;
					recover(d.getColumn());
					while (r != d){
						recover(r.getColumn());
						r = r.next;
					}
					d = d.down;
				}
			}
		}
	}
	
	public void print(){
		Iterator<Integer> iter = select.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next());
			System.out.print(' ');
		}
		System.out.println();
	}
}

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Counter c=new Counter();
		Node[] nodes=new Node[n];
		for (int i=0; i<n; i++) {
			nodes[i]=new Node();
		}
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			c.inc(a);
			c.inc(b);
			link(nodes, a, b);
		}
		for (int k : c.keySet()) {
			int v=c.get(k);
			if (v>2) ng();
		}
		if (n==m) ng();
		check(nodes);
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static class Node {
		int prev;
		int next;
	}
	static void link(Node[] nodes, int a, int b) {
		Node na=nodes[a-1];
		Node nb=nodes[b-1];
		if (na.prev==0) na.prev=b;
		else if (na.next==0) na.next=b;
		else ng();
		if (nb.prev==0) nb.prev=a;
		else if (nb.next==0) nb.next=a;
		else ng();
	}
	static void check(Node[] nodes) {
		boolean[] flag=new boolean[nodes.length];
		for (int i=0; i<nodes.length; i++) {
			if (!flag[i]) checkNode(i, nodes[i], flag);
		}
	}
	static void checkNode(int i, Node node, boolean[] flag) {
		while (true) {
			flag[i]=true;
			if (node.prev>0 && node.next>0) {
				if (flag[node.prev-1] && flag[node.next-1]) ng();
			}
			if (node.prev>0 && !flag[node.prev-1]) {
				i=node.prev-1;
				continue;
			}
			if (node.next>0 && !flag[node.next-1]) {
				i=node.next-1;
				continue;
			}
			break;
		}
	}
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(int c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(int c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(int c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
4 2
1 3
2 3

4 3
1 4
2 4
3 4
*/
/*
3 3
1 2
1 3
2 3

4 3
1 2
1 3
2 3
*/

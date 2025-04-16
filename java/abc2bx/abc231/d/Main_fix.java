import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Node[] nodes=new Node[n];
		for (int i=0; i<n; i++) {
			nodes[i]=new Node();
		}
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			link(nodes, a, b);
		}
		check(nodes);
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static class Node {
		TreeSet<Integer> set=new TreeSet<>();
	}
	static void link(Node[] nodes, int a, int b) {
		Node na=nodes[a-1];
		na.set.add(b);
		if (na.set.size()>2) ng();
		Node nb=nodes[b-1];
		nb.set.add(a);
		if (nb.set.size()>2) ng();
	}
	static void check(Node[] nodes) {
		boolean[] flag=new boolean[nodes.length];
		for (int i=0; i<nodes.length; i++) {
			if (!flag[i]) checkNode(i, nodes, flag);
		}
	}
	static void checkNode(int i, Node[] nodes, boolean[] flag) {
		Deque<Que> que=new ArrayDeque<>();
		que.offer(new Que(i));
		while (que.size()>0) {
			Que b=que.poll();
			i=b.cur;
			Node node=nodes[i];
			flag[i]=true;
			boolean bHit=false;
			for (int next : node.set) {
				if (!flag[next-1]) {
					i=next-1;
					que.offer(new Que(i));
					bHit=true;
				}
			}
			if (!bHit && node.set.size()>1) ng();
		}
	}
	static class Que { //Que_cur20250416
		int cur;
		Que(int cur) {
			this.cur=cur;
		}
		@Override
		public String toString() {
			return "(" + cur + ")";
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

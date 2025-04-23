import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		MyDeque que=new MyDeque(n+2, n/2);
		que.addFirst(n);
		for (int i=n-1; i>=0; i--) {
			char ch=s.charAt(i);
			if (ch=='L') {
				que.addLast(i);
			} else {
				que.addFirst(i);
			}
		}
//		System.out.println(que);
		while (que.size()>0) {
			int x=que.removeFirst();
			System.out.print(x+" ");
		}
		System.out.println();
	}
	static class MyDeque { //MyDeque_int20250326
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new int[n+1];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}
		int size() {
			int t=tail;
			if (head>t) t += ary.length;
			return t-head;
		}
		void addFirst(int x) {
			head--;
			if (head < 0) head = ary.length - 1;
			ary[head]=x;
		}
		void addLast(int x) {
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		int removeFirst() {
			int x=ary[head];
			ary[head]=-1;
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		int get(int i) {
			i += head-1;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
}
/*
5
LRRLR

7
LLLLLLL
*/

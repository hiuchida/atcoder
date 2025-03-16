import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		MyDeque que = new MyDeque(q, 0);
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			int x;
			switch (c) {
			case 1:
				x = sc.nextInt();
				que.addLast(x);
//				System.out.println(que);
				break;
			case 2:
				x=que.removeFirst();
				System.out.println(x);
				break;
			case 3:
				que.sort();
//				System.out.println(que);
				break;
			}
		}
	}
	static class MyDeque {
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new int[n+1];
			head = i;
			tail = i;
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
		void sort() {
			Arrays.parallelSort(ary, head, tail);
//			Arrays.sort(ary, head, tail);
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
}
/*
8
1 4
1 3
1 2
1 1
3
2
1 0
2

9
1 5
1 5
1 3
2
3
2
1 6
3
2
*/

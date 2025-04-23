import java.util.*;
public class Main {
	public static void main(String[] args) {
		main1(args);
		main2(args);
	}
	public static void main1(String[] args) {
//		final int N=1000;
		final int N=4*100*1000*1000;
		long st1=System.currentTimeMillis();
		MyDeque que=new MyDeque(N, -1);
		for (int i=0; i<N; i++) {
			que.addFirst(i);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
			int x=que.get(i);
			if (x!=N-1-i) System.out.println(i+" "+x);
			ary[i]=x;
		}
		int idx=0;
		while (que.size()>0) {
			int x=que.removeFirst();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of check "+tm2);
	}
	public static void main2(String[] args) {
//		final int N=1000;
		final int N=4*100*1000*1000;
		long st1=System.currentTimeMillis();
		MyDeque que=new MyDeque(N, -1);
		for (int i=0; i<N; i++) {
			que.addLast(i);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
			int x=que.get(i);
			if (x!=i) System.out.println(i+" "+x);
			ary[i]=x;
		}
		int idx=0;
		while (que.size()>0) {
			int x=que.removeLast();
			if (ary[N-1-idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of check "+tm2);
	}
	static class MyDeque { //MyDeque_int20250327
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			n++;
			n += n%2==0 ? 1 : 0;
			if (i<0) i=n/2;
			ary = new int[n];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}
		int size() {
			int t=tail;
			if (head>t) t += ary.length;
			return t-head;
		}
		int get(int i) {
			i += head;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		void addFirst(int x) {
			head--;
			if (head < 0) head += ary.length;
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
		int removeLast() {
			tail--;
			if (tail < 0) tail += ary.length;
			int x=ary[tail];
			ary[tail]=-1;
			return x;
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
}
/*
end of check 2802
end of check 1535
*/

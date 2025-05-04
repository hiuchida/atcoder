import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		mainMyHeapque1(2);
		mainMyHeapque1(1);
		mainPriorityQueue1(1);
	}
	public static void main0(String[] args) {
		int n=10;
		MyHeapque que=new MyHeapque(n);
		for (int i=0; i<n; i++) {
			long v=(i*3)%n+1;
			que.add(v);
			System.out.println(que+" add "+i+": "+v);
		}
		for (int i=0; i<5; i++) {
			long v=que.remove();
			System.out.println(que+" del "+i+": "+v);
		}
		for (int i=0; i<2*n; i++) {
			int v=i+11;
			que.add(v);
			System.out.println(que+" add "+i+": "+v);
		}
		while (que.size>0) {
			long v=que.remove();
			System.out.println(que+" del "+": "+v);
		}
	}
	public static void mainMyHeapque1(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		MyHeapque que=new MyHeapque(N);
		mainMyHeapque1a(que, N);
		que=new MyHeapque();
		mainMyHeapque1a(que, N);
	}
	public static void mainMyHeapque1a(MyHeapque que, int N) {
		long st1=System.currentTimeMillis();
		for (int i=0; i<N; i++) {
			que.add((i*3)%N);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
			ary[i]=i;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.remove();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainMyHeapque1a "+tm2);
	}
	public static void mainPriorityQueue1(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		PriorityQueue<Long> que=new PriorityQueue<>(N);
		mainPriorityQueue1a(que, N);
		que=new PriorityQueue<>();
		mainPriorityQueue1a(que, N);
	}
	public static void mainPriorityQueue1a(PriorityQueue<Long> que, int N) {
		long st1=System.currentTimeMillis();
		for (int i=0; i<N; i++) {
			long v=(i*3)%N;
			que.add(v);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
			ary[i]=i;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.remove();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainPriorityQueue1a "+tm2);
	}
	static class MyHeapque { //MyHeapque_long20250504
		long[] ary;
		int size;
		MyHeapque() {
			this(100);
		}
		MyHeapque(int n) {
			this.ary=new long[n];
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(long i) {
			if (ary.length == size) ary = Arrays.copyOf(ary, size * 2);
			ary[size]=i;
			upheap(ary, size);
			size++;
		}
		long remove() {
			long x=ary[0];
			size--;
			ary[0]=ary[size];
			ary[size]=0;
			downheap(ary, size);
			return x;
		}
		private void upheap(long[] ary, int i) {
			long x=ary[i];
			int j=i;
			while (i>0) {
				j=(i-1)/2;
				if (ary[j]>x) {
					ary[i]=ary[j];
				} else {
					break;
				}
				i=j;
			}
			ary[i]=x;
		}
		private void downheap(long[] ary, int n) {
			int i=0;
			while (true) {
				long x=ary[i];
				int lt=i*2+1;
				int rt=i*2+2;
				if (lt>=n) break;
				if (rt<n && ary[lt]>ary[rt]) lt=rt;
				if (x<=ary[lt]) break;
				ary[i]=ary[lt];
				ary[lt]=x;
				i=lt;
			}
		}
		@Override
		public String toString() {
			return Arrays.toString(ary)+" "+size;
		}
	}
}
/*
end of mainMyHeapque1a 25705
end of mainMyHeapque1a 26451
end of mainMyHeapque1a 12681
end of mainMyHeapque1a 13167
end of mainPriorityQueue1a 54339
end of mainPriorityQueue1a 52348
*/

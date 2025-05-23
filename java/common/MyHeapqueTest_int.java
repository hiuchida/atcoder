import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		mainMyHeapque1(4);
		mainMyHeapque1(1);
		mainPriorityQueue1(1);
	}
	public static void main0(String[] args) {
		int n=10;
		MyHeapque que=new MyHeapque(n);
		for (int i=0; i<n; i++) {
			int v=(i*3)%n+1;
			que.add(v);
			System.out.println(que+" add "+i+": "+v);
		}
		for (int i=0; i<5; i++) {
			int v=que.remove();
			System.out.println(que+" del "+i+": "+v);
		}
		for (int i=0; i<2*n; i++) {
			int v=i+11;
			que.add(v);
			System.out.println(que+" add "+i+": "+v);
		}
		while (que.size>0) {
			int v=que.remove();
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
			int x=que.remove();
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
		PriorityQueue<Integer> que=new PriorityQueue<>(N);
		mainPriorityQueue1a(que, N);
		que=new PriorityQueue<>();
		mainPriorityQueue1a(que, N);
	}
	public static void mainPriorityQueue1a(PriorityQueue<Integer> que, int N) {
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
			int x=que.remove();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainPriorityQueue1a "+tm2);
	}
	static class MyHeapque { //MyHeapque_int20250504
		int[] ary;
		int size;
		MyHeapque() {
			this(100);
		}
		MyHeapque(int n) {
			this.ary=new int[n];
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(int i) {
			if (ary.length == size) ary = Arrays.copyOf(ary, size * 2);
			ary[size]=i;
			upheap(ary, size);
			size++;
		}
		int remove() {
			int x=ary[0];
			size--;
			ary[0]=ary[size];
			ary[size]=0;
			downheap(ary, size);
			return x;
		}
		private void upheap(int[] ary, int i) {
			int x=ary[i];
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
		private void downheap(int[] ary, int n) {
			int i=0;
			while (true) {
				int x=ary[i];
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
end of mainMyHeapque1a 46039
end of mainMyHeapque1a 51376
end of mainMyHeapque1a 10467
end of mainMyHeapque1a 10585
end of mainPriorityQueue1a 45594
end of mainPriorityQueue1a 46414
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
//		mainMyDeque0(args);
		mainMyDeque1(2);
		mainMyDeque2(2);
		mainMyDeque1(1);
		mainMyDeque2(1);
		mainArrayDeque1(1);
		mainArrayDeque2(1);
	}
	public static void mainMyDeque0(String[] args) {
		final int N=10;
		MyDeque que=new MyDeque(N, -1);
		int v=0;
		for (int i=1; i<=4; i++) {
			System.out.println(que);
			que.addFirst(v++);
		}
		for (int i=1; i<=7; i++) {
			System.out.println(que);
			que.addLast(v++);
		}
		System.out.println(que);
	}
	public static void mainMyDeque1(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		MyDeque que=new MyDeque(N, -1);
		mainMyDeque1a(que, N);
		que=new MyDeque();
		mainMyDeque1a(que, N);
	}
	public static void mainMyDeque1a(MyDeque que, int N) {
		long st1=System.currentTimeMillis();
		for (int i=0; i<N; i++) {
			que.addFirst(i);
		}
//		System.out.println(que);
		long[] ary=new long[N];
		for (int i=0; i<que.size(); i++) {
			long x=que.get(i);
			if (x!=N-1-i) System.out.println(i+" "+x);
			ary[i]=x;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.removeFirst();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainMyDeque1a "+tm2);
	}
	public static void mainMyDeque2(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		MyDeque que=new MyDeque(N, -1);
		mainMyDeque2a(que, N);
		que=new MyDeque();
		mainMyDeque2a(que, N);
	}
	public static void mainMyDeque2a(MyDeque que, int N) {
		long st1=System.currentTimeMillis();
		for (int i=0; i<N; i++) {
			que.addLast(i);
		}
//		System.out.println(que);
		long[] ary=new long[N];
		for (int i=0; i<que.size(); i++) {
			long x=que.get(i);
			if (x!=i) System.out.println(i+" "+x);
			ary[i]=x;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.removeLast();
			if (ary[N-1-idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainMyDeque2a "+tm2);
	}
	public static void mainArrayDeque1(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		ArrayDeque<Long> que=new ArrayDeque<>(N);
		mainArrayDeque1a(que, N);
		que=new ArrayDeque<>();
		mainArrayDeque1a(que, N);
	}
	public static void mainArrayDeque1a(ArrayDeque<Long> que, int N) {
		long st1=System.currentTimeMillis();
		for (long i=0; i<N; i++) {
			que.addFirst(i);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
//			int x=que.get(i);
//			if (x!=N-1-i) System.out.println(i+" "+x);
			ary[i]=N-1-i;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.removeFirst();
			if (ary[idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainArrayDeque1a "+tm2);
	}
	public static void mainArrayDeque2(int n) {
//		final int N=1000;
		final int N=n*100*1000*1000;
		ArrayDeque<Long> que=new ArrayDeque<>(N);
		mainArrayDeque2a(que, N);
		que=new ArrayDeque<>();
		mainArrayDeque2a(que, N);
	}
	public static void mainArrayDeque2a(ArrayDeque<Long> que, int N) {
		long st1=System.currentTimeMillis();
		for (long i=0; i<N; i++) {
			que.addLast(i);
		}
//		System.out.println(que);
		int[] ary=new int[N];
		for (int i=0; i<que.size(); i++) {
//			int x=que.get(i);
//			if (x!=i) System.out.println(i+" "+x);
			ary[i]=i;
		}
		int idx=0;
		while (que.size()>0) {
			long x=que.removeLast();
			if (ary[N-1-idx]!=x) System.out.println(idx+" "+ary[idx]+" "+x);
			idx++;
		}
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of mainArrayDeque2a "+tm2);
	}
	static class MyDeque { //MyDeque_long20250425
		long[] ary;
		int head;
		int tail;
		MyDeque() {
			this(100, -1);
		}
		MyDeque(int n, int i) {
			n++;
			n += n%2==0 ? 1 : 0;
			if (i<0) i=n/2;
			ary = new long[n];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}
		int size() {
			int t=tail;
			if (head>t) t += ary.length;
			return t-head;
		}
		long get(int i) {
			i += head;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		private void grow() {
			int s0=ary.length-1;
			long[] tmp=new long[s0 * 2 + 1];
			int s1=ary.length/2;
			int s2=ary.length-head;
			int s3=s1+s2;
			int s4=tail;
			Arrays.fill(tmp, 0, tmp.length, -1);
			System.arraycopy(ary, head, tmp, s1, s2);
			System.arraycopy(ary, 0, tmp, s3, s4);
			ary=tmp;
			head=s1;
			tail=head+s0;
		}
		void addFirst(long x) {
			if (size()==ary.length-1) grow();
			head--;
			if (head < 0) head += ary.length;
			ary[head]=x;
		}
		void addLast(long x) {
			if (size()==ary.length-1) grow();
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		long removeFirst() {
			if (size()==0) return -1;
			long x=ary[head];
			ary[head]=-1;
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		long removeLast() {
			if (size()==0) return -1;
			tail--;
			if (tail < 0) tail += ary.length;
			long x=ary[tail];
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
end of mainMyDeque1a 1575
end of mainMyDeque1a 2049
end of mainMyDeque2a 1067
end of mainMyDeque2a 1986
end of mainMyDeque1a 556
end of mainMyDeque1a 961
end of mainMyDeque2a 602
end of mainMyDeque2a 1143
end of mainArrayDeque1a 2348
end of mainArrayDeque1a 4272
end of mainArrayDeque2a 3496
end of mainArrayDeque2a 3307
*/

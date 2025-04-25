import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		main1(args);
		main2(args);
	}
	public static void main0(String[] args) {
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
	public static void main1(String[] args) {
//		final int N=1000;
		final int N=4*100*1000*1000;
		MyDeque que=new MyDeque(N, -1);
		main1a(que, N);
		que=new MyDeque();
		main1a(que, N);
	}
	public static void main1a(MyDeque que, int N) {
		long st1=System.currentTimeMillis();
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
		System.out.println("end of main1a "+tm2);
	}
	public static void main2(String[] args) {
//		final int N=1000;
		final int N=4*100*1000*1000;
		MyDeque que=new MyDeque(N, -1);
		main2a(que, N);
		que=new MyDeque();
		main2a(que, N);
	}
	public static void main2a(MyDeque que, int N) {
		long st1=System.currentTimeMillis();
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
		System.out.println("end of main2a "+tm2);
	}
	static class MyDeque { //MyDeque_int20250425
		int[] ary;
		int head;
		int tail;
		MyDeque() {
			this(100, -1);
		}
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
		private void grow() {
			int s0=ary.length-1;
			int[] tmp=new int[s0 * 2 + 1];
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
		void addFirst(int x) {
			if (size()==ary.length-1) grow();
			head--;
			if (head < 0) head += ary.length;
			ary[head]=x;
		}
		void addLast(int x) {
			if (size()==ary.length-1) grow();
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		int removeFirst() {
			if (size()==0) return -1;
			int x=ary[head];
			ary[head]=-1;
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		int removeLast() {
			if (size()==0) return -1;
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
end of main1a 2037
end of main1a 2793
end of main2a 1618
end of main2a 3158
*/

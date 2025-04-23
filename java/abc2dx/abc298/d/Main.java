import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	static final long M_10=modinv(10, M);
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
//		System.out.println(M_10);
		int q=sc.nextInt();
		long w=1;
		long val=1;
		MyDeque que=new MyDeque(q, -1);
		que.addFirst(1);
//		Deque<Integer> que=new ArrayDeque<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			switch (cmd) {
			case 1:
				int x=sc.nextInt();
				que.addLast(x);
				val=modmul(val, 10);
				val=modadd(val, x);
				w=modmul(w, 10);
//				System.out.println(x+" "+w+" "+val);
				break;
			case 2:
				int y=que.removeFirst();
				long v=modmul(y, w);
				val=modadd(val, -v);
				w=modmul(w, M_10);
//				System.out.println(y+" "+w+" "+val);
				break;
			case 3:
				long ans=val;
//				long ans=0;
//				long w=1;
//				for (int i=que.size()-1; i>=0; i--) {
//					long v=que.get(i);
//					v=modmul(v, w);
//					w=modmul(w, 10);
//					ans=modadd(ans, v);
//				}
				System.out.println(ans);
				break;
			}
		}
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250424
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250424
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250424
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
	//mod mでのvalの逆元val^{-1}
	static long modinv(long val, long m) { //ModFunc20250424
		long a = val;
		long b = m;
		long u = 1;
		long v = 0;
		while (b!=0) {
			long t = a / b;
			a -= t * b;
			long tmp = a;
			a = b;
			b = tmp;
			u -= t * v;
			tmp = u;
			u = v;
			v = tmp;
		}
		u %= m;
		if (u < 0) u += m;
		return u;
	}
	static class MyDeque {
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
3
3
1 2
3

3
1 5
2
3

11
1 9
1 9
1 8
1 2
1 4
1 4
1 3
1 5
1 3
2
3
*/

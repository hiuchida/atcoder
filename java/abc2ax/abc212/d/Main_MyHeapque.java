import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MyHeapque que=new MyHeapque();
		int q = sc.nextInt();
		long base=0;
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			long v;
			switch (c) {
			case 1:
				v = sc.nextInt();
				v-=base;
				que.add(v);
				break;
			case 2:
				v = sc.nextInt();
				base+=v;
				break;
			case 3:
				v = que.remove();
				long ans=v+base;
				System.out.println(ans);
				break;
			}
		}
	}
	static class MyHeapque { //MyHeapque_long20250428
		long[] ary;
		int cnt;
		int size;
		MyHeapque() {
			this(100);
		}
		MyHeapque(int n) {
			this.ary=new long[n];
			this.cnt=0;
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(long i) {
			if (ary.length == cnt) ary = Arrays.copyOf(ary, cnt * 2);
			ary[cnt]=i;
			upheap(ary, cnt);
			cnt++;
			size++;
		}
		long remove() {
			long x=ary[0];
			ary[0]=Integer.MAX_VALUE;
			downheap(ary, cnt);
			if (cnt%2==0) {
				while (cnt>=2 && ary[cnt-2]==Integer.MAX_VALUE && ary[cnt-1]==Integer.MAX_VALUE) {
					ary[cnt-2]=0;
					ary[cnt-1]=0;
					cnt-=2;
				}
			}
			size--;
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
			return Arrays.toString(ary)+" cnt=" + cnt + ", size=" + size;
		}
	}
}
/*
4 3
2 1
3 4
2 4

2 3
1 2
1 2
2 1
*/
/*
9 3
3 4
4 5
5 2

9 3
1 2
1 2
1 2
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		MyDeque que = new MyDeque(q, 0);
		MyHeapque pq=new MyHeapque(q);
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
				if (pq.size()>0) x=pq.remove();
				else x=que.removeFirst();
				System.out.println(x);
				break;
			case 3:
				while (que.size()>0) {
					x=que.removeFirst();
					pq.add(x);
				}
//				System.out.println(pq);
				break;
			}
		}
	}
	static class MyHeapque { //MyHeapque_int20250428
		int[] ary;
		int cnt;
		int size;
		MyHeapque(int n) {
			this.ary=new int[n];
			this.cnt=0;
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(int i) {
			if (ary.length == cnt) ary = Arrays.copyOf(ary, cnt * 2);
			ary[cnt]=i;
			upheap(ary, cnt);
			cnt++;
			size++;
		}
		int remove() {
			int x=ary[0];
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
			return Arrays.toString(ary)+" cnt=" + cnt + ", size=" + size;
		}
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

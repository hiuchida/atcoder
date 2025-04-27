import java.util.*;
public class Main {
	public static void main(String[] args) {
		int n=10;
		ary=new int[n];
		for (int i=0; i<n; i++) {
			int v=(i*3)%n+1;
			add(v);
			System.out.println(Arrays.toString(ary)+" "+i+": "+v);
		}
		for (int i=0; i<n; i++) {
			int v=remove();
			System.out.println(Arrays.toString(ary)+" "+i+": "+v);
		}
	}
	static int[] ary;
	static int cnt=0;
	static void add(int i) {
		ary[cnt++]=i;
		upheap(ary, cnt-1);
	}
	static int remove() {
		int x=ary[0];
		ary[0]=0;
		downheap(ary, cnt);
		return x;
	}
	static void upheap(int[] ary, int i) {
		int x=ary[i];
		int j=i;
		while (i>0) {
			j=(i-1)/2;
			if (ary[j]<x) {
				ary[i]=ary[j];
			} else {
				break;
			}
			i=j;
		}
		ary[i]=x;
	}
	static void downheap(int[] ary, int n) {
		int i=0;
		while (true) {
			int x=ary[i];
			int lt=i*2+1;
			int rt=i*2+2;
			if (lt>=n) break;
			if (rt<n && ary[lt]<ary[rt]) lt=rt;
			if (x>=ary[lt]) break;
			ary[i]=ary[lt];
			ary[lt]=x;
			i=lt;
		}
	}
}
/*



*/

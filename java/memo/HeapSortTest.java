import java.util.*;
public class Main {
	public static void main(String[] args) {
		int n=10;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=(i*3)%n+1;
		}
//		Arrays.sort(ary);
		System.out.println(Arrays.toString(ary));
		heapsort(ary);
		System.out.println();
	}
	static void heapsort(int[] ary) {
		for (int i=1; i<ary.length; i++) {
			upheap(ary, i);
			System.out.println(Arrays.toString(ary)+" "+i);
		}
		for (int i=ary.length-1; i>0; i--) {
			int x=ary[0];
			ary[0]=ary[i];
			ary[i]=x;
			downheap(ary, i);
			System.out.println(Arrays.toString(ary)+" "+i);
		}
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

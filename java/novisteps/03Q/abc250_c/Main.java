import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] ary=new int[n+1];
		int[] idx=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=i;
			idx[i]=i;
		}
//		System.out.println("ary:"+Arrays.toString(ary));
//		System.out.println("idx:"+Arrays.toString(idx));
		for (int i=1; i<=q; i++) {
			int x = sc.nextInt();
			int r=idx[x];
			int a=ary[r];
			int s=(r<n) ? r+1 : r-1;
			int b=ary[s];
			ary[r]=b;
			ary[s]=a;
			idx[a]=s;
			idx[b]=r;
//			System.out.println("ary:"+Arrays.toString(ary));
//			System.out.println("idx:"+Arrays.toString(idx));
		}
		for (int i=1; i<=n; i++) {
			System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
}
/*
5 5
1
2
3
4
5

7 7
7
7
7
7
7
7
7

10 6
1
5
2
9
6
6
*/

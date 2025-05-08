import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		long[] dif=new long[n+1];
		long sum=0;
		for (int i=0; i<n; i++) {
			dif[i]=ary[i+1]-ary[i];
			if (i>0) sum+=Math.abs(dif[i]);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(dif)+" "+sum);
		for (int qq=0; qq<q; qq++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int v=sc.nextInt();
			for (int i=l; i<=r; i++) ary[i]+=v;
			long pl=dif[l-1];
			long pr=dif[r];
			dif[l-1]+=v;
			dif[r]-=v;
			if (l>1) sum+=Math.abs(dif[l-1])-Math.abs(pl);
			if (r<n) sum+=Math.abs(dif[r])-Math.abs(pr);
//			System.out.println(Arrays.toString(ary));
//			System.out.println(Arrays.toString(dif)+" "+sum);
			System.out.println(sum);
		}
	}
}
/*
3 3
1 2 3
2 3 1
1 2 -1
1 3 2

20 10
61 51 92 -100 -89 -65 -89 -64 -74 7 87 -2 51 -39 -50 63 -23 36 74 37
2 2 -45
6 19 82
2 9 36
7 13 71
16 20 90
18 20 -24
14 17 -78
10 11 -55
7 19 -26
20 20 -7
*/

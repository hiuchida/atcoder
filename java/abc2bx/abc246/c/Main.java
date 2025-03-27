import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int x = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
		}
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<n; i++) {
			int a=ary[i];
			int c=Math.min(k, a/x);
			a-=c*x;
			k-=c;
			ary[i]=a;
		}
		Arrays.sort(ary);
//		System.out.println(k);
//		System.out.println(Arrays.toString(ary));
		long sum=0;
		for (int i=n-1; i>=0; i--) {
			if (k>0 && ary[i]>0) {
				ary[i]=0;
				k--;
			}
			sum+=ary[i];
		}
		System.out.println(sum);
	}
}
/*
5 4 7
8 3 10 5 13

5 100 7
8 3 10 5 13

20 815 60
2066 3193 2325 4030 3725 1669 1969 763 1653 159 5311 5341 4671 2374 4513 285 810 742 2981 202
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		long d=sc.nextLong();
		long[] ary=new long[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextLong();
		}
		TreeSet<Long> set=new TreeSet<>();
		for (int i=0; i<m; i++) {
			set.add(sc.nextLong());
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(set);
		long ans=-1;
		for (long v : ary) {
			long v1=v+d;
			Long v2=set.floor(v1);
//			System.out.println(v+" "+v1+" "+v2);
			if (v2==null) continue;
			if (v1-d<=v2) {
				ans=Math.max(ans, v+v2);
			}
		}
		System.out.println(ans);
	}
}
/*
2 3 2
3 10
2 5 15

3 3 0
1 3 3
6 2 7

1 1 1000000000000000000
1000000000000000000
1000000000000000000

8 6 1
2 5 6 5 2 1 7 9
7 2 5 5 2 4
*/

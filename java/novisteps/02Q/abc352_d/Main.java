import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n+1];
		int[] idx=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
			idx[ary[i]]=i;
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(idx));
		TreeSet<Integer> set=new TreeSet<>();
		int ans=Integer.MAX_VALUE;
		for (int i=1; i<=k; i++) {
			set.add(idx[i]);
		}
		int x=set.last()-set.first();
		ans=Math.min(ans, x);
//		System.out.println(set+" "+x);
		for (int i=2; i+k-1<=n; i++) {
			set.remove(idx[i-1]);
			set.add(idx[i+k-1]);
			x=set.last()-set.first();
			ans=Math.min(ans, x);
//			System.out.println(set+" "+x);
		}
		System.out.println(ans);
	}
}
/*
4 2
2 3 1 4

4 1
2 3 1 4

10 5
10 1 6 8 7 2 5 9 3 4
*/

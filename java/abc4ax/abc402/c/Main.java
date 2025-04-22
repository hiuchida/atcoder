import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		List<List<Integer>> list=new ArrayList<>();
		for (int i=0; i<m; i++) {
			List<Integer> lst=new ArrayList<>();
			int k=sc.nextInt();
			for (int j=0; j<k; j++) {
				lst.add(sc.nextInt());
			}
//			Collections.sort(lst);
			list.add(lst);
			
		}
		int[] ary=new int[n+1];
		for (int i=0; i<n; i++) {
			int b=sc.nextInt();
			ary[b]=i+1;
		}
		int[] cnt=new int[n+1];
		for (int i=0; i<m; i++) {
			int max=0;
			List<Integer> lst=list.get(i);
			for (int j=0; j<lst.size(); j++) {
				int v=lst.get(j);
				max=Math.max(max, ary[v]);
			}
			cnt[max]++;
		}
//		System.out.println(list);
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(cnt));
		long ans=0;
		for (int i=1; i<=n; i++) {
			ans+=cnt[i];
			System.out.println(ans);
		}
	}
}
/*
5 4
2 1 2
3 3 4 5
3 1 2 5
1 3
1 3 2 5 4

9 8
1 4
5 6 9 7 4 3
4 2 4 1 3
1 1
5 7 9 8 1 5
2 9 8
1 2
1 1
6 5 2 7 8 4 1 9 3
*/

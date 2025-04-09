import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		List<List<Integer>> list=new ArrayList<>();
		for (int i=0; i<m; i++) {
			int k=sc.nextInt();
			List<Integer> lst=new ArrayList<>();
			for (int j=0; j<k; j++) {
				int s=sc.nextInt();
				lst.add(s);
			}
			list.add(lst);
		}
		int[] ary=new int[m];
		for (int i=0; i<m; i++) {
			int p=sc.nextInt();
			ary[i]=p;
		}
//		System.out.println(list);
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=0; i < 1 << n; i++) {
			Set<Integer> set=new HashSet<>();
			for (int j=0; j<n; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					set.add(j+1);
				}
			}
//			System.out.println(set);
			boolean bng=false;
			for (int j=0; j<m; j++) {
				int c=0;
				for (int v : list.get(j)) {
					if (set.contains(v)) c++;
				}
				if (c%2!=ary[j]) {
					bng=true;
					break;
				}
			}
			if (!bng) ans++;
		}
		System.out.println(ans);
	}
}
/*
2 2
2 1 2
1 2
0 1

2 3
2 1 2
1 1
1 2
0 0 1

5 2
3 1 2 5
2 2 3
1 0
*/

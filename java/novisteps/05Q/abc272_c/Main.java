import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		List<Integer> lo=new ArrayList<>();
		List<Integer> le=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int a=ary[i];
			if (a%2==1) lo.add(a);
			else le.add(a);
		}
//		System.out.println(lo);
//		System.out.println(le);
		int ao=-1;
		if (lo.size()>1) {
			ao=lo.get(lo.size()-1)+lo.get(lo.size()-2);
		}
		int ae=-1;
		if (le.size()>1) {
			ae=le.get(le.size()-1)+le.get(le.size()-2);
		}
		int ans=Math.max(ao, ae);
		System.out.println(ans);
	}
}
/*
3
2 3 4

2
1 0
*/

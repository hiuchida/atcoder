import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int[] ary=new int[n];
		TreeSet<Long> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			set.add(0L+ary[i]);
		}
		for (int kk=1; kk<k; kk++) {
//			System.out.println(set);
			long v=set.pollFirst();
			for (int i=0; i<n; i++) {
				set.add(v+ary[i]);
			}
		}
		long ans=set.first();
		System.out.println(ans);
	}
}
/*
4 6
20 25 30 100

2 10
2 1

10 200000
955277671 764071525 871653439 819642859 703677532 515827892 127889502 881462887 330802980 503797872
*/

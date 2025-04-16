import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		String[] as=new String[n];
		for (int i=0; i<n; i++) {
			as[i]=sc.next().substring(3);
		}
		TreeSet<String> set=new TreeSet<>();
		for (int i=0; i<m; i++) {
			String t=sc.next();
			set.add(t);
		}
		int ans=0;
		for (int i=0; i<n; i++) {
			if (set.contains(as[i])) ans++;
		}
		System.out.println(ans);
	}
}
/*
3 3
142857
004159
071028
159
287
857

5 4
235983
109467
823476
592801
000333
333
108
467
983

4 4
000000
123456
987111
000000
000
111
999
111
*/

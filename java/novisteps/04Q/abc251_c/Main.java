import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<String> set=new TreeSet<>();
		int ans=0;
		int high=0;
		for (int i=1; i<=n; i++) {
			String s = sc.next();
			int t = sc.nextInt();
			if (set.contains(s)) continue;
			set.add(s);
			if (high<t) {
				ans=i;
				high=t;
			}
		}
		System.out.println(ans);
	}
}
/*
3
aaa 10
bbb 20
aaa 30

5
aaa 9
bbb 10
ccc 10
ddd 10
bbb 11

10
bb 3
ba 1
aa 4
bb 1
ba 5
aa 9
aa 2
ab 6
bb 5
ab 3
*/

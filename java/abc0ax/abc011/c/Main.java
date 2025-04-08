import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Set<Integer> set=new HashSet<>();
		for (int i=0; i<3; i++) {
			int a=sc.nextInt();
			set.add(a);
			if (n==a) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		int cnt=0;
		while (n>0) {
			int a=n-3;
			if (!set.contains(a)) {
				n=a;
				cnt++;
				continue;
			}
			int b=n-2;
			if (!set.contains(b)) {
				n=b;
				cnt++;
				continue;
			}
			int c=n-1;
			if (!set.contains(c)) {
				n=c;
				cnt++;
				continue;
			}
			System.out.println("NO");
			System.exit(0);
		}
		if (cnt>100) {
			System.out.println("NO");
			System.exit(0);
		}
		System.out.println("YES");
	}
}
/*
2
1
7
15

5
1
4
2

300
57
121
244
*/
/*
10
1
10
5

5
3
2
1

300
55
121
244
*/

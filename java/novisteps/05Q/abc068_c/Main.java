import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		TreeSet<Integer> set1=new TreeSet<>();
		TreeSet<Integer> setn=new TreeSet<>();
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			if (a==1) set1.add(b);
			if (b==n) setn.add(a);
		}
		for (int nxt : set1) {
			if (setn.contains(nxt)) {
				System.out.println("POSSIBLE");
				System.exit(0);
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}
/*
3 2
1 2
2 3

4 3
1 2
2 3
3 4

100000 1
1 99999

5 5
1 3
4 5
2 3
2 4
1 4
*/

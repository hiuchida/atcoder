import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = 0;
		int t0 = 0;
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			int v = sc.nextInt();
			r = Math.max(r - (t - t0), 0) + v;
			t0 = t;
		}
		System.out.println(r);
	}
}
/*
4
1 3
3 1
4 4
7 1

3
1 8
10 11
21 5

10
2 1
22 10
26 17
29 2
45 20
47 32
72 12
75 1
81 31
97 7
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		int max=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			if (max<ary[i]) max=ary[i];
		}
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<k; i++) {
			int b=sc.nextInt();
			set.add(b);
		}
		for (int i=0; i<n; i++) {
			if (max==ary[i]) {
				if (set.contains(i+1)) {
					System.out.println("Yes");
					System.exit(0);
				}
			}
		}
		System.out.println("No");
	}
}
/*
5 3
6 8 10 7 10
2 3 4

5 2
100 100 100 1 1
5 4

2 1
100 1
2
*/

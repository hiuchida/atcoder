import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		{
			TreeSet<Integer> set=new TreeSet<>();
			set.add(ary[0]);
//			System.out.println(set);
			for (int i=2; i<n; i+=2) {
				TreeSet<Integer> set2=new TreeSet<>();
				for (int j : set) {
					set2.add(j+ary[i]);
					set2.add(j-ary[i]);
				}
				set=set2;
//				System.out.println(set);
			}
			if (!set.contains(x)) {
				System.out.println("No");
				System.exit(0);
			}
		}
		{
			TreeSet<Integer> set=new TreeSet<>();
			set.add(0);
//			System.out.println(set);
			for (int i=1; i<n; i+=2) {
				TreeSet<Integer> set2=new TreeSet<>();
				for (int j : set) {
					set2.add(j+ary[i]);
					set2.add(j-ary[i]);
				}
				set=set2;
//				System.out.println(set);
			}
			if (!set.contains(y)) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
3 -1 1
2 1 3

5 2 0
2 2 2 2 2

4 5 5
1 2 3 4

3 2 7
2 7 4

10 8 -7
6 10 4 1 5 9 8 6 5 1
*/

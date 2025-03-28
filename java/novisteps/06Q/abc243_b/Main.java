import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			set.add(a);
		}
		int ans1=0;
		int cnt=0;
		for (int i=0; i<n; i++) {
			int b=sc.nextInt();
			if (ary[i]==b) ans1++;
			if (set.contains(b)) cnt++;
		}
		int ans2=cnt-ans1;
		System.out.println(ans1);
		System.out.println(ans2);
	}
}
/*
4
1 3 5 2
2 3 1 4

3
1 2 3
4 5 6

7
4 8 1 7 9 5 6
3 5 1 7 8 2 6
*/

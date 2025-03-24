import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			if (set.contains(a)) ans++;
			else set.add(a);
		}
		System.out.println(ans);
	}
}
/*
5
1
2
3
2
1

11
3
1
4
1
5
9
2
6
5
3
5
*/

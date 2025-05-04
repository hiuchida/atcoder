import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=(int)1e6;
		int s=sc.nextInt();
		int[] ary=new int[N+1];
		ary[1]=s;
		for (int i=2; i<=N; i++) {
			int a=ary[i-1];
			if (a%2==0) {
				ary[i]=a/2;
			} else {
				ary[i]=3*a+1;
			}
		}
//		System.out.println(Arrays.toString(ary));
		Set<Integer> set=new HashSet<>();
		for (int i=1; true; i++) {
			int a=ary[i];
			if (set.contains(a)) {
				System.out.println(i);
				System.exit(0);
			}
			set.add(a);
		}
	}
}
/*
8

7

54
*/

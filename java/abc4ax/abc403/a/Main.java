import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long ans=0;
		for (int i=0; i<n; i+=2) {
			ans+=ary[i];
		}
		System.out.println(ans);
	}
}
/*
7
3 1 4 1 5 9 2

1
100

14
100 10 1 10 100 10 1 10 100 10 1 10 100 10
*/

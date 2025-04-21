import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[5*n];
		for (int i=0; i<5*n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		long sum=0;
		for (int i=n; i<4*n; i++) {
			sum+=ary[i];
		}
		double ans=(double)sum/(3*n);
		System.out.println(ans);
	}
}
/*
1
10 100 20 50 30

2
3 3 3 4 5 6 7 8 99 100
*/

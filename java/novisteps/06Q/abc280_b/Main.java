import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] ans=new int[n];
		for (int i=0; i<n; i++) {
			if (i==0) ans[i]=ary[i];
			else ans[i]=ary[i]-ary[i-1];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(ans));
		for (int i=0; i<n; i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println();
	}
}
/*
3
3 4 8

10
314159265 358979323 846264338 -327950288 419716939 -937510582 97494459 230781640 628620899 -862803482
*/

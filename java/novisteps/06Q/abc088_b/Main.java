import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int a=0;
		int b=0;
		for (int i=n-1; i>=0; i--) {
			a+=ary[i];
			i--;
			if (i<0) break;
			b+=ary[i];
		}
		int ans=a-b;
		System.out.println(ans);
	}
}
/*
2
3 1

3
2 7 4

4
20 18 2 18
*/

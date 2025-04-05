import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum+=ary[i];
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary)+" "+sum);
		int ans=sum-ary[n-1]/2;
		System.out.println(ans);
	}
}
/*
3
4980
7980
6980

4
4320
4320
4320
4320
*/

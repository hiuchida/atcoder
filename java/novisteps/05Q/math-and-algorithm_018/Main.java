import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] cnt=new int[5];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt[a/100]++;
		}
//		System.out.println(Arrays.toString(cnt));
		long c1=(long)cnt[1]*cnt[4];
		long c2=(long)cnt[2]*cnt[3];
		long ans=c1+c2;
		System.out.println(ans);
	}
}
/*
5
100 300 400 400 200
*/

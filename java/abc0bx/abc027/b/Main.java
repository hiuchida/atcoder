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
		if (sum%n!=0) {
			System.out.println(-1);
			System.exit(0);
		}
		int ave=sum/n;
		for (int i=0; i<n; i++) {
			ary[i]-=ave;
		}
//		System.out.println(Arrays.toString(ary)+" "+sum+" "+ave);
		int ans=0;
		int cnt=0;
		for (int i=0; i<n; i++) {
			cnt+=ary[i];
			if (cnt!=0) ans++;
		}
		System.out.println(ans);
	}
}
/*
3
1 2 3

5
2 0 0 0 3

2
0 99

4
0 0 0 0
*/

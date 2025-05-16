import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int sum=0;
		int cnt=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			if (a>0) {
				sum+=a;
				cnt++;
			}
		}
//		System.out.println(sum+" "+cnt);
		int ans=sum/cnt;
		if (sum%cnt!=0) ans++;
		System.out.println(ans);
	}
}
/*
4
0 1 3 8

5
1 4 9 10 15
*/

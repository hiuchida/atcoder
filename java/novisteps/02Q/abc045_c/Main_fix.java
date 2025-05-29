import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		long ans=0;
		for (int i=0; i < 1 << (n-1); i++) {
			long val=ary[0]-'0';
			long sum=0;
			for (int j=0; j<n-1; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					sum+=val;
					val=ary[j+1]-'0';
				} else {
					val*=10;
					val+=ary[j+1]-'0';
				}
//				System.out.println(i+","+j+" : "+val+" "+sum+" "+ans);
			}
			sum+=val;
			ans+=sum;
//			System.out.println(i+" : "+val+" "+sum+" "+ans);
		}
		System.out.println(ans);
	}
}
/*
125

9999999999
*/
/*
2
*/

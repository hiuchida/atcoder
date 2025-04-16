import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
//		System.out.println(Arrays.toString(ary));
		long ans=Long.MAX_VALUE;
		for (int i=0; i<n; i++) {
			long c=(long)a*i;
			long cnt=check(ary, i);
			c+=b*cnt;
			ans=Math.min(ans, c);
		}
		System.out.println(ans);
	}
	static int check(char[] buf, int st) { //abc286_c: stから始まるbufの回文チェック
		int n=buf.length;
		int ans=0;
		for (int i=0; i<n/2; i++) {
			int i1=(i+st)%n;
			int i2=(n-1-i+st)%n;
			if (buf[i1]!=buf[i2]) {
				ans++;
			}
		}
//		System.out.println(st+" "+ans);
		return ans;
	}
}
/*
5 1 2
rrefa

8 1000000000 1000000000
bcdfcgaa
*/

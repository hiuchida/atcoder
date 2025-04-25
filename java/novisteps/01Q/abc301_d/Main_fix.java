import java.util.*;
public class Main {
	static char[] ary;
	static long n;
	static long ans=-1;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		ary=s.toCharArray();
		n=sc.nextLong();
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='?') {
				ary[i]='1';
				long min=calcMin(ary);
				if (min>n) ary[i]='0';
			}
		}
		long x=calc(ary);
		if (x<=n) ans=x;
		System.out.println(ans);
	}
	static long calc(char[] ary) {
		long ans=0;
		for (int i=0; i<ary.length; i++) {
			long v=0;
			if (ary[i]=='?') v=1;
			else v=ary[i]-'0';
			ans*=2;
			ans+=v;
		}
		return ans;
	}
	static long calcMin(char[] ary) {
		long ans=0;
		for (int i=0; i<ary.length; i++) {
			long v=0;
			if (ary[i]=='?') v=0;
			else v=ary[i]-'0';
			ans*=2;
			ans+=v;
		}
		return ans;
	}
}
/*
?0?
2

101
4

?0?
1000000000000000000
*/
/*
?0?
5

????????????????????????????????????????????????????????????
1000000000000000000
*/

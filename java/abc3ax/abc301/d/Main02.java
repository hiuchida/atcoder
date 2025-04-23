import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		long n=sc.nextLong();
		TreeSet<Long> set=new TreeSet<>();
		for (int i=0; i<ary.length; i++) {
//			System.out.println(Arrays.toString(ary)+" "+i);
			if (ary[i]=='?') {
				ary[i]='1';
				long x=calc(ary);
				set.add(x);
				ary[i]='0';
			}
			long x=calc(ary);
			set.add(x);
		}
//		System.out.println(set);
		Long ans=set.floor(n);
		if (ans==null) System.out.println(-1);
		else System.out.println(ans);
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
*/

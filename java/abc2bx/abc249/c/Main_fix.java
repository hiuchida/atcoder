import java.util.*;
public class Main {
	static int n;
	static int k;
	static Count[] ary;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		ary=new Count[n];
		for (int i=0; i<n; i++) {
			String s = sc.next();
			ary[i]=count(s);
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(ary[i]);
//		}
		Count c=new Count();
		dfs(0, c);
		System.out.println(ans);
	}
	static void dfs(int i, Count c) {
		if (i==n) {
			int a=c.ans();
			ans=Math.max(ans, a);
//			System.out.println(i+" "+c+" "+a);
			return;
		}
//		System.out.println(i+" "+c);
		Count c1=new Count();
		c1.add(c);
		dfs(i+1, c1);
		c1.add(ary[i]);
		dfs(i+1, c1);
	}
	static class Count {
		int[] cnt=new int[26];
		void add(Count c) {
			for (int i=0; i<cnt.length; i++) {
				cnt[i]+=c.cnt[i];
			}
		}
		int ans() {
			int a=0;
			for (int i=0; i<cnt.length; i++) {
				if (cnt[i]==k) a++;
			}
			return a;
		}
		@Override
		public String toString() {
			return Arrays.toString(cnt);
		}
	}
	static Count count(String s) {
		Count c=new Count();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			c.cnt[ch-'a']++;
		}
		return c;
	}
}
/*
4 2
abi
aef
bc
acg

2 2
a
b

5 2
abpqxyz
az
pq
bc
cy
*/

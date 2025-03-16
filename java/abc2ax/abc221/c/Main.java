import java.util.*;
public class Main {
	static char[] ary;
	static TreeSet<String> set = new TreeSet<>();
	static long ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		ary=n.toCharArray();
		StringBuilder sb=new StringBuilder();
		boolean[] flag=new boolean[ary.length];
		dfs(0, sb, flag);
//		System.out.println(set+" "+set.size());
		for (String s : set) {
			for (int i=1; i<=ary.length-1; i++) {
				if (s.charAt(0)=='0' || s.charAt(i)=='0') continue;
				String s1=s.substring(0, i);
				String s2=s.substring(i);
				int i1=Integer.parseInt(s1);
				int i2=Integer.parseInt(s2);
				ans=Math.max(ans, (long)i1*i2);
			}
		}
		System.out.println(ans);
	}
	static void dfs(int i, StringBuilder sb, boolean[] flag) {
		if (i==ary.length) {
			set.add(sb.toString());
			return;
		}
		for (int j=0; j<ary.length; j++) {
			if (flag[j]) continue;
			int v=ary[j]-'0';
			if (sb.length()==0 && v==0) continue;
			sb.append(ary[j]);
			flag[j]=true;
			dfs(i+1, sb, flag);
			sb.deleteCharAt(sb.length()-1);
			flag[j]=false;
		}
	}
}
/*
123

1010

998244353
*/
/*
123456789
*/

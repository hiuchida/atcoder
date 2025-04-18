import java.util.*;
public class Main {
	static int n;
	static int k;
	static String s;
	static char[] ary;
	static boolean[] flag;
	static Set<Long> uniq = new HashSet<>(3628800);
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		s = sc.next();
		ary = s.toCharArray();
		flag=new boolean[n];
//		Arrays.sort(ary);
//		is("zzxyy");
		char[] buf=new char[n];
		dfs(buf, 0, 0);
//		DEBUG(strs);
//		DEBUG(strs.size());
//		int cnt=strs.size();
//		Collections.sort(strs);
//		int cnt=0;
//		String pre="";
//		for (int i=0; i<strs.size(); i++) {
//			String s=strs.get(i);
//			if (!pre.equals(s)) {
//				cnt++;
//				pre=s;
//			}
//		}
		long cnt=ans;
		System.out.println(cnt);
	}
	static void dfs(char[] buf, int len, long v) {
		if (len == n) {
//			long v=calc(buf);
			if (!uniq.contains(v)) {
				uniq.add(v);
				if (!is(buf)) {
//					String s=new String(buf);
//					strs.add(v);
					ans++;
				}
			}
			return;
		}
		v*=26;
		for (int i=0; i<n; i++) {
			if (flag[i]) continue;
			flag[i]=true;
			buf[len]=ary[i];
			dfs(buf, len+1, v+buf[len]-'a');
			flag[i]=false;
		}
	}
	static long calc(char[] buf) {
		long sum=0;
		for (int i=0; i<n; i++) {
			sum*=26;
			sum+=buf[i]-'a';
		}
		return sum;
	}
	static boolean is(char[] buf) {
		for (int i=0; i<=n-k; i++) {
			boolean rc = true;
			for (int j=0; j<k; j++) {
				if (buf[i+j] == buf[i+k-1-j]) {
//					DEBUG(s0 + " " + (i+j) + " " + (i+k-1-j) + " T");
				}
				else {
//					DEBUG(s0 + " " + (i+j) + " " + (i+k-1-j) + " F");
					rc=false;
					break;
				}
			}
			if (rc) {
//				DEBUG(s0 + " " + i + " " + rc);
				return true;
			}
		}
		return false;
	}
}
/*
3 2
aab

5 3
zzyyx

10 5
abcwxyzyxw
*/

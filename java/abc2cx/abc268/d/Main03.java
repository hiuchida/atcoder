import java.util.*;
public class Main {
	static int n;
	static String[] ary;
	static int len;
	static Set<String> set;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
			len+=ary[i].length();
		}
		len+=n-1;
		if (n==1) {
			for (int i=0; i<m; i++) {
				String s = sc.next();
				if (s.equals(ary[0])) {
					System.out.println(-1);
					System.exit(0);
				}
			}
			System.out.println(ary[0]);
			System.exit(0);
		}
		set=new HashSet<>();
		for (int i=0; i<m; i++) {
			String s = sc.next();
			set.add(s);
		}
		List<String> list=new ArrayList<>();
		boolean[] flag=new boolean[n];
		dfs(list, flag, 0);
		System.out.println(-1);
	}
	static void dfs(List<String> list, boolean[] flag, int us) {
		if (list.size()==n) {
			String s=String.join("", list);
			if (s.length()<3 || s.length()>16) return;
			if (!set.contains(s)) {
				System.out.println(s);
				System.exit(0);
			}
			return;
		}
//		System.out.println(list+" "+us);
		for (int i=0; i<n; i++) {
			if (flag[i]) continue;
			flag[i]=true;
			if (list.size()<n-1) {
				for (int j=0; j+len+us<=16+1; j++) {
					String s=ary[i];
					for (int k=0; k<=j; k++) {
						s=s+"_";
					}
					list.add(s);
					dfs(list, flag, us+j);
					list.remove(list.size()-1);
				}
			} else {
				String s=ary[i];
				list.add(s);
				dfs(list, flag, us);
				list.remove(list.size()-1);
			}
			flag[i]=false;
		}
	}
}
/*
1 1
chokudai
chokudai

2 2
choku
dai
chokudai
choku_dai

2 2
chokudai
atcoder
chokudai_atcoder
atcoder_chokudai

4 4
ab
cd
ef
gh
hoge
fuga
____
_ab_cd_ef_gh_
*/
/*
1 1
chokudai
chokudaj
*/

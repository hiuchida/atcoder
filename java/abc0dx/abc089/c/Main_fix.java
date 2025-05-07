import java.util.*;
public class Main {
	static final int N=5;
	static int[] ary;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ary=new int[N];
		int n=sc.nextInt();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			char ch=s.charAt(0);
			int idx=0;
			switch (ch) {
			case 'H':
				idx++;
			case 'C':
				idx++;
			case 'R':
				idx++;
			case 'A':
				idx++;
			case 'M':
				ary[idx]++;
				break;
			}
		}
//		System.out.println(Arrays.toString(ary));
		boolean[] flag=new boolean[N];
		dfs(0, 0, 1, flag);
		System.out.println(ans);
	}
	static void dfs(int i, int st, long v, boolean[] flag) {
		if (i==3) {
			ans+=v;
//			System.out.println(i+","+st+": "+v+" "+Arrays.toString(flag)+" "+ans);
			return;
		}
//		System.out.println(i+","+st+": "+v+" "+Arrays.toString(flag));
		for (int j=st; j<N; j++) {
			if (flag[j]) continue;
			if (ary[j]==0) continue;
			flag[j]=true;
			dfs(i+1, j+1, v*ary[j], flag);
			flag[j]=false;
		}
	}
}
/*
5
MASHIKE
RUMOI
OBIRA
HABORO
HOROKANAI

4
ZZ
ZZZ
Z
ZZZZZZZZZZ

5
CHOKUDAI
RNG
MAKOTO
AOKI
RINGO
*/

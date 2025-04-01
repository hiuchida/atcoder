import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		long ans=0;
		ans=move(ary, ans, 'a', 0);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		ans=move(ary, ans, 'r', 6);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		ans=move(ary, ans, 't', 1);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		ans=move(ary, ans, 'e', 5);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		ans=move(ary, ans, 'c', 2);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		ans=move(ary, ans, 'd', 4);
//		System.out.println(Arrays.toString(ary)+" "+ans);
		System.out.println(ans);
	}
	static long move(char[] ary, long ans, char ch, int idx) {
		int find=0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]==ch) find=i;
		}
		if (idx<find) {
			for (int i=find; i>idx; i--) {
				ary[i]=ary[i-1];
				ans++;
			}
			ary[idx]=ch;
		} else if (idx>find) {
			for (int i=find; i<idx; i++) {
				ary[i]=ary[i+1];
				ans++;
			}
			ary[idx]=ch;
		}
		return ans;
	}
}
/*
catredo

atcoder

redocta
*/

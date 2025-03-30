import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		String t = sc.next();
		char[] as=s.toCharArray();
		char[] at=t.toCharArray();
		int ans=0;
		for (int i=0; i<n; i++) {
			if (as[i]!=at[i]) ans++;
		}
		System.out.println(ans);
	}
}
/*
6
abcarc
agcahc

7
atcoder
contest

8
chokudai
chokudai

10
vexknuampx
vzxikuamlx
*/

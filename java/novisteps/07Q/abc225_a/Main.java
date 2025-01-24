import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		int ans = 0;
		if (ary[0]==ary[1]) {
			if (ary[1]==ary[2]) ans=1;
			else ans=3;
		} else if (ary[1]==ary[2]) {
			ans=3;
		} else if (ary[0]==ary[2]) {
			ans=3;
		} else {
			ans=6;
		}
		System.out.println(ans);
	}
}
/*
aba

ccc

xyz
*/

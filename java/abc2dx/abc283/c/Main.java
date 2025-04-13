import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		long ans=0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]!='0') ans++;
			else {
				if (i<ary.length-1 && ary[i+1]=='0') {
					ans++;
					i++;
				} else {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
40004

1355506027

10888869450418352160768000001
*/

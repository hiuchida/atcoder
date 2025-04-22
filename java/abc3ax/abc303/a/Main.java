import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] as=s.toCharArray();
		String t=sc.next();
		char[] at=t.toCharArray();
		for (int i=0; i<n; i++) {
			if (as[i]==at[i]) continue;
			if (as[i]=='1' && at[i]=='l') continue;
			if (as[i]=='l' && at[i]=='1') continue;
			if (as[i]=='0' && at[i]=='o') continue;
			if (as[i]=='o' && at[i]=='0') continue;
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
	}
}
/*
3
l0w
1ow

3
abc
arc

4
nok0
n0ko
*/

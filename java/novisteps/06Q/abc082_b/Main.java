import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String t=sc.next();
		char[] as=s.toCharArray();
		char[] at=t.toCharArray();
		Arrays.sort(as);
		Arrays.sort(at);
		int n=at.length;
		for (int i=0; i<n/2; i++) {
			char ch=at[i];
			at[i]=at[n-1-i];
			at[n-1-i]=ch;
		}
		String ss=new String(as);
		String st=new String(at);
//		System.out.println(ss);
//		System.out.println(st);
		if (ss.compareTo(st)<0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
yx
axy

ratcode
atlas

cd
abc

w
ww

zzz
zzz
*/

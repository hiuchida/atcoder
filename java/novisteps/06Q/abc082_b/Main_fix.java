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
		reverse(at);
//		int n=at.length;
//		for (int i=0; i<n/2; i++) {
//			char ch=at[i];
//			at[i]=at[n-1-i];
//			at[n-1-i]=ch;
//		}
		String ss=new String(as);
		String st=new String(at);
//		System.out.println(ss);
//		System.out.println(st);
		if (ss.compareTo(st)<0) System.out.println("Yes");
		else System.out.println("No");
	}
	static void reverse(char[] ary) { //abc082_b,abc192_c: 指定された配列の要素の順序を逆にします。see Collections.reverse()
		for (int i=0, mid=ary.length/2, j=ary.length-1; i<mid; i++, j--) {
			char tmp=ary[i];
			ary[i]=ary[j];
			ary[j]=tmp;
		}
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

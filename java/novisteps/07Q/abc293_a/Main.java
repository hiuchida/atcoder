import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=1; i<=s.length()/2; i++) {
			swap(ary, 2*i-1-1, 2*i-1);
		}
		String ans=new String(ary);
		System.out.println(ans);
	}
	static void swap(char[] ary, int i, int j) { //abc293_a: ary[i]とary[j]のスワップ
		char t=ary[i];
		ary[i]=ary[j];
		ary[j]=t;
	}
}
/*
abcdef

aaaa

atcoderbeginnercontest
*/

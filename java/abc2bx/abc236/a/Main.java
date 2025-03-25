import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int a = sc.nextInt();
		int b = sc.nextInt();
		char[] ary=s.toCharArray();
		char t=ary[a-1];
		ary[a-1]=ary[b-1];
		ary[b-1]=t;
		String ans=new String(ary);
		System.out.println(ans);
	}
}
/*
chokudai
3 5

aa
1 2

aaaabbbb
1 8
*/

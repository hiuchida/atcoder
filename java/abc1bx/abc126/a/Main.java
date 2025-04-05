import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		char[] ary=s.toCharArray();
		ary[k-1]=(char)(ary[k-1]-'A'+'a');
		String ans=new String(ary);
		System.out.println(ans);
	}
}
/*
3 1
ABC

4 3
CABA
*/

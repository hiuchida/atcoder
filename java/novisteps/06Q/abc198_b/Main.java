import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int lt=0;
		int rt=ary.length-1;
		while (lt<rt) {
			if (ary[lt]=='0' && ary[rt]=='0') {
				lt++;
				rt--;
				continue;
			}
			break;
		}
//		System.out.println(lt+" "+rt);
		while (lt<rt && ary[rt]=='0') rt--;
//		System.out.println(lt+" "+rt);
		if (lt>=rt) {
			System.out.println("Yes");
			System.exit(0);
		}
		for (int i=lt; i<rt; i++) {
//			System.out.println(i+" "+(rt-i));
			if (ary[i]!=ary[rt-i]) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
1210

777

123456789
*/

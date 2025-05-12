import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] ary=new int[256];
		String s=sc.next();
		for (int i=0; i<s.length(); i++) ary[s.charAt(i)]++;
		for (int i='a'; i<='z'; i++) {
			if (ary[i]==0) {
				System.out.println((char)i);
				System.exit(0);
			}
		}
	}
}
/*
a

abcdfhijklmnopqrstuvwxyz

qazplwsxokmedcijnrfvuhbgt
*/

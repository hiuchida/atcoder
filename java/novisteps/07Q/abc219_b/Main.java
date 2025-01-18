import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] ary = new String[3];
		for (int i=0; i<3; i++) {
			String s = sc.next();
			ary[i]=s;
		}
		String t = sc.next();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<t.length(); i++) {
			int j=(int)(t.charAt(i)-'1');
			sb.append(ary[j]);
		}
		System.out.println(sb);
	}
}
/*
mari
to
zzo
1321

abra
cad
abra
123

a
b
c
1
*/

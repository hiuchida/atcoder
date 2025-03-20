import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<s.length(); i++) {
			char cur=s.charAt(i);
			int j=i;
			for (; j<s.length(); j++) {
				char ch=s.charAt(j);
				if (cur!=ch) {
					break;
				}
			}
			int c=j-i;
			sb.append(cur).append(c);
			i=j-1;
		}
		System.out.println(sb);
	}
}
/*
aabbbaad

aabbbbbbbbbbbbxyza

edcba
*/

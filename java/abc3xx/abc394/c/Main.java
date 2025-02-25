import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		s+=" ";
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (ch!='W') {
				if (ch!=' ') sb.append(ch);
			} else {
				StringBuilder sb2 = new StringBuilder();
				StringBuilder sb3 = new StringBuilder();
				sb2.append('A');
				sb3.append('W');
				for (int j=i+1; j<s.length(); j++) {
					ch=s.charAt(j);
					if (ch=='W') {
						sb2.append('C');
						sb3.append('W');
					} else if (ch=='A') {
						sb2.append('C');
						sb.append(sb2);
						i=j;
						break;
					} else {
						sb.append(sb3);
						i=j-1;
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}
/*
WACWA

WWA

WWWWW
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int ans=0;
		int idx=1;
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (idx%2==1) {
				if (ch=='o') {
					ans++;
					i--;
				}
			} else {
				if (ch=='i') {
					ans++;
					i--;
				}
			}
			idx++;
		}
		if (s.charAt(s.length()-1)=='i') ans++;
		System.out.println(ans);
	}
}
/*
ioi

iioo

io
*/

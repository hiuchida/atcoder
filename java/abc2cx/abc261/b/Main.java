import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				char ch1=ary[i].charAt(j);
				char ch2=ary[j].charAt(i);
				if (ch1=='D' && ch2=='D') continue;
				if (ch1=='W' && ch2=='L') continue;
				if (ch1=='L' && ch2=='W') continue;
				System.out.println("incorrect");
				System.exit(0);
			}
		}
		System.out.println("correct");
	}
}
/*
4
-WWW
L-DD
LD-W
LDW-

2
-D
D-
*/

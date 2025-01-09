import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int mid = (n+1)/2;
		if (n%2 == 0) ng();
		for (int i = 0; i < mid-1; i++) {
			if (s.charAt(i) != '1') ng();
		}
		if (s.charAt(mid-1) != '/') ng();
		for (int i = mid; i < n; i++) {
			if (s.charAt(i) != '2') ng();
		}
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
5
11/22

1
/

4
1/22

5
22/11
*/

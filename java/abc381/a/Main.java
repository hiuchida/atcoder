import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int mid = (n+1)/2;
		boolean ng = false;
		if (n%2 == 0) ng = true;
		for (int i = 0; i < mid-1; i++) {
			if (s.charAt(i) != '1') ng = true;
		}
		if (s.charAt(mid-1) != '/') ng = true;
		for (int i = mid; i < n; i++) {
			if (s.charAt(i) != '2') ng = true;
		}
		if (ng)
			System.out.println("No");
		else
			System.out.println("Yes");
	}
}

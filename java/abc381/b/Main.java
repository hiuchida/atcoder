import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int mid = s.length()/2;
		int[] ary = new int[256];
		boolean ng = false;
		if (s.length()%2 == 1) ng = true;
		for (int i = 1; i <= mid; i++) {
			if (s.charAt(2*i-2) != s.charAt(2*i-1)) ng = true;
			else ary[s.charAt(2*i-2)]++;
		}
		for (int i = 'a'; i <= 'z'; i++) {
			if (ary[i] > 1) ng = true;
		}
		if (ng)
			System.out.println("No");
		else
			System.out.println("Yes");
	}
}

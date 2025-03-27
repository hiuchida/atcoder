import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[] cnt=new int[10];
		for (int i=0; i<s.length(); i++) {
			cnt[s.charAt(i)-'0']++;
		}
		for (int i=0; i<10; i++) {
			if (cnt[i]==0) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
}
/*
023456789

459230781
*/

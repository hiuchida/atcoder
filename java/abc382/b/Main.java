import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		String s = sc.next();
		char[] ary = s.toCharArray();
		for (int i = ary.length - 1; i >= 0; i--) {
			if (d <= 0) {
				break;
			}
			if (ary[i] == '@') {
				ary[i] = '.';
				d--;
			}
		}
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i]);
		}
		System.out.println();
	}
}

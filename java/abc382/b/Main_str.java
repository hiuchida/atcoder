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
		String ss = new String(ary);
		System.out.println(ss);
	}
}

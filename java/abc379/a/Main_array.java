import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[3];
		for (int i=2; i>=0; i--) {
			ary[i] = n%10;
			n /= 10;
		}
		int[] bca = { 1, 2, 0 };
		int[] cab = { 2, 0, 1 };
		System.out.println(mkstr(ary, bca) + " " + mkstr(ary, cab));
	}
	static String mkstr(int[] ary, int[] idx) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<ary.length; i++) {
			sb.append(ary[idx[i]]);
		}
		return sb.toString();
	}
}

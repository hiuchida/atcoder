import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sa=0;
		for (int i=0; i<9; i++) {
			int a = sc.nextInt();
			sa+=a;
		}
		int sb=0;
		for (int i=0; i<8; i++) {
			int b = sc.nextInt();
			sb+=b;
		}
		System.out.println(sa-sb+1);
	}
}
/*
0 1 0 1 2 2 0 0 1
1 1 0 0 0 0 1 0

0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
*/

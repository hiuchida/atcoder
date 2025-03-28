import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		while (true) {
			if (v<a) {
				System.out.println("F");
				System.exit(0);
			}
			v-=a;
			if (v<b) {
				System.out.println("M");
				System.exit(0);
			}
			v-=b;
			if (v<c) {
				System.out.println("T");
				System.exit(0);
			}
			v-=c;
		}
	}
}
/*
25 10 11 12

30 10 10 10

100000 1 1 1
*/

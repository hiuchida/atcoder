import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		long a=0;
		long b=1;
		for (int i=1; true; i++) {
			a+=b;
			b*=2;
			if (a>h) {
				System.out.println(i);
				break;
			}
		}
	}
}
/*
54

7

262144
*/

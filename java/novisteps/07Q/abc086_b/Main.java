import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int n;
		if (b<10) n=a*10+b;
		else if (b<100) n=a*100+b;
		else n=a*1000+b;
		for (int i=1; true; i++) {
			if (i*i>n) break;
			if (i*i==n) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
1 21

100 100

12 10
*/

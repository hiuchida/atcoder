import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int a=0; 4*a<=n; a++) {
			int aa=n-4*a;
			int b=aa/7;
			if (aa==b*7) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
11

40

3
*/

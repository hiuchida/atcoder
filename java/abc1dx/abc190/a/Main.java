import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
//		System.out.println(a+" "+b);
		while (true) {
			if (c==0) a--;
			else b--;
//			System.out.println(c+": "+a+" "+b);
			c=(c+1)%2;
			if (b<0) {
				System.out.println("Takahashi");
				System.exit(0);
			} else if (a<0) {
				System.out.println("Aoki");
				System.exit(0);
			}
		}
	}
}
/*
2 1 0

2 2 0

2 2 1
*/

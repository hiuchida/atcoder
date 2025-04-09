import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int y=sc.nextInt();
		for (int a=0; a<=n; a++) {
			int aa=a*10000;
			if (aa>y) break;
			for (int b=0; b<=n; b++) {
				int bb=b*5000;
				if (aa+bb>y) break;
				int c=n-a-b;
				int cc=c*1000;
				if (aa+bb+cc==y) {
					System.out.println(a+" "+b+" "+c);
					System.exit(0);
				}
			}
		}
		System.out.println("-1 -1 -1");
	}
}
/*
9 45000

20 196000

1000 1234000

2000 20000000
*/

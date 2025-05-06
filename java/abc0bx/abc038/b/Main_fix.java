import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h1=sc.nextInt();
		int w1=sc.nextInt();
		int h2=sc.nextInt();
		int w2=sc.nextInt();
		if (h1==h2 || h1==w2 || h2==w1 || w1==w2) System.out.println("YES");
		else System.out.println("NO");
	}
}
/*
1080 1920
1080 1920

1080 1920
1920 1080

334 668
668 1002

100 200
300 150

120 120
240 240
*/

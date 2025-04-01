import java.util.*;
public class Main {
	static int n;
	static int x;
	static int y;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		long[] red=new long[n+1];
		long[] blue=new long[n+1];
		red[1]=0;
		blue[1]=1;
		for (int i=2; i<=n; i++) {
			blue[i]=red[i-1]+blue[i-1]*y;
			red[i]=red[i-1]+blue[i]*x;
		}
//		System.out.println("r:"+Arrays.toString(red));
//		System.out.println("b:"+Arrays.toString(blue));
		System.out.println(red[n]);
	}
}
/*
2 3 4

1 5 5

10 5 5
*/

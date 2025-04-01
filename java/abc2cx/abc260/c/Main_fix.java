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
		red[n]=1;
		for (int i=n; i>1; i--) {
//			System.out.println(i+" r:"+Arrays.toString(red));
//			System.out.println(i+" b:"+Arrays.toString(blue));
			red[i-1]+=red[i];
			blue[i]+=red[i]*x;
//			red[i]=0;
			red[i-1]+=blue[i];
			blue[i-1]+=blue[i]*y;
//			blue[i]=0;
		}
//		System.out.println("r:"+Arrays.toString(red));
//		System.out.println("b:"+Arrays.toString(blue));
		System.out.println(blue[1]);
	}
}
/*
2 3 4

1 5 5

10 5 5
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		int k = sc.nextInt();
		for (int i=0; i<k; i++) {
			x=calc(x, i);
//			System.out.println(i+" "+x);
		}
		System.out.println(x);
	}
	static long calc(long x, int n) {
		long m=1;
		for (int i=0; i<=n; i++) {
			m*=10;
		}
		long x1=x%m;
		long x2=x1*10/m;
		long x3=x/m*m;
		if (x2>=5) x3+=m;
//		System.out.println(x1+" "+x2+" "+x3+" "+m);
		return x3;
	}
}
/*
2048 2

1 15

999 3

314159265358979 12
*/

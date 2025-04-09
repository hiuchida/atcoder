import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		long ans=4*(r-1)+1;
		ans += 4*calc(r);
		System.out.println(ans);
	}
	static long calc(int r) {
		long rr=4L*r*r;
		int y0=r;
		long sum=0;
		for (int x=1; x<=r; x++) {
			for (int y=y0; y>=1; y--) {
				long xx=(2L*x+1)*(2*x+1);
				long yy=(2L*y+1)*(2*y+1);
				if (xx+yy<=rr) {
					sum+=y;
					y0=y;
					break;
				} else {
					;
				}
			}
		}
		return sum;
	}
}
/*
2

4

26
*/
/*
1

1000000
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		long ans1=0;
		long sum2=0;
		long ans3=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			ans1=calc1(0, 0, ans1, a);
			sum2+=(long)a*a;
			ans3=calc3(0, 0, ans3, a);
		}
//		System.out.println(Arrays.toString(ary));
		double ans2=Math.sqrt(sum2);
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}
	static long calc1(long x1, long y1, long x2, long y2) { //abc180_b: x1,y1からx2,y2までのマンハッタン距離
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
	static long calc3(long x1, long y1, long x2, long y2) { //abc180_b: x1,y1からx2,y2までのチェビシェフ距離
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.max(Math.abs(dx), Math.abs(dy));
	}
}
/*
2
2 -1

10
3 -1 -4 1 -5 9 2 -6 5 -3
*/

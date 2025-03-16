import java.util.*;
public class Main {
	static int n;
	static int cnt;
	static int[] ary;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		String s = sc.next();
		cnt=0;
		int sum=0;
		ary=new int[n];
		for (int i=0; i<n; i++) {
			if (s.charAt(i) == '1') {
				ary[cnt++]=i;
				sum+=i;
			}
		}
		int sum2=0;
		for (int i=0; i<cnt/2; i++) {
			sum2+=ary[i+1]-ary[i];
		}
		int sum3=0;
		for (int i=cnt/2; i<cnt-1; i++) {
			sum3+=ary[i+1]-ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(sum+" "+sum2+" "+sum3);
		int lt=ary[0];
		if (sum2<sum3) {
			lt+=sum3-sum2;
		} else {
			lt+=sum2-sum3;
		}
		long ans = Integer.MAX_VALUE;
		for (int d=0; d<=10000; d++) {
			ans=Math.min(ans, calc(lt+d));
		}
		System.out.println(ans);
	}
	static int calc(int lt) {
		int x=0;
		for (int i=0; i<cnt; i++) {
			if (lt<0 || lt>=n) return Integer.MAX_VALUE;
			int dif=Math.abs(ary[i]-lt);
			x+=dif;
//			System.out.println(lt+" "+ary[i]+" "+dif+" "+x);
			lt++;
		}
		return x;
	}
}
/*
7
0101001

3
100

10
0101001001
0001111000

10
0101001101
0001111100
*/

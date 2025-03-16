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
		int ave=sum/cnt;
		int lt=ave-cnt/2;
//		System.out.println(Arrays.toString(ary));
//		System.out.println(sum+" "+ave+" "+lt);
		long ans = Integer.MAX_VALUE;
		ans=Math.min(ans, calc(lt-1));
		ans=Math.min(ans, calc(lt));
		ans=Math.min(ans, calc(lt+1));
		ans=Math.min(ans, calc(lt+2));
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

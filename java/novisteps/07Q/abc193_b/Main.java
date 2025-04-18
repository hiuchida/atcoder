import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			int p=sc.nextInt();
			int x=sc.nextInt();
			int c=x-a;
			if (c>0) {
				ans=Math.min(ans, p);
			}
		}
		if (ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
}
/*
3
3 9 5
4 8 5
5 7 5

3
5 9 5
6 8 5
7 7 5

10
158260522 877914575 602436426
24979445 861648772 623690081
433933447 476190629 262703497
211047202 971407775 628894325
731963982 822804784 450968417
430302156 982631932 161735902
880895728 923078537 707723857
189330739 910286918 802329211
404539679 303238506 317063340
492686568 773361868 125660016
*/

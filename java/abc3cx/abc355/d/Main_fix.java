import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] al=new int[n];
		int[] ar=new int[n+1];
		for (int i=0; i<n; i++) {
			al[i]=sc.nextInt();
			ar[i]=sc.nextInt();
		}
		ar[n]=Integer.MAX_VALUE;
		Arrays.sort(al);
		Arrays.sort(ar);
//		System.out.println(Arrays.toString(al));
//		System.out.println(Arrays.toString(ar));
		long ans=calc(n, 2);
		int ir=0;
		for (int il=0; il<n; il++) {
			while (ar[ir]<al[il]) {
				ir++;
			}
//			System.out.println(il+" "+ir);
			ans -= ir;
		}
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
		long ans=1;
		for (int i=0; i<b; i++) ans*=a-i;
		for (int i=1; i<=b; i++) ans/=i;
		return ans;
	}
}
/*
3
1 5
7 8
3 7

3
3 4
2 5
1 6

2
1 2
3 4
*/
/*
3
1 2
3 4
5 6
*/

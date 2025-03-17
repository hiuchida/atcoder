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
		long ans=(long)n*(n-1)/2L;
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

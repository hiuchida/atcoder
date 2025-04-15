import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int p=sc.nextInt()-1;
		int q=sc.nextInt()-1;
		int r=sc.nextInt()-1;
		int s=sc.nextInt()-1;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		for (int i=p, j=r; i<=q; i++, j++) {
			swap(ary, i, j);
		}
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<n; i++) {
			System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
	static void swap(int[] ary, int i, int j) { //abc286_a,abc350_c: ary[i]とary[j]のスワップ
		int t=ary[i];
		ary[i]=ary[j];
		ary[j]=t;
	}
}
/*
8 1 3 5 7
1 2 3 4 5 6 7 8

5 2 3 4 5
2 2 1 1 1

2 1 1 2 2
50 100

10 2 4 7 9
22 75 26 45 72 81 47 29 97 2
*/

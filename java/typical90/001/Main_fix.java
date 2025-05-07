import java.util.*;
public class Main {
	static int n;
	static int l;
	static int k;
	static int[] ary;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		l=sc.nextInt();
		k=sc.nextInt();
		ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		int ok=0;
		int ng=l+1;
		while (ok+1<ng) {
			int mid=(ok+ng)/2;
			boolean rc=check(mid);
//			System.out.println(ok+" "+ng+" "+mid+" : "+rc);
			if (rc) ok=mid;
			else ng=mid;
		}
		int ans=ok;
		System.out.println(ans);
	}
	static boolean check(int x) {
		int pre=0;
		int cnt=0;
		for (int i=0; i<n; i++) {
			if (ary[i]-pre>=x) {
				pre=ary[i];
				cnt++;
			}
		}
		if (l-pre>=x) cnt++;
		return cnt>=k+1;
	}
}
/*
3 34
1
8 13 26

7 45
2
7 11 16 20 28 34 38

3 100
1
28 54 81

3 100
2
28 54 81

20 1000
4
51 69 102 127 233 295 350 388 417 466 469 523 553 587 720 739 801 855 926 954
*/
/*
1 1
1
1
*/

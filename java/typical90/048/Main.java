import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		int[] ac=new int[2*n];
		System.arraycopy(ab, 0, ac, n, n);
		for (int i=0; i<n; i++) ac[i]=aa[i]-ab[i];
		Arrays.sort(ac);
//		System.out.println(Arrays.toString(ac));
		long ans=0;
		for (int i=0; i<k; i++) ans+=ac[ac.length-1-i];
		System.out.println(ans);
	}
}
/*
4 3
4 3
9 5
15 8
8 6

2 2
7 6
3 2

10 12
987753612 748826789
36950727 36005047
961239509 808587458
905633062 623962559
940964276 685396947
959540552 928301562
60467784 37828572
953685176 482123245
87983282 66762644
912605260 709048491
*/

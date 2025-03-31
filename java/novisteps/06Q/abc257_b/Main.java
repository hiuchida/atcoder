import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int q = sc.nextInt();
		int[] ary=new int[k];
		for (int i=0; i<k; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<q; i++) {
			int j = sc.nextInt();
			int a=ary[j-1];
			if (a!=n) {
				boolean bhit=false;
				for (int h=0; h<k; h++) {
					if (a+1==ary[h]) bhit=true;
				}
				if (!bhit) ary[j-1]++;
			}
//			System.out.println(Arrays.toString(ary));
		}
		for (int i=0; i<k; i++) {
			System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
}
/*
5 3 5
1 3 4
3 3 1 1 2

2 2 2
1 2
1 2

10 6 9
1 3 5 7 8 9
1 2 3 4 5 6 5 6 2
*/

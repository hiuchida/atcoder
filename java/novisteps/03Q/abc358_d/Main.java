import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			aa[i]=a;
		}
		int[] ab=new int[m];
		for (int i=0; i<m; i++) {
			int b = sc.nextInt();
			ab[i]=b;
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		long ans=0;
		int j=0;
		int cnt=0;
		for (int i=0; i<m; i++) {
			for (; j<n; j++) {
				if (aa[j]<ab[i]) ;
				else {
					ans+=aa[j];
//					System.out.println(i+" "+j+" "+ans);
					j++;
					cnt++;
					break;
				}
			}
		}
		if (cnt==m) System.out.println(ans);
		else System.out.println(-1);
	}
}
/*
4 2
3 4 5 4
1 4

3 3
1 1 1
1000000000 1000000000 1000000000

7 3
2 6 8 9 5 1 11
3 5 7
*/

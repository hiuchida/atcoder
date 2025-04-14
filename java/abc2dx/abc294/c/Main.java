import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		int[] ab=new int[m];
		for (int i=0; i<m; i++) {
			ab[i]=sc.nextInt();
		}
		int ans=1;
		int i=0;
		int j=0;
		StringBuilder sba=new StringBuilder();
		StringBuilder sbb=new StringBuilder();
		while (i<n && j<m) {
			if (aa[i]<ab[j]) {
				sba.append(ans+" ");
				ans++;
				i++;
			} else {
				sbb.append(ans+" ");
				ans++;
				j++;
			}
		}
		while (i<n) {
			sba.append(ans+" ");
			ans++;
			i++;
		}
		while (j<m) {
			sbb.append(ans+" ");
			ans++;
			j++;
		}
		System.out.println(sba);
		System.out.println(sbb);
	}
}
/*
4 3
3 14 15 92
6 53 58

4 4
1 2 3 4
100 200 300 400

8 12
3 4 10 15 17 18 22 30
5 7 11 13 14 16 19 21 23 24 27 28
*/

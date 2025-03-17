import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa=new int[n];
		int[] ac=new int[n+m];
		int idx=0;
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ac[idx]=aa[i];
			idx++;
		}
		int[] ab=new int[m];
		for (int i=0; i<m; i++) {
			ab[i]=sc.nextInt();
			ac[idx]=ab[i];
			idx++;
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		Arrays.sort(ac);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
//		System.out.println(Arrays.toString(ac));
		for (int i=0; i<n+m-1; i++) {
			int x=ac[i];
			int y=ac[i+1];
			for (int j=0; j<n-1; j++) {
				if (x==aa[j] && y==aa[j+1]) {
					System.out.println("Yes");
					System.exit(0);
				}
			}
		}
		System.out.println("No");
	}
}
/*
3 2
3 2 5
4 1

3 2
3 1 5
4 2

1 1
1
2
*/

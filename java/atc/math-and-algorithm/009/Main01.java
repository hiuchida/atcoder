import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int s=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		for (int i=0; i < 1 << n; i++) {
			int sum=0;
			for (int j=0; j<n; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					sum+=ary[j];
				}
			}
			if (sum==s) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
3 11
2 5 9

4 11
3 1 4 5
*/

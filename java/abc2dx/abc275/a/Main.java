import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int max=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			max=Math.max(max, ary[i]);
		}
		for (int i=0; i<n; i++) {
			if (ary[i]==max) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
	}
}
/*
3
50 80 70

1
1000000000

10
22 75 26 45 72 81 47 29 97 2
*/

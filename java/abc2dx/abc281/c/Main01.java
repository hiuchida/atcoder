import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long t=sc.nextLong();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		while (true) {
			for (int i=0; i<n; i++) {
				int a=ary[i];
				if (t-a<0) {
					System.out.println((i+1)+" "+t);
					System.exit(0);
				}
				t-=a;
			}
		}
	}
}
/*
3 600
180 240 120

3 281
94 94 94

10 5678912340
1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000
*/

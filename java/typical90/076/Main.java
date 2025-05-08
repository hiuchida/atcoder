import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		long s=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			s+=ary[i];
		}
		if (s%10!=0) {
			System.out.println("No");
			System.exit(0);
		}
		long[] sum=new long[2*n+1];
		for (int i=0; i<n; i++) {
			sum[i+1]=sum[i]+ary[i];
		}
		for (int i=0; i<n; i++) {
			sum[n+i+1]=sum[n+i]+ary[i];
		}
//		System.out.println(Arrays.toString(sum)+" "+s);
		for (int i=0; i<2*n; i++) {
			long x=sum[i];
			long y=s/10;
			long z=x+y;
			int idx=Arrays.binarySearch(sum, z);
//			System.out.println(i+" "+x+" "+y+" "+z+" "+idx);
			if (idx>=0) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
10
1 1 1 1 1 1 1 1 1 1

3
1 1 1

3
1 18 1

4
1 9 1 9
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum+=ary[i];
		}
		sum*=(n-1);
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int i=0;
		int j=n-1;
		while (i<n-1) {
			while (i>=j) j++;
			while (i<j && ary[i]+ary[j]>=10000*10000) j--;
//			System.out.println(i+" "+j+" "+(n-1-j));
			sum-=(n-1-j)*10000L*10000;
			i++;
		}
		System.out.println(sum);
	}
}
/*
3
3 50000001 50000002

5
1 3 99999999 99999994 1000000
*/

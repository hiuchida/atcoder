import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum+=ary[i];
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary)+" "+sum);
		sum-=ary[n-1];
		if (sum>ary[n-1]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4
3 8 5 1

4
3 8 4 1

10
1 8 10 5 8 12 34 100 11 3
*/

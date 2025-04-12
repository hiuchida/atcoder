import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		int s1=aa[0]+ab[0];
		int s2=Math.max(aa[0], ab[1]);
		int s3=Math.max(aa[1], ab[0]);
		int ans=Math.min(Math.min(s1, s2), s3);
		System.out.println(ans);
	}
}
/*
3
8 5
4 4
7 9

3
11 7
3 2
6 7
*/

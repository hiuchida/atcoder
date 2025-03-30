import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 3;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		int ans=0;
		for (int i=1; i<n; i++) {
			ans+=ary[i]-ary[i-1];
		}
		System.out.println(ans);
	}
}
/*
1 6 3

11 5 5

100 100 100
*/

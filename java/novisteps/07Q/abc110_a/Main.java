import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=3;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		int ans=ary[2]*10+ary[1]+ary[0];
		System.out.println(ans);
	}
}
/*
1 5 2

9 9 9

6 6 7
*/

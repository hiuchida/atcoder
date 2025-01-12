import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt();
		}
		int ans = 1;
		for (int d=1; d<n; d++) {
			for (int i=0; i<n-1; i++) {
				int a=1;
				for (int j=i+d; j<n; j+=d) {
					if (ary[i] == ary[j]) a++;
					else break;
				}
				ans = Math.max(ans, a);
			}
		}
		System.out.println(ans);
	}
}

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
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) {
				if (ary[i] != ary[j]) continue;
				int a=2;
				for (int k=j+(j-i); k<n; k+=(j-i)) {
					if (ary[j] == ary[k]) a++;
					else break;
				}
				ans = Math.max(ans, a);
			}
		}
		System.out.println(ans);
	}
}

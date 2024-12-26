import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arya = new int[n];
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			arya[i] = a;
		}
		int[] aryb = new int[n];
		for (int i = 0; i < n-1; i++) {
			int b = sc.nextInt();
			aryb[i] = b;
		}
		Arrays.sort(arya);
		Arrays.sort(aryb);
//		System.out.println(Arrays.toString(arya));
//		System.out.println(Arrays.toString(aryb));
		int ans = -1;
		int j=n-1;
		for (int i=n-1; i>=0; i--) {
			if (arya[i] > aryb[j]) {
				if (ans < 0) {
					ans = arya[i];
					j++;
				} else {
					ans = -1;
					break;
				}
			}
			j--;
		}
		System.out.println(ans);
	}
}

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		int ans=1;
		int rest=k;
		for (int i=0; i<n; i++) {
			if (rest<ary[i]) {
				ans++;
				rest=k;
				i--;
			} else {
				rest-=ary[i];
			}
		}
		System.out.println(ans);
	}
}
/*
7 6
2 5 1 4 1 2 3

7 10
1 10 1 10 1 10 1

15 100
73 8 55 26 97 48 37 47 35 55 5 17 62 2 60
*/

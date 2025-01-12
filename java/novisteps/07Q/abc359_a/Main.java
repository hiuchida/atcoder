import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans = 0;
		for (int i=0; i<n; i++) {
			String s = sc.next();
			if ("Takahashi".equals(s)) ans++;
		}
		System.out.println(ans);
	}
}
/*
3
Aoki
Takahashi
Takahashi

2
Aoki
Aoki

20
Aoki
Takahashi
Takahashi
Aoki
Aoki
Aoki
Aoki
Takahashi
Aoki
Aoki
Aoki
Takahashi
Takahashi
Aoki
Takahashi
Aoki
Aoki
Aoki
Aoki
Takahashi
*/

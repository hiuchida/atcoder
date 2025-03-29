import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int ans=2;
		if (h==1) ans--;
		if (w==1) ans--;
		if (1<r && r<h) ans++;
		if (1<c && c<w) ans++;
		System.out.println(ans);
	}
}
/*
3 4
2 2

3 4
1 3

3 4
3 4

1 10
1 5

8 1
8 1

1 1
1 1
*/

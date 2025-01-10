import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int ans=Math.min(a, b)+Math.min(c, d);
		System.out.println(ans);
	}
}
/*
600
300
220
420

555
555
400
200

549
817
715
603
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int ab=a*b;
		int ac=a*c;
		int bc=b*c;
		int ans=2*(ab+ac+bc);
		System.out.println(ans);
	}
}
/*
2 3 4

3 4 2

100 100 100

1 1 1
*/

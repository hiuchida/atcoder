import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int e=k/2;
		int o=k/2;
		if (k%2==1) {
			o++;
		}
		int ans=e*o;
		System.out.println(ans);
	}
}
/*
3

6

11

50
*/

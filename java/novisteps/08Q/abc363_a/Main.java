import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int ans=0;
		if (r<100) ans=100-r;
		else if (r<200) ans=200-r;
		else ans=300-r;
		System.out.println(ans);
	}
}
/*
123

250
*/

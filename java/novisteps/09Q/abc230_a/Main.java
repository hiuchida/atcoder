import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n>=42) n++;
		String ans=String.format("AGC%03d", n);
		System.out.println(ans);
	}
}
/*
42

19

1

50
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int hh=21;
		hh+=k/60;
		int mm=k%60;
		System.out.println(String.format("%d:%02d", hh, mm));
	}
}
/*
63

45

100
*/

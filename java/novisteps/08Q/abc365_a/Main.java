import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		int ans=365;
		if (y%4>0) ans=365;
		else if (y%100>0) ans=366;
		else if (y%400>0) ans=365;
		else ans=366;
		System.out.println(ans);
	}
}
/*
2023

1992

1800

1600
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		while (true) {
			if (y%4==2) {
				System.out.println(y);
				System.exit(0);
			}
			y++;
		}
	}
}
/*
2022

2023

3000
*/

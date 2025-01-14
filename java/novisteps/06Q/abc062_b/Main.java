import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		for (int i=0; i<w+2; i++) {
			System.out.print('#');
		}
		System.out.println();
		for (int i=0; i<h; i++) {
			String s = sc.next();
			System.out.print('#');
			System.out.print(s);
			System.out.print('#');
			System.out.println();
		}
		for (int i=0; i<w+2; i++) {
			System.out.print('#');
		}
		System.out.println();
	}
}
/*
2 3
abc
arc

1 1
z
*/

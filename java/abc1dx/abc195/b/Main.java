import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int w=1000*sc.nextInt();
		int wa=w/a;
		int wb=w/b;
		if (b*wb!=w) wb++;
		if (wa<wb) {
			System.out.println("UNSATISFIABLE");
			System.exit(0);
		}
		int min=wb;
		int max=wa;
		System.out.println(min+" "+max);
	}
}
/*
100 200 2

120 150 2

300 333 1
*/

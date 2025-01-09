import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = n / 100;
		int b = n / 10 % 10;
		int c = n % 10;
		int bca = b * 100 + c * 10 + a;
		int cab = c * 100 + a * 10 + b;
		System.out.println(bca + " " + cab);
	}
}
/*
379

919
*/

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String[] tbl={ "Sunny","Cloudy","Rainy" };
		String s=sc.next();
		for (int i=0; i<tbl.length; i++) {
			if (tbl[i].equals(s)) {
				i=(i+1)%tbl.length;
				System.out.println(tbl[i]);
				System.exit(0);
			}
		}
	}
}
/*
Sunny

Rainy
*/

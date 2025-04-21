import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int s=sc.nextInt();
		int lt=s/100;
		int rt=s%100;
		boolean blt=(0<lt && lt<=12);
		boolean brt=(0<rt && rt<=12);
		if (blt && brt) System.out.println("AMBIGUOUS");
		else if (brt) System.out.println("YYMM");
		else if (blt) System.out.println("MMYY");
		else System.out.println("NA");
	}
}
/*
1905

0112

1700
*/

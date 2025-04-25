import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i=1; i<=10; i++) {
			int rc=query(sc, i+1);
			if (rc==1) {
				System.out.println("! "+i);
				System.exit(0);
			}
			rc=query(sc, n-i);
			if (rc==0) {
				System.out.println("! "+(n-i));
				System.exit(0);
			}
		}
	}
	static int query(Scanner sc, int n) {
		System.out.println("? "+n);
		System.out.flush();
		int rc=sc.nextInt();
		return rc;
	}
}
/*



*/

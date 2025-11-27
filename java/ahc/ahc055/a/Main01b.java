import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
		for (int b=0; b<n; b++) {
			hand(b);
			if (b<n-1) {
				weapon(b, b+1);
			}
		}
	}
	static void hand(int b) {
		while (ah[b]>0) {
			System.out.println("-1 "+b);
			ah[b]--;
		}
	}
	static void weapon(int w, int b) {
		while (ah[b]>0 && ac[w]>0) {
			System.out.println(w+" "+b);
			ah[b]-=aa[w][b];
			ac[w]--;
		}
	}
	static int n;
	static int[] ah;
	static int[] ac;
	static int[][] aa;
	static void init(Scanner sc) {
		n=sc.nextInt();
		ah=new int[n];
		for (int i=0; i<n; i++) {
			ah[i]=sc.nextInt();
		}
		ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		aa=new int[n][n];
		for (int w=0; w<n; w++) {
			for (int b=0; b<n; b++) {
				aa[w][b]=sc.nextInt();
			}
		}
	}
}
/*



*/

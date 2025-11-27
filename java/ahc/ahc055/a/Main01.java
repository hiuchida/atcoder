import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ah=new int[n];
		for (int i=0; i<n; i++) {
			ah[i]=sc.nextInt();
		}
		int[] ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		int[][]aa=new int[n][n];
		for (int w=0; w<n; w++) {
			for (int b=0; b<n; b++) {
				aa[w][b]=sc.nextInt();
			}
		}
		for (int b=0; b<n; b++) {
			for (int i=0; i<ah[b]; i++) {
				System.out.println("-1 "+b);
			}
			if (b<n-1) {
				while (ah[b+1]>0 && ac[b]>0) {
					System.out.println(b+" "+(b+1));
					ah[b+1]-=aa[b][b+1];
					ac[b]--;
				}
			}
		}
	}
}
/*



*/

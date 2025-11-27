import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int mini=srchminh();
		hand(mini);
		for (int i=0; i<n; i++) {
			int maxi=srchmaxc(mini);
			if (maxi<0) break;
			both(mini, maxi);
			mini=maxi;
		}
		for (int b=0; b<n; b++) {
			hand(b);
		}
	}
	static int srchminh() {
		int minh=Integer.MAX_VALUE;
		int mini=-1;
		for (int b=0; b<n; b++) {
			if (ah[b]<minh) {
				minh=ah[b];
				mini=b;
			}
		}
		return mini;
	}
	static int srchmaxc(int w) {
		int maxc=0;
		int maxi=-1;
		for (int b=0; b<n; b++) {
			if (b==w) continue;
			if (ah[b]<=0) continue;
			if (aa[w][b]>maxc) {
				maxc=aa[w][b];
				maxi=b;
			}
		}
		return maxi;
	}
	static void both(int w, int b) {
		weapon(w, b);
		hand(b);
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

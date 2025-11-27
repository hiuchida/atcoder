import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
		for (int i=0; i<n; i++) {
			int mini=srchminh();
			if (mini<0) break;
			hand(mini);
			Deque<Integer> que=new ArrayDeque<>();
			que.offer(mini);
			while (que.size()>0) {
				mini=que.poll();
				int next=srchmaxc(mini);
				if (next<0) break;
				weapon(mini, next);
				if (ah[next]<=0) que.offer(next);
				while (ac[mini]>0) {
					int maxi=srchmaxc(mini);
					if (maxi<0) break;
					weapon(mini, maxi);
					if (ah[maxi]<=0) que.offer(maxi);
				}
			}
		}
//		System.out.println("---");
		for (int b=0; b<n; b++) {
			hand(b);
		}
	}
	static int srchminh() {
		int minh=Integer.MAX_VALUE;
		int mini=-1;
		for (int b=0; b<n; b++) {
			if (ah[b]<=0) continue;
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
		if (ac[w]<=0) return maxi;
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
	static int srchmint(int w) {
		int mint=Integer.MAX_VALUE;
		int mini=-1;
		if (ac[w]<=0) return mini;
		for (int b=0; b<n; b++) {
			if (b==w) continue;
			if (ah[b]<=0) continue;
			int t=Math.min(ac[w], ah[b]/aa[w][b]);
			t+=Math.max(0, ah[b]-aa[w][b]*t);
			if (t<mint) {
				mint=t;
				mini=b;
			}
		}
		return mini;
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

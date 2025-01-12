import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		String a = sc.nextLine();
		String b = sc.nextLine();
		String[] arya = a.split(" ");
		int siz = 200*1000+1;
		int[] aryi = new int[siz];
		int mi = aryi.length;
		for (int i = 0; i < arya.length; i++) {
			int v = Integer.parseInt(arya[i]);
			while (v < mi) {
				aryi[--mi] = i+1;
			}
		}
//		System.out.println(Arrays.toString(aryi));
		String[] aryb = b.split(" ");
		for (int i = 0; i < aryb.length; i++) {
			int v = Integer.parseInt(aryb[i]);
			int ii = aryi[v];
			if (ii > 0) {
				System.out.println(ii);
			} else {
				System.out.println("-1");
			}
		}
	}
}

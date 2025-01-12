import java.util.*;
public class Main {
	static class Pair implements Comparable<Pair>{
		int v;
		int i;
		Pair(int v, int i) {
			this.v = v;
			this.i = i;
		}
		@Override
		public int compareTo(Main.Pair that) {
			return Integer.compare(this.v, that.v);
		}
		@Override
		public String toString() {
			return "Pair [v=" + v + ", i=" + i + "]";
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		String a = sc.nextLine();
		String b = sc.nextLine();
		String[] arya = a.split(" ");
		List<Pair> list = new ArrayList<>();
		int mi = Integer.MAX_VALUE;
		for (int i = 0; i < arya.length; i++) {
			int v = Integer.parseInt(arya[i]);
			if (v < mi) {
				list.add(new Pair(v, i + 1));
				mi = v;
			}
		}
		Collections.sort(list);
//		System.out.println(list);
		String[] aryb = b.split(" ");
		for (int i = 0; i < aryb.length; i++) {
			int v = Integer.parseInt(aryb[i]);
			Pair p = new Pair(v, 0);
			int f = Collections.binarySearch(list, p);
//			System.out.println("v=" + v + ", f="+f);
			if (f == -1) {
				System.out.println("-1");
			} else {
				if (f < 0) {
					f = -(f + 1) - 1;
				}
				System.out.println(list.get(f).i);
			}
		}
	}
}

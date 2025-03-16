import java.util.*;
public class Main {
	static class Info implements Comparable<Info>{
		String id;
		int s;
		Info(String id, int s) {
			this.id = id;
			this.s = s;
		}
		@Override
		public int compareTo(Main.Info that) {
			int cmp = Integer.compare(this.s, that.s);
			if (cmp != 0) {
				return -cmp;
			}
			return this.id.compareTo(that.id);
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[5];
		for (int i = 0; i < ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		List<Info> list = new ArrayList<>();
		for (int v = 1; v < 32; v++) {
			int s = 0;
			String id = "";
			for (int i = 0; i < ary.length; i++) {
				int mask = 16 >> i;
				if ((v & mask) > 0) {
					s += ary[i];
					id += "ABCDE".charAt(i);
				}
			}
			Info info = new Info(id, s);
			list.add(info);
		}
		Collections.sort(list);
		for (Info info : list) {
			System.out.println(info.id);
		}
	}
}

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
			if (this.s != that.s) {
				return -Integer.compare(this.s, that.s);
			}
			return this.id.compareTo(that.id);
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int e = sc.nextInt();
		List<Info> list = new ArrayList<>();
		for (int i = 1; i < 32; i++) {
			int s = 0;
			String id = "";
			if ((i & 16) > 0) {
				s += a;
				id += "A";
			}
			if ((i & 8) > 0) {
				s += b;
				id += "B";
			}
			if ((i & 4) > 0) {
				s += c;
				id += "C";
			}
			if ((i & 2) > 0) {
				s += d;
				id += "D";
			}
			if ((i & 1) > 0) {
				s += e;
				id += "E";
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

import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans=new Answer();
		ans.solve();
		ans.print();
		if (!RELEASE) System.err.println("Score:"+ans.score());
	}
	class Shop {
		Set<String> set=new HashSet<>();
	}
	class Tree {
		boolean bRed=false;
	}
	class Answer {
		Shop[] as;
		Tree[] at;
		List<String> ls=new ArrayList<>();
		void solve() {
			as=new Shop[k];
			at=new Tree[n-k];
			for (int i=0; i<k; i++) {
				as[i]=new Shop();
			}
			for (int i=0; i<n-k; i++) {
				at[i]=new Tree();
			}
			int pos=0;
			int prev=-1;
			String cup="";
			for (int i=0; i<t; i++) {
				int nxt=0;
				while (true) {
					int c=cnt.get(pos).size();
					int j=rand.nextInt(c);
					nxt=cnt.get(pos).get(j);
					if (prev==nxt) continue;
					break;
				}
				if (pos>=k) {
					if (!at[pos-k].bRed) {
						if (rand.nextInt(100)<10) {
							at[pos-k].bRed=true;
							nxt=-1;
						}
					}
				}
				ls.add(""+nxt);
				if (nxt>=0) {
					if (nxt<k) {
						as[nxt].set.add(cup);
						cup="";
					} else {
						String col="W";
						if (at[nxt-k].bRed) col="R";
						cup+=col;
					}
					prev=pos;
					pos=nxt;
				}
			}
		}
		void print() {
			for (String s : ls) {
				System.out.println(s);
			}
		}
		int score() {
			int score=0;
			for (int i=0; i<k; i++) {
				score+=as[i].set.size();
			}
			return score;
		}
	}
	int n;
	int m;
	int k;
	int t;
	Counter cnt=new Counter();
	void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		t=sc.nextInt();
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			cnt.add(a, b);
			cnt.add(b, a);
		}
//		System.out.println(cnt);
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Counter { //Counter_int_listint20250413
		Map<Integer, List<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Integer> get(int k) {
			List<Integer> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*

*/

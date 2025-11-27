import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(apr));
//		System.out.println(lnk);
//		System.out.println(Arrays.toString(apt));
		State s0=new State();
		while (!s0.isDone()) {
			List<State> ls=new ArrayList<>();
			for (int i=0; i<n; i++) {
				State s1=new State(s0);
				s1.bfs(i);
				ls.add(s1);
			}
//			System.out.println(ls);
			int maxscore=-1;
			int maxi=-1;
			for (int i=0; i<n; i++) {
				State s1=ls.get(i);
				if (maxscore<s1.score) {
					maxscore=s1.score;
					maxi=i;
				}
			}
			s0=ls.get(maxi);
		}
		Answer a0=new Answer(s0.score, s0.ans, s0.li);
        Random rand=new Random(42);
		long start=System.currentTimeMillis();
		long end;
        final int time_limit=1700;
        final double start_temperature=2e2;
        final double end_temperature=1e0;
        double current_temperature=start_temperature;
        int iteration=0;
		Answer an=a0;
		an.score++;
        System.err.println("iteration: "+iteration+", score: "+an.score);
//		while (true) {
//			end=System.currentTimeMillis();
//			if (end-start>=time_limit) break;
//			iteration++;
//			int r1=rand.nextInt(an.li.size());
//			Answer a1=new State().resolve(an, r1);
//			int score2=a1.score;
//			if (an.score<score2 || rand.nextFloat()<=Math.exp((score2-an.score)/current_temperature)) {
//				an=a1;
//			}
//            if (iteration % 1000 == 0) {
//                System.err.println("iteration: "+iteration+", score: "+an.score);
//            }
//            double progress=(double)(end-start)/time_limit;
//            current_temperature=Math.pow(start_temperature, 1.0-progress)*Math.pow(end_temperature, progress);
//		}
		end=System.currentTimeMillis();
        System.err.println("--- Result ---");
        System.err.println("elaps:     "+(end-start)+"ms");
        System.err.println("iteration: "+iteration);
        System.err.println("score:     "+an.score);
		an.print();
	}
	static class State {
		int score=0;
		int[] ans=new int[n];
		List<Integer> li=new ArrayList<>();
		boolean[] flag=new boolean[n];
		State() {
		}
		State(State that) {
			this.score=that.score;
			this.ans=Arrays.copyOf(that.ans, that.ans.length);
			this.li=new ArrayList<>(that.li);
			this.flag=Arrays.copyOf(that.flag, that.flag.length);
		}
		boolean isDone() {
			for (int i=0; i<n; i++) {
				if (!flag[i]) return false;
			}
			return true;
		}
		Answer resolve(Answer a0, int r1) {
			int skip=a0.li.get(r1);
			while (true) {
				int mini=minv(skip);
				if (mini<0) break;
				li.add(mini);
				ans[mini]=-1;
				dfs(mini, 0);
			}
			fill();
			return new Answer(score, ans, li);
		}
		Answer solve() {
			for (int i=0; i<n; i++) {
				bfs(i);
			}
			fill();
			return new Answer(score, ans, li);
		}
		Answer solve0() {
			while (true) {
				int mini=minv();
				if (mini<0) break;
				li.add(mini);
				ans[mini]=-1;
				dfs(mini, 0);
			}
			fill();
			return new Answer(score, ans, li);
		}
		void fill() {
			for (int i=0; i<n; i++) {
				if (!flag[i]) {
					ans[i]=-1;
					score+=aa[i];
				}
			}
		}
		void dfs(int i, int depth) {
			if (depth>h) return;
			if (flag[i]) return;
			score+=(depth+1)*aa[i];
//			System.err.println(i+" "+((depth+1)*aa[i]));
			flag[i]=true;
			for (int nxt : lnk.get(i)) {
				if (depth+1>h) continue;
				if (flag[nxt]) continue;
				ans[nxt]=i;
				dfs(nxt, depth+1);
			}
		}
		void bfs(int root) {
			if (flag[root]) return;
			flag[root]=true;
			ans[root]=-1;
			Deque<Pair> que=new ArrayDeque<>();
			que.add(new Pair(root, 0));
			while (que.size()>0) {
				Pair cur=que.poll();
				score+=(cur.ed+1)*aa[cur.st];
				for (int nxt : lnk.get(cur.st)) {
					if (cur.ed+1>h) continue;
					if (flag[nxt]) continue;
					flag[nxt]=true;
					ans[nxt]=cur.st;
					que.add(new Pair(nxt, cur.ed+1));
				}
			}
		}
		int minv() {
			int minv=Integer.MAX_VALUE;
			int mini=-1;
			for (int i=0; i<n; i++) {
				if (flag[i]) continue;
				if (aa[i]<minv) {
					minv=aa[i];
					mini=i;
				}
			}
			return mini;
		}
		int minv(int skip) {
			int minv=Integer.MAX_VALUE;
			int mini=-1;
			for (int i=0; i<n; i++) {
				if (flag[i]) continue;
				if (i==skip) continue;
				if (aa[i]<minv) {
					minv=aa[i];
					mini=i;
				}
			}
			return mini;
		}
		@Override
		public String toString() {
			return ""+score;
		}
	}
	static class Answer {
		int score;
		int[] ans;
		List<Integer> li;
		Answer(int score, int[] ans, List<Integer> li) {
			this.score=score;
			this.ans=Arrays.copyOf(ans, ans.length);
			this.li=new ArrayList<>(li);
		}
		void print() {
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<n; i++) {
				if (i>0) sb.append(" ");
				sb.append(ans[i]);
			}
			System.out.println(sb.toString());
		}
	}
	static int n;
	static int m;
	static int h;
	static int[] aa;
	static Pair[] apr;
	static Counter lnk=new Counter();
	static Point[] apt;
	static void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		h=sc.nextInt();
		aa=new int[n];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			aa[i]=a;
		}
		apr=new Pair[m];
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			apr[i]=new Pair(u, v);
		}
		for (int i=0; i<m; i++) {
			lnk.add(apr[i].st, apr[i].ed);
			lnk.add(apr[i].ed, apr[i].st);
		}
		apt=new Point[n];
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			apt[i]=new Point(x, y);
		}
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
	static class Pair implements Comparable<Pair> {
		final int st;
		final int ed;
		Pair(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<Pair> newComparator1() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<Pair> newComparator2() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Pair that) {
			int cmp = Long.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Long.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Pair that = (Pair) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.x, that.x);
			if (cmp != 0) return cmp;
			return Integer.compare(this.y, that.y);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}
/*



*/

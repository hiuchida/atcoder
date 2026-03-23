import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	long start = System.currentTimeMillis();

	double erf0(double x) {
		// 誤差関数の近似
		double p = 0.3275911, a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027,
				a5 = 1.061405429;
		int sign = (x < 0) ? -1 : 1;
		x = Math.abs(x);
		double t = 1.0 / (1.0 + p * x);
		double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
		return sign * y;
	}

	double erf(double a) {
		return erf0(a);
	}

	double exp(double a) {
		return (a <= -20.8) ? 0 : Math.exp(a);
	}

	boolean isinf(double a) {
		return Double.isInfinite(a);
	}

	double log(double a) {
		return (a == 0.0) ? -999 : Math.log(a);
	}

	int max(int a, int b) {
		return Math.max(a, b);
	}

	double min(double a, double b) {
		return Math.min(a, b);
	}

	int min(int a, int b) {
		return Math.min(a, b);
	}

	long round(double a) {
		return Math.round(a);
	}

	double sqrt(double a) {
		return Math.sqrt(a);
	}

	class PairDouble {
		double first;
		double second;

		PairDouble(double f, double s) {
			this.first = f;
			this.second = s;
		}
	}

	class PairDoubleInt {
		double first;
		int second;

		PairDoubleInt(double f, int s) {
			this.first = f;
			this.second = s;
		}
	}

	static class PairInt {
		int first;
		int second;

		PairInt(int f, int s) {
			this.first = f;
			this.second = s;
		}

		static int compare(PairInt a, PairInt b) {
			int cmp = Integer.compare(a.first, b.first);
			if (cmp != 0)
				return cmp;
			cmp = Integer.compare(a.second, b.second);
			if (cmp != 0)
				return cmp;
			return 0;
		}
	}

	class BoolAry {
		boolean[] ary;

		BoolAry(int size, boolean def) {
			this.ary = new boolean[size];
			if (def != false)
				Arrays.fill(ary, def);
		}

		void and(int idx, boolean val) {
			ary[idx] = ary[idx] && val;
		}

		void set(int idx, boolean val) {
			ary[idx] = val;
		}

		boolean get(int idx) {
			return ary[idx];
		}

		int size() {
			return ary.length;
		}
	}

	class BoolAryAry {
		boolean[][] ary;

		BoolAryAry(int size1, int size2, boolean def) {
			ary = new boolean[size1][size2];
			if (def != false) {
				for (int i = 0; i < size1; i++) {
					Arrays.fill(ary[i], def);
				}
			}
		}

		void set(int i, int j, boolean val) {
			ary[i][j] = val;
		}

		boolean get(int i, int j) {
			return ary[i][j];
		}
	}

	class DoubleAry {
		double[] ary;

		DoubleAry(int size, double def) {
			this.ary = new double[size];
			if (def != 0.0)
				Arrays.fill(ary, def);
		}

		void add(int idx, double val) {
			ary[idx] += val;
		}

		void set(int idx, double val) {
			ary[idx] = val;
		}

		double get(int idx) {
			return ary[idx];
		}

		int size() {
			return ary.length;
		}
	}

//    class DoubleList {
//        ArrayList<Double> list;
//        DoubleList() {
//            this.list = new ArrayList<>();
//        }
//        void add(double val) {
//            list.add(val);
//        }
//        void add(int idx, double val) {
//            list.set(idx, list.get(idx) + val);
//        }
//        void set(int idx, double val) {
//            list.set(idx, val);
//        }
//        double get(int idx) {
//            return list.get(idx);
//        }
//        int size() {
//            return list.size();
//        }
//    }
	class DoubleAryList {
		ArrayList<DoubleAry> list;

		DoubleAryList() {
			this.list = new ArrayList<>();
		}

		void add(DoubleAry val) {
			list.add(val);
		}

		double get(int idx1, int idx2) {
			return list.get(idx1).get(idx2);
		}

		int size() {
			return list.size();
		}
	}

	class IntAry {
		int[] ary;

		IntAry(int size, int def) {
			this.ary = new int[size];
			if (def != 0)
				Arrays.fill(ary, def);
		}

		IntAry(IntAry that) {
			this.ary = that.ary.clone();
		}

		void add(int idx, int val) {
			ary[idx] += val;
		}

		void set(int idx, int val) {
			ary[idx] = val;
		}

		int get(int idx) {
			return ary[idx];
		}

		int size() {
			return ary.length;
		}
	}

	class IntList implements Iterable<Integer> {
		ArrayList<Integer> list;

		IntList() {
			this.list = new ArrayList<>();
		}

		void add(int val) {
			list.add(val);
		}

		void add(int idx, int val) {
			list.set(idx, list.get(idx) + val);
		}

		void set(int idx, int val) {
			list.set(idx, val);
		}

		int get(int idx) {
			return list.get(idx);
		}

		int size() {
			return list.size();
		}

		@Override
		public boolean equals(Object o) {
			IntList that = (IntList) o;
			return this.list.equals(that.list);
		}

		@Override
		public Iterator<Integer> iterator() {
			return list.iterator();
		}
	}

	class IntAryAry {
		int[][] ary;

		IntAryAry(int size1, int size2, int def) {
			this.ary = new int[size1][size2];
			if (def != 0) {
				for (int i = 0; i < size1; i++)
					Arrays.fill(ary[i], def);
			}
		}

		void set(int i, int j, int val) {
			ary[i][j] = val;
		}

		int get(int idx1, int idx2) {
			return ary[idx1][idx2];
		}

		int size() {
			return ary.length;
		}
	}

	class IntListAry {
		ArrayList<Integer>[] ary;

		@SuppressWarnings("unchecked")
		IntListAry(int size1) {
			this.ary = new ArrayList[size1];
			for (int i = 0; i < size1; i++) {
				ary[i] = new ArrayList<>();
			}
		}

		void add(int idx1, int val) {
			ary[idx1].add(val);
		}

		int get(int idx1, int idx2) {
			return ary[idx1].get(idx2);
		}
	}

	class IntListList implements Iterable<IntList> {
		ArrayList<IntList> list;

		IntListList() {
			this.list = new ArrayList<>();
		}

		void add(IntList val) {
			list.add(val);
		}

		void set(int i, int j, int val) {
			list.get(i).set(j, val);
		}

		int get(int idx1, int idx2) {
			return list.get(idx1).get(idx2);
		}

		int size() {
			return list.size();
		}

		boolean empty() {
			return list.isEmpty();
		}

		@Override
		public Iterator<IntList> iterator() {
			return list.iterator();
		}
	}

	class LongAry {
		long[] ary;

		LongAry(int size, long def) {
			this.ary = new long[size];
			if (def != 0)
				Arrays.fill(ary, def);
		}

		void set(int idx, long val) {
			ary[idx] = val;
		}

		long get(int idx) {
			return ary[idx];
		}
	}

	class PairDoubleList {
		ArrayList<PairDouble> list;

		PairDoubleList() {
			this.list = new ArrayList<>();
		}

		void add(PairDouble val) {
			list.add(val);
		}

		PairDouble get(int idx) {
			return list.get(idx);
		}

		int size() {
			return list.size();
		}

		void reverse() {
			Collections.reverse(list);
		}
	}

	class PairDoubleListAryAry {
		PairDoubleList[][] ary;

		PairDoubleListAryAry(int size1, int size2) {
			this.ary = new PairDoubleList[size1][size2];
			for (int i = 0; i < size1; i++) {
				for (int j = 0; j < size2; j++) {
					ary[i][j] = new PairDoubleList();
				}
			}
		}

		PairDoubleList get(int i, int j) {
			return ary[i][j];
		}
	}

	class PairDoubleIntList implements Iterable<PairDoubleInt> {
		ArrayList<PairDoubleInt> list;

		PairDoubleIntList() {
			this.list = new ArrayList<>();
		}

		void add(PairDoubleInt val) {
			list.add(val);
		}

		public void sort(Comparator<PairDoubleInt> c) {
			list.sort(c);
		}

		@Override
		public Iterator<PairDoubleInt> iterator() {
			return list.iterator();
		}
	}

	class PairIntList implements Comparable<PairIntList>, Iterable<PairInt> {
		ArrayList<PairInt> list;

		PairIntList() {
			this.list = new ArrayList<>();
		}

		void add(PairInt val) {
			list.add(val);
		}

		void clear() {
			list.clear();
		}

		PairInt get(int idx) {
			return list.get(idx);
		}

		int size() {
			return list.size();
		}

		public void sort(Comparator<PairInt> c) {
			list.sort(c);
		}

		@Override
		public int compareTo(PairIntList that) {
			for (int i = 0; i < min(this.size(), that.size()); i++) {
				PairInt pi1 = this.get(i);
				PairInt pi2 = that.get(i);
				int cmp = PairInt.compare(pi1, pi2);
				if (cmp != 0)
					return cmp;
			}
			int cmp = Integer.compare(this.size(), that.size());
			return cmp;
		}

		@Override
		public Iterator<PairInt> iterator() {
			return list.iterator();
		}
	}

	class PairIntListAryAry {
		PairIntList[][] ary;

		PairIntListAryAry(int size1, int size2) {
			this.ary = new PairIntList[size1][size2];
			for (int i = 0; i < size1; i++) {
				for (int j = 0; j < size2; j++) {
					ary[i][j] = new PairIntList();
				}
			}
		}

		PairIntList get(int i, int j) {
			return ary[i][j];
		}
	}

	class LongDoubleMap {
		HashMap<Long, Double> map = new HashMap<>();

		void put(long key, double val) {
			map.put(key, val);
		}

		double get(long key) {
			return map.get(key);
		}

		boolean count(long key) {
			return map.containsKey(key);
		}
	}

	/*
	 * 記事公開後、当日1位を超えたコード 00_complete.cppとの違い - 難しさを判定し、難しくないときは5倍探索した(影響高) -
	 * poolをグローバルに1個だけ置くようにした(影響低) - push_backの代わりにemplace_backを使った(影響低)
	 */
	double time_limit = 3.0;

	final int[][] DIJ = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	class Xorshift {
		Xorshift(long seed) {
			x_ = seed;
		}

		// [0, stop)
		long randrange(long stop) {
			assert (stop > 0);
			next();
			return x_ % stop;
		}

		// [start, stop)
		long randrange(long start, long stop) {
			assert (start < stop);
			next();
			return start + x_ % (stop - start);
		}

		// [a, b]
		long randint(long a, long b) {
			assert (a <= b);
			return randrange(a, b + 1);
		}

		// [0.0, 1.0]
		double random() {
			next();
			return (double) (x_) * (1.0 / (double) (Long.MAX_VALUE)); // java UINT64_MAXからINT64_MAX相当
		}

		// [a, b] or [b, a]
		double uniform(double a, double b) {
			return a + (b - a) * random();
		}

		long next() {
			x_ ^= x_ << 13;
			x_ ^= x_ >>> 17; // java 論理シフト
			x_ ^= x_ << 5;
			x_ &= max(); // java 64ビットを63ビット
			return x_;
		}

		boolean gen_bool(double p) {
			return random() < p;
		}

		long min() {
			return 0;
		}

		long max() {
			return Long.MAX_VALUE;
		} // java UINT64_MAXからINT64_MAX相当

		long x_;
	}

	Xorshift rng = new Xorshift(1);

	class OilLayoutList implements Iterable<OilLayout> {
		ArrayList<OilLayout> list;

		OilLayoutList() {
			this.list = new ArrayList<>();
		}

		OilLayoutList(int size) {
			this.list = new ArrayList<>(size);
		}

		void add(OilLayout val) {
			list.add(val);
		}

		void remove(int idx) {
			list.remove(idx);
		}

		OilLayout get(int idx) {
			return list.get(idx);
		}

		int size() {
			return list.size();
		}

		void resize(int size) {
			while (list.size() > size)
				list.remove(list.size() - 1);
		}

		void shuffle(Xorshift rnd) {
			int size = list.size();
			for (int i = size; i > 1; i--) {
				int p = i - 1;
				int q = (int) rnd.randrange(i);
				list.set(p, list.set(q, list.get(p)));
			}
		}

		void sort() {
			list.sort((a, b) -> Double.compare(b.ln_pR_if_x, a.ln_pR_if_x));
		}

		// プールの確率を正規化する
		void normalize() {
			double total = 0;
			for (final var layout : list) {
				total += layout.px_if_R;
			}
			for (var layout : list) {
				layout.px_if_R /= total;
			}
		}

		// プールを小さくして探索範囲を狭める
		void concat(int ITER) {
			double tmp1 = ITER * 0.01;
			double tmp2 = min(time_limit - get_time(), 1.0);

			int size = min(size(), max((int) (tmp1 * tmp2), (int) 2));
			// 尤度が低すぎる要素を削除
			while (size > 2 && get(0).px_if_R * 1e-4 > get(size - 1).px_if_R) {
				--size;
			}
			if (size > 0) {
				resize(size);
			}
		}

		// プールの油田配置の全座標の埋蔵量を計算する
		void set_volume() {
			for (var layout : pool) {
				layout.volume = input.get_volume(layout.top_lefts);
			}
		}

		@Override
		public Iterator<OilLayout> iterator() {
			return list.iterator();
		}
	}

// 油田の配置についての情報
	class OilLayout {
		long hash;
		// 油田配置をこの配置xだと過程したとき
		// 今までのクエリ結果Rが得られる確率の対数.
		// 対数尤度ともいう。
		double ln_pR_if_x;
		// 今までのクエリの結果Rから計算した,この配置になる事後確率P(x|R)
		double px_if_R;
		IntAry top_lefts; // 油田の左上の座標
		IntAry volume; // ある位置の油の埋蔵量

		OilLayout(long h, double ln, double px, IntAry tl, IntAry v) {
			this.hash = h;
			this.ln_pR_if_x = ln;
			this.px_if_R = px;
			this.top_lefts = new IntAry(tl);
			this.volume = v;
		}
	}

	OilLayoutList pool;

	class OilShapeList {
		ArrayList<OilShape> list;

		OilShapeList(int size) {
			this.list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				list.add(new OilShape());
			}
		}

		OilShape get(int idx) {
			return list.get(idx);
		}

		void sort() {
			Collections.sort(list);
		}
	}

// 油田の形についての情報
	class OilShape implements Comparable<OilShape> {
		int max_i, max_j; // 油田が収まる正方形の大きさ
		IntList coordinate_ids; // 座標(i,j)の組を1変数で表したもの
		PairIntList coordinates; // 座標(i,j)の組
		// 座標(i,j)の組をマスクしたもの
		// 島の大きさN<=20なので、20*20のビットセットで表現できる
		BitSet mask = new BitSet(20 * 20);

		@Override
		public int compareTo(OilShape that) {
			return this.coordinates.compareTo(that.coordinates);
		}
	}

	class Input {
		int n; // 島の大きさ 10<=N<=20
		int n2; // n*n
		int m; // 油田の数 2<=M<=10
		double eps; // 占い結果に用いるエラーパラメータ 0.01<=eps<=0.2
		OilShapeList oils; // 油田の形に関する情報 .size()==M
		int total; // 島全体の油田の埋蔵量の合計
		double difficulty;

		void set_difficult() {
			difficulty = n2 * m * m * eps;
		}

		boolean is_difficult() {
			// ローカルの計算結果100個で
			// n m epsilonの3つのパラメータを適当にかけたりいろいろしてたら
			// n*n*m*m*epsilonが2000を超えるシードでちょくちょく時間orクエリが足らなくなってた
			// なので、これを超えるシードは難しいと判断する
			return difficulty > 2000;
		}

		// 油田の左上の座標を受け取り、埋蔵量が1以上のマスの集合をbitsetで返す
		BitSet get_positives(final IntAry top_lefts) {
			BitSet positives = new BitSet(20 * 20);
			for (int oil_id = 0; oil_id < m; ++oil_id) {
				int pij = top_lefts.get(oil_id);
				for (int ij : oils.get(oil_id).coordinate_ids) {
					positives.set(ij + pij);
				}
			}
			return positives;
		}

		// M個の油田の左上の座標を受け取り、その位置の油の埋蔵量を返す
		IntAry get_volume(final IntAry top_lefts) {
			IntAry volume = new IntAry(n2, 0);
			for (int oil_id = 0; oil_id < top_lefts.size(); ++oil_id) {
				int pij = top_lefts.get(oil_id);
				for (int ij : oils.get(oil_id).coordinate_ids) {
					volume.add(ij + pij, 1);
				}
			}
			return volume;
		}
	}

	Input input;

// プログラムが始まってからの時間を計測する
	double get_time() {
		// startをstaticにすることで、
		// 2回目以降のget_time()の呼び出しでも
		// プログラムが始まってからの時間を計測できる
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0; // java ミリ秒単位の整数を秒単位の小数
	}

	Input read_input() {
		Input input = new Input();
		input.n = sc.nextInt();
		input.m = sc.nextInt();
		input.eps = sc.nextDouble();
		input.n2 = input.n * input.n;
		input.oils = new OilShapeList(input.m);

		for (int oil_id = 0; oil_id < input.m; ++oil_id) {
			int t_size;
			t_size = sc.nextInt();
			input.oils.get(oil_id).coordinates = new PairIntList();
			for (int i = 0; i < t_size; ++i) {
				int x, y;
				x = sc.nextInt();
				y = sc.nextInt();
				input.oils.get(oil_id).coordinates.add(new PairInt(x, y));
			}
		}
		input.total = 0;
		for (int oil_id = 0; oil_id < input.m; ++oil_id) {
			input.total += input.oils.get(oil_id).coordinates.size();
		}

		// 同じ図形かの判定を行うためにポリオミノをソートしてく
		input.oils.sort();

		for (int oil_id = 0; oil_id < input.m; ++oil_id) {
			var oil = input.oils.get(oil_id);
			oil.max_i = 0;
			oil.max_j = 0;
			for (PairInt pi : oil.coordinates) {
				int i = pi.first;
				int j = pi.second;
				oil.max_i = max(oil.max_i, i);
				oil.max_j = max(oil.max_j, j);
			}
			oil.coordinate_ids = new IntList();
			for (int i = 0; i < oil.coordinates.size(); ++i) {
				oil.coordinate_ids.add(oil.coordinates.get(i).first * input.n + oil.coordinates.get(i).second);
			}
			oil.mask.clear();
			for (int ij : oil.coordinate_ids) {
				oil.mask.set(ij, true);
			}
		}

		return input;
	}

	final double SMALL_VALUE = 1e-6; // すごく小さい値

	class OilStateList implements Iterable<OilState> {
		ArrayList<OilState> list;

		OilStateList(int size) {
			this.list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				list.add(new OilState());
			}
		}

		OilState get(int idx) {
			return list.get(idx);
		}

		@Override
		public Iterator<OilState> iterator() {
			return list.iterator();
		}
	}

// 油田1個分の状態
	class OilState {
		// top_left_query_volumes[top_left][q]は、
		// この油田の左上座標がtop_leftにあるとき、
		// q番目のクエリで占った座標集合の埋蔵量の合計を示している
		IntListAry top_left_query_volumes;
		// この油田の左上座標ごとのzobrist hash
		LongAry hashes;

		OilState() {
			top_left_query_volumes = new IntListAry(input.n2);
			hashes = new LongAry(input.n2, 0);
		}
	}

	class State {
		OilStateList oil_states; // 油田の状態についてのリスト: M個
		IntAry top_lefts; // 油田の左上の座標. oil_satesに含めたかったが、OilLayoutにコピーして使うので別で持つ: M個
		IntAry volumes; // ある位置の油の埋蔵量: N*N個
		IntList query_volumes; // q番目のクエリで占った座標集合の埋蔵量の合計
		long hash;

		// 全ての油田が0,0にあるときの状態を初期状態とする
		State() {
			this.oil_states = new OilStateList(input.m);
			top_lefts = new IntAry(input.m, 0);
			volumes = null;
			query_volumes = new IntList();
			for (int oil_id = 0; oil_id < input.m; ++oil_id) {
				var oil_state = oil_states.get(oil_id);
				if (oil_id > 0
						&& input.oils.get(oil_id - 1).coordinate_ids.equals(input.oils.get(oil_id).coordinate_ids)) {
					oil_state.hashes = oil_states.get(oil_id - 1).hashes;
				} else {
					for (int ij = 0; ij < input.n2; ++ij) {
						oil_state.hashes.set(ij, rng.next());
					}
				}
			}
			// zobrist hashを初期化
			hash = 0;
			for (var oil_state : oil_states) {
				hash += oil_state.hashes.get(0);
			}
		}

		// 油田oil_idをnew_top_leftに移動する
		// 移動自体はtop_leftsの更新だが、
		// 移動に伴い、hash,query_volumes,volumesを更新する
		void move_to(int oil_id, int new_top_left) {
			var oil_state = oil_states.get(oil_id);
			hash += -oil_state.hashes.get(top_lefts.get(oil_id)) + oil_state.hashes.get(new_top_left);
			for (int q = 0; q < query_volumes.size(); ++q) {
				query_volumes.add(q, oil_state.top_left_query_volumes.get(new_top_left, q)
						- oil_state.top_left_query_volumes.get(top_lefts.get(oil_id), q));
			}
			if (volumes != null) {
				for (int ij : input.oils.get(oil_id).coordinate_ids) {
					volumes.add(ij + top_lefts.get(oil_id), -1);
					volumes.add(ij + new_top_left, 1);
				}
			}
			top_lefts.set(oil_id, new_top_left);
		}

		// 占いクエリを投げた後、今回占った座標集合に含まれる埋蔵量を記録する
		void add_query(final IntList query_coordinates) {
			BoolAry in_query = new BoolAry(input.n2, false);
			for (int ij : query_coordinates) {
				in_query.set(ij, true);
			}
			for (int oil_id = 0; oil_id < input.m; ++oil_id) {
				var oil = input.oils.get(oil_id);
				var oil_state = oil_states.get(oil_id);
				for (int di = 0; di < input.n - oil.max_i; ++di) {
					for (int dj = 0; dj < input.n - oil.max_j; ++dj) {
						int top_left = di * input.n + dj;
						int c = 0;
						for (int ij : input.oils.get(oil_id).coordinate_ids) {
							if (in_query.get(top_left + ij)) {
								c += 1;
							}
						}
						oil_state.top_left_query_volumes.add(top_left, c);
					}
				}
			}
			IntAry volume = input.get_volume(top_lefts);
			int c = 0;
			for (int ij : query_coordinates) {
				c += volume.get(ij);
			}
			query_volumes.add(c);
		}
	}

	State state;

	class QueryHistoryList {
		ArrayList<QueryHistory> list;

		QueryHistoryList() {
			list = new ArrayList<>();
		}

		void add(QueryHistory val) {
			list.add(val);
		}

		QueryHistory get(int idx) {
			return list.get(idx);
		}

		int size() {
			return list.size();
		}
	}

	class QueryHistory {
		IntList query_coordinates;
		int ret;

		QueryHistory(IntList q, int r) {
			this.query_coordinates = q;
			this.ret = r;
		}
	}

	class Sim {
		int n, n2, m, total;
		double eps;
		// 過去の占いの(油田配置、占い結果)の集合
		QueryHistoryList queries;
		// 既に油田配置を答えるクエリを投げて失敗した油田配置の集合
		IntListList failed;
		// クエリサイズk、埋蔵量総量Sに対して、
		// pr_if_xがもつrの値の下限
		IntAryAry pr_if_x_lb;
		// 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
		// 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
		// クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
		// 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
		// pr_if_x[k][S][r-lb] = (prob, log(prob))
		PairDoubleListAryAry pr_if_x;
		// クエリごとにあり得る埋蔵量総量Sごとに
		// 埋蔵量Sのときにそのクエリで得られた結果になる確率P(r|S)の対数を記録
		DoubleAryList ln_pr_if_s_query;
		// 残りクエリ回数. 2*N*N回までクエリを投げられる
		int rem;
		double cost;

		Sim() {
			this.n = input.n;
			this.n2 = input.n2;
			this.m = input.m;
			this.total = input.total;
			this.eps = input.eps;
			this.rem = input.n * input.n * 2;
			this.queries = new QueryHistoryList();
			this.failed = new IntListList();
			// クエリサイズk、埋蔵量総量S、クエリの結果rに対する尤度は事前に計算しておく
			this.pr_if_x_lb = new IntAryAry(n * n + 1, total + 1, 0);
			this.pr_if_x = new PairDoubleListAryAry(n * n + 1, total + 1);
			this.ln_pr_if_s_query = new DoubleAryList();
			for (int k = 1; k <= n * n; ++k) {
				for (int S = 0; S <= total; ++S) {
					// muとsigmaは問題文の計算式をそのまま使うだけで求まる
					double mu = ((double) k - (double) S) * eps + (double) S * (1.0 - eps);
					double sigma = sqrt(k * eps * (1.0 - eps));
					// 尤度が小さすぎるrがどこかわからないため、
					// 尤度が最も高いmuから順に尤度を求め、
					// 尤度が小さすぎたタイミングで止める
					for (int r = (int) (round(mu)); r >= 0; --r) {
						double prob = likelihood(mu, sigma, r);
						if (prob < SMALL_VALUE) {
							pr_if_x_lb.set(k, S, r + 1);
							break;
						}
						pr_if_x.get(k, S).add(new PairDouble(prob, log(prob)));
					}
					// rの値について降順になっているため、昇順になおす
					pr_if_x.get(k, S).reverse();
					// muを基準に対称なので、muより大きいrについても同様に計算する
					for (int r = (int) (round(mu)) + 1;; ++r) {
						double prob = likelihood(mu, sigma, r);
						if (prob < SMALL_VALUE) {
							break;
						}
						pr_if_x.get(k, S).add(new PairDouble(prob, log(prob)));
					}
				}
			}
		}

		// 油田配置をあてるクエリを投げる
		// 失敗した場合は失敗した座標郡をfailedに記録する
		boolean ans(final IntList T) {
			if (rem == 0) {
				System.err.println("!log giveup because rem==0");
				throw new RuntimeException();
			}
			--rem;
			System.out.print("a " + T.size() + " ");
			for (int ij : T) {
				System.out.print(ij / n + " " + ij % n + " ");
			}
			System.out.println();
			int ret;
			ret = sc.nextInt();
			if (ret == 1) {
				return true;
			}
			cost++;
			failed.add(T);
			return false;
		}

		// 指定したマスの集合の埋蔵量を占う
		int query(final IntList query_coordinates) {
			if (rem == 0) {
				System.err.println("!log giveup because rem==0");
				throw new RuntimeException();
			}
			--rem;
			cost += 1.0 / sqrt(query_coordinates.size());
			System.out.print("q " + query_coordinates.size() + " ");
			for (int ij : query_coordinates) {
				System.out.print(ij / n + " " + ij % n + " ");
			}
			System.out.println();
			int ret;
			ret = sc.nextInt();
			// クエリの結果を記録する
			queries.add(new QueryHistory(query_coordinates, ret));
			// 結果retが得られた.
			// 指定したマス集合の真の埋蔵量総量がわからないため、
			// あり得る埋蔵量総量全てについて、
			// 埋蔵量Sのときに結果がretになる確率P(ret|S)を求める
			DoubleAry ln_pr_if_s = new DoubleAry(total + 1, 0.0);
			int k = query_coordinates.size();
			for (int S = 0; S <= total; ++S) {
				double kS = (double) k - (double) S;
				double kSeps = kS * eps;
				double meps = 1.0 - eps;
				double mu = kSeps + S * meps;
				double sigma = sqrt(k * eps * meps);
				ln_pr_if_s.set(S, log(likelihood(mu, sigma, ret)));
			}

			// 尤度が非常に小さい場合、隣の値から計算しやすい値に更新する
			// これはあとで焼きなましの時にpoolに入れるかどうかの相対閾値として使う
			for (int i = 0; i < ln_pr_if_s.size() - 1; ++i) {
				if (!isinf(ln_pr_if_s.get(i)) && isinf(ln_pr_if_s.get(i + 1))) {
					ln_pr_if_s.set(i + 1, ln_pr_if_s.get(i) - 10.0);
				}
			}
			for (int i = ln_pr_if_s.size() - 1; i > 0; --i) {
				if (!isinf(ln_pr_if_s.get(i)) && isinf(ln_pr_if_s.get(i - 1))) {
					ln_pr_if_s.set(i - 1, ln_pr_if_s.get(i) - 10.0);
				}
			}

			ln_pr_if_s_query.add(ln_pr_if_s);
			return ret;
		}

		// 指定したマスの埋蔵量を取得する
		// 1マスなら正確な値がわかる
		int mine(int i, int j) {
			if (rem == 0) {
				System.err.println("!log giveup because rem==0");
				throw new RuntimeException();
			}
			--rem;
			cost++;
			System.out.println("q 1 " + i + " " + j);
			int ret;
			ret = sc.nextInt();
			return ret;
		}

		// 平均mean, 標準偏差std_devに従う正規分布において、
		// resが観測される確率を求める
		double likelihood(double mean, double std_dev, int res) {
			// 占い結果はμとσ^2に従う正規分布からサンプルされたxそのものではなく、
			// max(0,round(x))である。
			// res=rounnd(x)について考えると、
			// 占い結果がresになる確率はres-0.5<=x<res+0.5の範囲の確率分布の面積と同等である。
			// なぜなら、round(res-0.5)=res, round(res+0.5)=res+1となり、
			// res-0.5<=x<res+0.5の範囲全てで同じ値をとるからである。
			// また、max(0,round(x))について,
			// x<0の場合は常に0であるから、
			// x=-1でもx=0.5でもx=0でもres=0である。
			// よって、res=0の場合は
			// -∞<=x<0.5の確率分布の面積を求める。
			// 区間[a,b)の確率分布の面積は
			// 累積分布関数の差cdf(b)-cdf(a)で求められる。
			// 区間[-∞,b)の確率分布の面積は
			// 累積分布関数cdf(b)で求められる。
			double b = (double) res + 0.5;

			if (res == 0) {
				return normal_cdf(mean, std_dev, b);
			} else {
				double a = (double) res - 0.5;
				return normal_cdf(mean, std_dev, b) - normal_cdf(mean, std_dev, a);
			}
		}

		// 平均mean, 標準偏差std_devに従う正規分布において、a以上b以下の確率を求める
		double probability_in_range(double mean, double std_dev, double a, double b) {
			double p_a = normal_cdf(mean, std_dev, a);
			double p_b = normal_cdf(mean, std_dev, b);
			return p_b - p_a;
		}

		// 累積分布関数
		// 平均mean, 標準偏差std_devに従う正規分布において、x以下の確率を求める
		double normal_cdf(double mean, double std_dev, double x) {
			return 0.5 * (1.0 + erf((x - mean) / (std_dev * sqrt(2.0))));
		}

		/// 時間切れしたときはBFSで掘る。
		void giveup() {
			System.err.println("!log giveup because timeup");
			ArrayDeque<PairInt> que = new ArrayDeque<>();
			que.add(new PairInt(n / 2, n / 2));
			IntList list = new IntList();
			int rem = total;
			BoolAryAry used = new BoolAryAry(n, n, false);

			while (!que.isEmpty()) {
				PairInt pi = que.poll();
				int i = pi.first;
				int j = pi.second;
				if (used.get(i, j))
					continue;
				used.set(i, j, true);

				int ret = mine(i, j);
				if (ret > 0) {
					list.add(i * n + j);
					rem -= ret;
					if (rem == 0)
						break;
				}

				for (final var dij : DIJ) {
					int di = dij[0];
					int dj = dij[1];
					int i2 = i + di;
					int j2 = j + dj;
					if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < n) {
						if (ret == 0) {
							que.add(new PairInt(i2, j2));
						} else {
							que.addFirst(new PairInt(i2, j2));
						}
					}
				}
			}
			ans(list);
		}

		// volumesとfailed_coordinatesが異なるかどうかを返す
		boolean is_different(final IntAry volumes, final IntList failed_coordinates) {
			for (final var ij : failed_coordinates) {
				if (volumes.get(ij) == 0) {
					return true;
				}
			}
			return false;
		}

		// 油田配置がtop_leftsにある場合、
		// q番目のクエリで占った油田集合の埋蔵量合計
		int get_query_volume(final OilStateList oil_states, int q, final IntAry top_lefts) {
			int S = 0;
			for (int oil_id = 0; oil_id < top_lefts.size(); ++oil_id) {
				var oil_state_p = oil_states.get(oil_id);
				var ij = top_lefts.get(oil_id);
				int p_volume = oil_state_p.top_left_query_volumes.get(ij, q);
				if (p_volume > 0) {
					S += p_volume;
				}
			}
			return S;
		}

		// 油田配置がこの状態になる確率を求める
		// vs: 各座標の埋蔵量
		// top_lefts: 油田の左上座標郡
		double get_ln_pR_if_x(final OilStateList oil_states, final IntAry volumes, final IntAry top_lefts) {
			// 既に失敗した油田配置の集合に含まれる油田配置の場合、対数尤度を非常に小さい値にする
			for (final var failed_coordinates : failed) {
				boolean skip = is_different(volumes, failed_coordinates);

				if (!skip) {
					int size = 0;
					for (int ij = 0; ij < n2; ++ij) {
						if (volumes.get(ij) > 0) {
							++size;
						}
					}
					if (size == failed_coordinates.size()) {
						return -1e20;
					}
				}
			}

			// 今までのクエリ結果Rから、
			// 配置x=oil_statesにおける尤度P(R|x)を求める
			// 公式は以下の通り
			// P(R|x) = ΠP(ret|x) for ret in R
			// P(ret|x)は非常に小さい値でdoubleに収まらない可能性があるため、対数尤度を計算する
			// 対数に変形すると
			// log(P(R|x)) = Σlog(P(ret|x)) for ret in R
			// となる
			double ln_pR_if_x = 0.0;
			for (int q = 0; q < queries.size(); ++q) {
				// この油田配置において、q番目のクエリで占った油田の埋蔵量の合計を求める
				// q番目のクエリを打った時のlog(P(ret|S))は記録済みであり、
				// 配置xにおけるSを求めることで、
				// log(P(ret|x)) = log(P(ret|S))を求めることができる
				int S = get_query_volume(oil_states, q, top_lefts);
				ln_pR_if_x += ln_pr_if_s_query.get(q, S);
			}
			return ln_pR_if_x;
		}

		double ln_prob_state() {
			for (final var failed_coordinates : failed) {
				boolean skip = is_different(state.volumes, failed_coordinates);
				if (!skip) {
					int size = 0;
					for (int ij = 0; ij < n2; ++ij) {
						if (state.volumes.get(ij) > 0) {
							++size;
						}
					}
					if (size == failed_coordinates.size()) {
						return -1e20;
					}
				}
			}
			double prob = 0.0;
			for (int q = 0; q < ln_pr_if_s_query.size(); ++q) {
				prob += ln_pr_if_s_query.get(q, state.query_volumes.get(q));
			}
			return prob;
		}
	}

	Sim sim;

	class Query {
		BoolAry in_query; // ある位置の油の埋蔵量がクエリされているか : N*N個
		IntAry volume; // 油田の埋蔵量のリスト : M個
		int coordinate_size; // クエリに含めるマスの数

		Query() {
			this.in_query = new BoolAry(input.n2, false);
			this.volume = new IntAry(pool.size(), 0);
			this.coordinate_size = 0;
		}

		// 指定したマスをクエリに含めるかどうかを反転する
		void flip(int ij) {
			if (in_query.get(ij)) {
				in_query.set(ij, false);
				for (int x = 0; x < pool.size(); ++x) {
					volume.add(x, -pool.get(x).volume.get(ij));
				}
				--coordinate_size;
			} else {
				in_query.set(ij, true);
				for (int x = 0; x < pool.size(); ++x) {
					volume.add(x, pool.get(x).volume.get(ij));
				}
				++coordinate_size;
			}
		}

		// 占うマス集合を取得する
		IntList get_coordinates() {
			IntList result = new IntList();
			for (int ij = 0; ij < in_query.size(); ++ij) {
				if (in_query.get(ij)) {
					result.add(ij);
				}
			}
			return result;
		}

		// 占いの良さを評価する
		// add_k: クエリに含める座標の数. 0ならすでにインスタンスに含めているものだけ使う
		// add_v: クエリに含める座標の埋蔵量合計. 0ならすでにインスタンスに含めているものだけ使う
		double eval(int add_k, int add_v) {
			int k = coordinate_size + add_k;
			// ln_pr[r] = クエリ結果がrとなる確率の対数
			// 最後にlogをとるまで、普通の確率として計算する
			double[] ln_pr = new double[input.total + 50];
			for (int x = 0; x < pool.size(); ++x) {
				int v = volume.get(x) + add_v;
				int lb = sim.pr_if_x_lb.get(k, v);
				double px = pool.get(x).px_if_R;
				// 公式 p(r)=Σp(r|x)p(x)
				// この公式を使って、p(r)を求める
				for (int pi = 0; pi < sim.pr_if_x.get(k, v).size(); ++pi) {
					final var pr_if_x = sim.pr_if_x.get(k, v).get(pi).first;
					ln_pr[lb + pi] += pr_if_x * px;
				}
			}
			for (int x = 0; x < ln_pr.length; ++x) {
				ln_pr[x] = log(ln_pr[x]);
			}
			// I(X;R) = ΣΣp(x,r)log(p(x,r)/(p(x)p(r))
			// = ΣΣp(r|x)p(x)log(p(r|x)p(x)/p(x)p(r))
			// = ΣΣp(r|x)p(x)log(p(r|x)/p(r))
			// = ΣΣp(r|x)p(x)(log(p(r|x))-log(p(r)))
			// この式を使って、相互情報量を求める
			double info = 0.0;
			for (int x = 0; x < pool.size(); ++x) {
				double px = pool.get(x).px_if_R;
				int v = volume.get(x) + add_v;
				int lb = sim.pr_if_x_lb.get(k, v);
				for (int pi = 0; pi < sim.pr_if_x.get(k, v).size(); ++pi) {
					final var pr_if_x = sim.pr_if_x.get(k, v).get(pi).first;
					final var ln_pr_if_x = sim.pr_if_x.get(k, v).get(pi).second;
					final var ln_prr = ln_pr[lb + pi];
					info += pr_if_x * px * (ln_pr_if_x - ln_prr);
				}
			}
			// コストは1/sqrt(k)なので、
			// sqrt(k)倍することは、コストで割ることと同じ
			// コストあたりの相互情報慮を求める
			return info * sqrt((double) k);
		}
	}

// 占いクエリを取得する
	IntList getDivinationQuery() {
		final var size = pool.size();
		// 全ての油田配置が同じ埋蔵量を持つかどうかを座標ごとに調べる
		BoolAry same = new BoolAry(input.n2, true);
		for (int x = 1; x < size; ++x) {
			final var layout = pool.get(x);
			for (int ij = 0; ij < input.n2; ++ij) {
				same.and(ij, layout.volume.get(ij) == pool.get(0).volume.get(ij));
			}
		}

		// クエリを作成
		Query query = new Query();
		// 全ての配置が同じ埋蔵量だと想定される座標以外で1点だけ占うクエリを評価する
		PairDoubleIntList query_coordinate_evals = new PairDoubleIntList();
		for (int ij = 0; ij < input.n2; ++ij) {
			if (!same.get(ij)) {
				// 評価前後にflipを挟むことで、
				// この座標1個だけでクエリを作成するときの情報量を計算する
				query.flip(ij); // クエリにこの座標を含める
				double ev = query.eval(0, 0); // クエリにこの座標を含めたときの情報量を計算
				query.flip(ij); // クエリにこの座標を含めない
				query_coordinate_evals.add(new PairDoubleInt(ev, ij));
			}
		}
		query_coordinate_evals.sort((a, b) -> Double.compare(b.first, a.first));

		// 全ての配置が同じ埋蔵量だと想定される座標をクエリに含める
		IntList no_info_coordinates = new IntList();
		for (int ij = 0; ij < input.n2; ++ij) {
			if (same.get(ij)) {
				no_info_coordinates.add(ij);
			}
		}

		// クエリに含める座標をランダムにソート
		PairIntList evaluation_values = new PairIntList();
		for (int ij : no_info_coordinates) {
			int evaluation_value = (int) (pool.get(0).volume.get(ij) * 1000 + rng.randrange(1000));
			evaluation_values.add(new PairInt(ij, evaluation_value));
		}

		evaluation_values.sort((a, b) -> Integer.compare(b.second, a.second));
		no_info_coordinates = new IntList();
		for (PairInt pi : evaluation_values) {
			no_info_coordinates.add(pi.first);
		}

		double crt = -1e100;
		int add_k = 0; // 情報量0のマスを追加する数
		int add_v = 0; // 情報量0のマスを追加することで増える埋蔵量
		final var best_layout = pool.get(0);
		for (int _i = 0; _i < 3; ++_i) {
			boolean change = false;
			// クエリに含める座標を1つずつ増やすか減らすかして、
			// 相互情報量/コストを最大化する山登り
			for (final PairDoubleInt pdi : query_coordinate_evals) {
				int ij = pdi.second;
				query.flip(ij);
				double eval = query.eval(add_k, add_v);

				if (crt < eval) {
					crt = eval;
					change = true;
				} else {
					query.flip(ij);
				}
			}
			if (!input.is_difficult()) {
				// 情報量0のマスの追加
				while (add_k < no_info_coordinates.size()) {
					double eval = query.eval(add_k + 1, add_v + best_layout.volume.get(no_info_coordinates.get(add_k)));
					if (crt < eval) {
						crt = eval;
						add_v += pool.get(0).volume.get(no_info_coordinates.get(add_k));
						add_k += 1;
						change = true;
					} else {
						break;
					}
				}
				while (add_k > 0) {
					double eval = query.eval(add_k - 1,
							add_v - best_layout.volume.get(no_info_coordinates.get(add_k - 1)));
					if (crt < eval) {
						crt = eval;
						add_v -= pool.get(0).volume.get(no_info_coordinates.get(add_k - 1));
						add_k -= 1;
						change = true;
					} else {
						break;
					}
				}
			}

			if (!change) {
				// どの座標を1点変えても相互情報量/コストが変わらない場合は終了
				break;
			}
		}
		IntList query_coordinates = query.get_coordinates();
		for (int i = 0; i < add_k; i++) {
			query_coordinates.add(no_info_coordinates.get(i));
		}
		return query_coordinates;
	}

	void simulated_annealing(final PairIntListAryAry swaps, LongDoubleMap hash_ln_lilelihood, int ITER) {
		// 一番尤度の高い配置からスタートし、焼き鈍し法を実行することで配置候補を沢山生成する
		// 途中過程で見つけた配置は、よほど小さいもの以外は全てpoolに入れる
		var bef_time = get_time();
		double crt = pool.get(0).ln_pR_if_x;
		for (int oil_id = 0; oil_id < input.m; ++oil_id) {
			state.move_to(oil_id, pool.get(0).top_lefts.get(oil_id));
		}
		double max_crt = crt;
		final double T0 = 2.0;
		final double T1 = 1.0;
		// TLEにならないように残り時間が少なくなったら反復回数を減らす
		ITER = (int) (ITER * min(time_limit - get_time(), 1.0));
		for (int t = 0; t < ITER; ++t) {
			double temp = T0 + (T1 - T0) * t / (double) ITER;
			int slide_threshold = 30;
			int warp_threshold = slide_threshold + 10;
			int swap_threshold = warp_threshold + 60;
			int coin = (int) rng.randrange(swap_threshold);
			if (coin < slide_threshold) {
				// ポリオミノをランダムに選び、上下左右に1マス移動
				int oil_id = (int) rng.randrange(input.m);
				var oil = input.oils.get(oil_id);
				var dij = DIJ[(int) rng.randrange(4)];
				int di = dij[0];
				int dj = dij[1];
				int i2 = state.top_lefts.get(oil_id) / input.n + di;
				int j2 = state.top_lefts.get(oil_id) % input.n + dj;
				if (i2 < 0)
					i2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (j2 < 0)
					j2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (i2 < input.n - oil.max_i && j2 < input.n - oil.max_j) {
					int bk = state.top_lefts.get(oil_id);
					state.move_to(oil_id, i2 * input.n + j2);
					// 移動後の配置の対数尤度を計算
					// 既に計算している場合はその値を使用
					// zobrist hashを使用しているので、既に計算しているかどうかはO(1)で判定可能
					double next = hash_ln_lilelihood.count(state.hash) ? hash_ln_lilelihood.get(state.hash)
							: sim.ln_prob_state();
					if (!hash_ln_lilelihood.count(state.hash)) {
						hash_ln_lilelihood.put(state.hash, next);
						// -∞になるときはpoolに入れない
						// これはquery関数でprobを計算したときに-∞になるときに隣のprob-10.0としたことで計算可能にしている
						if (next - max_crt >= -10.0) {
							pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes));
						}
					}
					if (crt <= next || rng.gen_bool(exp((next - crt) / temp))) {
						crt = next;
					} else {
						state.move_to(oil_id, bk);
					}
				}
			} else if (coin < warp_threshold) {
				// ポリオミノをランダムに選び、ランダムな位置に移動
				int oil_id = (int) rng.randrange(input.m);
				var oil = input.oils.get(oil_id);
				int i2 = (int) rng.randrange(input.n - oil.max_i);
				int j2 = (int) rng.randrange(input.n - oil.max_j);
				int bk = state.top_lefts.get(oil_id);
				state.move_to(oil_id, i2 * input.n + j2);
				double next = hash_ln_lilelihood.count(state.hash) ? hash_ln_lilelihood.get(state.hash)
						: sim.ln_prob_state();
				if (!hash_ln_lilelihood.count(state.hash)) {
					hash_ln_lilelihood.put(state.hash, next);
					if (next - max_crt >= -10.0) {
						pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes));
					}
				}
				if (crt <= next || rng.gen_bool(exp((next - crt) / temp))) {
					crt = next;
				} else {
					state.move_to(oil_id, bk);
				}
			} else {
				// ポリオミノを2つランダムに選び、互いの位置を交換
				int oil_id_a = (int) rng.randrange(input.m);
				var oil_a = input.oils.get(oil_id_a);
				int oil_id_b = (int) rng.randrange(input.m);
				var oil_b = input.oils.get(oil_id_b);
				if (oil_id_a == oil_id_b) {
					continue;
				}
				int ai = state.top_lefts.get(oil_id_a) / input.n;
				int aj = state.top_lefts.get(oil_id_a) % input.n;
				int bi = state.top_lefts.get(oil_id_b) / input.n;
				int bj = state.top_lefts.get(oil_id_b) % input.n;
				var daij = swaps.get(oil_id_b, oil_id_a).get((int) rng.randrange(swaps.get(oil_id_a, oil_id_b).size()));
				var dbij = swaps.get(oil_id_a, oil_id_b).get((int) rng.randrange(swaps.get(oil_id_b, oil_id_a).size()));
				int dai = daij.first;
				int daj = daij.second;
				int dbi = dbij.first;
				int dbj = dbij.second;
				int ai2 = bi + dai;
				int aj2 = bj + daj;
				if (ai2 < 0)
					ai2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (aj2 < 0)
					aj2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (ai2 >= input.n - oil_a.max_i || aj2 >= input.n - oil_a.max_j) {
					continue;
				}
				int bi2 = ai + dbi;
				int bj2 = aj + dbj;
				if (bi2 < 0)
					bi2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (bj2 < 0)
					bj2 += Integer.MAX_VALUE; // java size_tは負の数にならない
				if (bi2 >= input.n - oil_b.max_i || bj2 >= input.n - oil_b.max_j) {
					continue;
				}
				state.move_to(oil_id_a, ai2 * input.n + aj2);
				state.move_to(oil_id_b, bi2 * input.n + bj2);
				double next = hash_ln_lilelihood.count(state.hash) ? hash_ln_lilelihood.get(state.hash)
						: sim.ln_prob_state();
				if (!hash_ln_lilelihood.count(state.hash)) {
					hash_ln_lilelihood.put(state.hash, next);
					if (next - max_crt >= -10.0) {
						pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes));
					}
				}
				if (crt <= next || rng.gen_bool(exp((next - crt) / temp))) {
					crt = next;
				} else {
					state.move_to(oil_id_a, ai * input.n + aj);
					state.move_to(oil_id_b, bi * input.n + bj);
				}
			}
			if (max_crt < crt) {
				max_crt = crt;
			}
		}
		pool.sort();
	}

	class SwapsPairList implements Iterable<SwapsPair> {
		ArrayList<SwapsPair> list;

		SwapsPairList() {
			list = new ArrayList<>();
		}

		void add(SwapsPair val) {
			list.add(val);
		}

		int size() {
			return list.size();
		}

		void resize(int size) {
			while (list.size() > size)
				list.remove(list.size() - 1);
		}

		public void sort(Comparator<SwapsPair> c) {
			list.sort(c);
		}

		@Override
		public Iterator<SwapsPair> iterator() {
			return list.iterator();
		}
	}

	class SwapsPair {
		int volume;
		PairInt pair;

		SwapsPair(int v, PairInt pi) {
			this.volume = v;
			this.pair = pi;
		}
	}

	PairIntListAryAry get_swaps() {
		// 2つのポリオミノの位置を入れ替える操作を行うために、入れ替えた際にどれだけ位置をずらせばよいかを予め計算しておく
		// swaps[oil_id_a][oil_id_b] := oil_id_aとoil_id_b+Δが出来るだけ一致するようなΔ
		// 具体例で説明する。
		// ポリオミノpとポリオミノqを入れ替えるとき、
		// 単純に左上座標を入れ替えるだけだと元の配置から大きくずれてしまう。
		// 例えば、
		// .a.
		// .a.
		// aa.
		// の位置に
		// b..
		// b..
		// bb.
		// を単純に配置すると、
		// 元の配置と重なるのは
		// ...
		// ...
		// cc.
		// だけである。
		// これを横方向に+1マスずらして入れ替えた場合、
		// .c.
		// .c.
		// .c.
		// となり、元の配置からのずれを減らすことができる。

		PairIntListAryAry swaps = new PairIntListAryAry(input.m, input.m);
		for (int oil_id_a = 0; oil_id_a < input.m; ++oil_id_a) {
			final var oil_a = input.oils.get(oil_id_a);
			// ポリオミノpを0,0に配置したときの座標ならtrue, それ以外ならfalse
			BoolAry is_a_coordinate = new BoolAry(input.n2, false);
			for (int ij : oil_a.coordinate_ids) {
				is_a_coordinate.set(ij, true);
			}
			for (int oil_id_b = 0; oil_id_b < input.m; ++oil_id_b) {
				final var oil_b = input.oils.get(oil_id_b);
				if (oil_id_a == oil_id_b) {
					continue;
				}
				// 二つのポリオミノが重なっているマスの数と、そのときの位置のずれのリストを計算
				SwapsPairList list = new SwapsPairList();
				for (int di = -(int) oil_b.max_i; di <= (int) oil_a.max_i; ++di) {
					for (int dj = -(int) oil_b.max_j; dj <= (int) oil_a.max_j; ++dj) {
						int volume = 0;
						for (int ij : oil_b.coordinate_ids) {
							int i = ij / input.n + di;
							int j = ij % input.n + dj;
							if (i >= 0 && j >= 0 && i < input.n && j < input.n
									&& is_a_coordinate.get(i * input.n + j)) {
								++volume;
							}
						}
						list.add(new SwapsPair((int) volume, new PairInt(di, dj)));
					}
				}
				// 重なっているマスの数が多い順にソート
				list.sort((a, b) -> Integer.compare(b.volume, a.volume));
				// 4個である必要はないが、近傍の多様性のために複数持っておく
				list.resize(4);
				swaps.get(oil_id_a, oil_id_b).clear();
				for (final var sp : list) {
					PairInt i_j = sp.pair;
					swaps.get(oil_id_a, oil_id_b).add(i_j);
				}
			}
		}
		return swaps;
	}

	int main() {
		input = read_input();
		input.set_difficult();
		// 2つのポリオミノの位置を入れ替える操作を行うために、入れ替えた際にどれだけ位置をずらせばよいかを予め計算しておく
		// swaps[p][q] := pとq+Δが出来るだけ一致するようなΔ
		var swaps = get_swaps();

		sim = new Sim();
		state = new State();
		int ITER = 4000000 * 5 / (2 * input.n2);
		if (input.is_difficult()) {
			ITER /= 5;
		}
		pool = new OilLayoutList(ITER * 2);
		for (int t = 0;; ++t) {
			if (sim.rem == 0) {
				System.err.println("!There is no more query");
				break;
			}
			if (get_time() > time_limit - 0.1) {
				sim.giveup();
				break;
			}

			// 各配置の対数尤度を更新する
			// t=0のときはpoolになにも入っていないので何もしない
			for (var layout : pool) {
				if (layout.volume == null && !sim.failed.empty()) {
					layout.volume = input.get_volume(layout.top_lefts);
				}
				layout.ln_pR_if_x = sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts);
			}
			// 同じ尤度の配置を散らすためにシャッフル
			pool.shuffle(rng);
			// 対数尤度が高い順に配置候補をソート
			pool.sort();
			// プールに存在する配置と対数尤度を記録しておく
			LongDoubleMap hash_ln_lilelihood = new LongDoubleMap();
			for (final var layout : pool) {
				hash_ln_lilelihood.put(layout.hash, layout.ln_pR_if_x);
			}

			if (t == 0) {
				// 1ターン目は全ての配置が等確率なので、ランダムに候補を生成する
				for (int _i = 0; _i < ITER; ++_i) {
					for (int oil_id = 0; oil_id < input.m; ++oil_id) {
						final var oil = input.oils.get(oil_id);
						int i = (int) rng.randrange(input.n - oil.max_i);
						int j = (int) rng.randrange(input.n - oil.max_j);
						state.move_to(oil_id, i * input.n + j);
					}
					// まだプールに追加していない配置なら追加する
					if (!hash_ln_lilelihood.count(state.hash)) {
						hash_ln_lilelihood.put(state.hash, 0.0);
						pool.add(new OilLayout(state.hash, 0.0, 0.0, state.top_lefts, state.volumes));
					}
				}
			} else {
				simulated_annealing(swaps, hash_ln_lilelihood, ITER);
			}
			// この時点でpoolはソート済み
			var max_prob = pool.get(0).ln_pR_if_x;
			// 対数尤度から尤度に戻す
			// p(R|x) = exp(log(p(R|x)))
			for (var layout : pool) {
				layout.px_if_R = exp(layout.ln_pR_if_x - max_prob);
			}

			// 尤度から事後確率に変換する
			// p(x|R) = p(R|x)/Σp(R|x)
			pool.normalize();

			// 尤度が低すぎる配置を削除
			while (pool.size() > 1 && pool.get(pool.size() - 1).px_if_R < 1e-9) {
				pool.remove(pool.size() - 1);
			}
			final var best_layout = pool.get(0);
			var best_bits = input.get_positives(best_layout.top_lefts);

			double best_pool_prob = best_layout.px_if_R;
			pool.concat(ITER);
			// poolを切り取ったことで合計100%じゃなくなったので再度正規化
			pool.normalize();
			// 油田配置の全座標の埋蔵量を計算
			pool.set_volume();

			if (best_pool_prob > 0.8) {
				// 自信があるとき、推測をう
				// Mが大きい時は焼き鈍しに失敗して真の解を見落としている可能性が高く推測に失敗することがあるので、
				// いきなり推測を行わず、一旦それらのマス以外に対して占いを実行
				IntList T_vec = new IntList();
				IntList T_vec_reverse = new IntList();
				for (int ij = 0; ij < input.n2; ++ij) {
					if (best_bits.get(ij)) {
						T_vec.add(ij);
					} else {
						T_vec_reverse.add(ij);
					}
				}
				if (!input.is_difficult() || (sim.queries.size() > 0
						&& sim.queries.get(sim.queries.size() - 1).query_coordinates.equals(T_vec_reverse))) {
					if (sim.ans(T_vec)) {
						break;
					} else if (sim.failed.size() == 1) {
						state.volumes = input.get_volume(state.top_lefts);
					}
				} else {
					sim.query(T_vec_reverse);
					state.add_query(T_vec_reverse);
				}
			} else {
				var query_coordinates = getDivinationQuery();
				// 占いの評価値の方が高い場合、占いを行う
				sim.query(query_coordinates);
				state.add_query(query_coordinates);
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		Main main = new Main();
		try {
			main.main();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("!Time = " + main.get_time());
		System.err.println("!log miss " + main.sim.failed.size());
		System.err.println("!cost = " + main.sim.cost);
		System.err.println("!main end");
	}
}

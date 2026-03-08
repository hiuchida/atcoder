import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static boolean RELEASE = true;

	// 誤差関数 (erf) の近似　※Java22以降はMath.erf()
	static double erf(double x) {
		// 精度: 最大誤差は約 1.5e-7
		double sign = (x < 0) ? -1 : 1;
		x = Math.abs(x);
		// 定数
		double p = 0.3275911;
		double a1 = 0.254829592;
		double a2 = -0.284496736;
		double a3 = 1.421413741;
		double a4 = -1.453152027;
		double a5 = 1.061405429;
		double t = 1.0 / (1.0 + p * x);
		double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
		return sign * y;
	}
	static int lastid=1;
	static int seq() {
		return lastid++;
	}
	static class IntDouble implements Comparable<IntDouble> {
		int i;
		double v;
		IntDouble(int i, double v) {
			this.i=i;
			this.v=v;
		}
		@Override
		public int compareTo(IntDouble that) {
			return -1 * Double.compare(this.v, that.v);
		}
		@Override
		public String toString() {
			return "[i=" + i + ", v=" + v + "]";
		}
	}
	static class IntAryInt implements Comparable<IntAryInt> {
		int[] ary;
		int v;
		IntAryInt(int[] ary, int v) {
			this.ary=ary;
			this.v=v;
		}
		@Override
		public int compareTo(IntAryInt that) {
			return -1 * Integer.compare(this.v, that.v);
		}
	}
	static class Point {
		int r;
		int c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Random rand=new Random(0);

	static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	// Xorshiftによる乱数生成器
	static class Xorshift {
		private long x;
		public Xorshift(long seed) {
			this.x = seed;
		}

		// [0, stop)
		public int randrange(int stop) {
			long v=next();
			v=v & Long.MAX_VALUE;
			return (int)(v % stop);
		}

		// [start, stop)
		public int randrange(int start, int stop) {
			long v=next();
			v=v & max();
			return start + (int)(v % (stop - start));
		}

		// [a, b]
		public int randint(int a, int b) {
			return randrange(a, b + 1);
		}

		// [0.0, 1.0]
		public double random() {
			long v=next();
			v=v & max();
			return (double)v / max();
		}

		// [a, b] or [b, a]
		public double uniform(double a, double b) { return a + (b - a) * random(); }

		public long next() {
			x ^= x << 13;
			x ^= x >>> 17;
			x ^= x << 5;
			return x;
		}

		public boolean gen_bool(double p) {
//			return rand.nextBoolean();
			double r = random();
			return r < p;
		}
		public long min() { return 0; }
		public long max() { return Long.MAX_VALUE; }
	}
	static Xorshift rng = new Xorshift(1);

	// 油田の配置についての情報
	static class OilLayout implements Comparable<OilLayout> {
		int id=seq();
		long hash;
		// 油田配置をこの配置xだと過程したとき
		// 今までのクエリ結果Rが得られる確率の対数.
		// 対数尤度ともいう。
		double ln_pR_if_x;
		// 今までのクエリの結果Rから計算した,この配置になる事後確率P(x|R)
		double px_if_R;
		int[] top_lefts; // 油田の左上の座標
		int[] volume; // ある位置の油の埋蔵量
		BitSet mask;
		OilLayout(long hash, double ln, double px, int[] tls, int[] vol, BitSet mask) {
			this.hash = hash;
			this.ln_pR_if_x = ln;
			this.px_if_R = px;
			this.top_lefts = tls.clone();
			this.volume = (vol != null) ? vol.clone() : null;
			this.mask = mask;
		}
		@Override
		public int compareTo(OilLayout that) {
			return -1*Double.compare(this.ln_pR_if_x, that.ln_pR_if_x);
		}
	}

	// 油田の形についての情報
	static class OilShape implements Comparable<OilShape> {
		int max_i, max_j; // 油田が収まる正方形の大きさ
		int[] coordinate_ids; // 座標(i,j)の組を1変数で表したもの
//		List<Integer> coordinate_ids = new ArrayList<>(); // 座標(i,j)の組を1変数で表したもの
		// read_input()内しか使わないため、coordinatesは未使用
//		List<Point> coordinates = new ArrayList<>(); // 座標(i,j)の組
		// 座標(i,j)の組をマスクしたもの
		// 島の大きさN<=20なので、20*20のビットセットで表現できる
		// javaではBitSetのシフトができないため、maskは未使用
//		BitSet mask = new BitSet(20 * 20);
		@Override
		public int compareTo(OilShape that) {
			return Arrays.compare(this.coordinate_ids, that.coordinate_ids);
		}
	}

	static class Input {
		int n; // 島の大きさ 10<=N<=20
		int n2; // n*n
		int m; // 油田の数 2<=M<=10
		double eps; // 占い結果に用いるエラーパラメータ 0.01<=eps<=0.2
		OilShape[] oils; // 油田の形に関する情報 .size()==M
		int total; // 島全体の油田の埋蔵量の合計

		// 油田の左上の座標を受け取り、埋蔵量が1以上のマスの集合をbitsetで返す
		BitSet get_positives(int[] top_lefts) {
			BitSet positives = new BitSet(n2);
			for (int oil_id = 0; oil_id < m; oil_id++) {
				// javaではBitSetのシフトができないため、maskは未使用
				// positives |= oils[oil_id].mask << top_lefts[oil_id];
				for (int ij : oils[oil_id].coordinate_ids) {
					positives.set(ij + top_lefts[oil_id]);
				}
			}
			return positives;
		}

		// M個の油田の左上の座標を受け取り、その位置の油の埋蔵量を返す
		int[] get_volume(int[] top_lefts) {
			int[] volume = new int[n2];
			for (int oil_id = 0; oil_id < m; oil_id++) {
				int pij = top_lefts[oil_id];
				for (int ij : oils[oil_id].coordinate_ids) {
					volume[ij + pij]++;
				}
			}
			return volume;
		}
	}

	// プログラムが始まってからの時間を計測する
	static double get_time() {
		// startをstaticにすることで、
		// 2回目以降のget_time()の呼び出しでも
		// プログラムが始まってからの時間を計測できる
		long now=System.currentTimeMillis();
		return (double)(now - start) / 1000.0;
	}

	static Input read_input(Scanner sc) {
		Input input = new Input();
		input.n = sc.nextInt();
		input.m = sc.nextInt();
		input.eps = sc.nextDouble();
		input.n2 = input.n * input.n;
		input.oils = new OilShape[input.m];
		for (int oil_id = 0; oil_id < input.m; oil_id++) {
			input.oils[oil_id] = new OilShape();
			OilShape oil = input.oils[oil_id];
			int t_size = sc.nextInt();
			oil.coordinate_ids = new int[t_size];
			for (int i = 0; i < t_size; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
//				OilShape oil = input.oils[oil_id];
				int ij = r * input.n + c;
				oil.coordinate_ids[i] = ij;
//				oil.coordinate_ids.add(ij);
//				oil.coordinates.add(new Point(r, c));
			}
			Arrays.sort(oil.coordinate_ids);
		}
		input.total = 0;
		for (int oil_id = 0; oil_id < input.m; oil_id++) {
			input.total += input.oils[oil_id].coordinate_ids.length;
//			input.total += input.oils[oil_id].coordinate_ids.size();
		}

		// 同じ図形かの判定を行うためにポリオミノをソートしてく
		Arrays.sort(input.oils);

		for (int oil_id = 0; oil_id < input.m; oil_id++) {
			OilShape oil = input.oils[oil_id];
			oil.max_i = 0;
			oil.max_j = 0;
			for (int ij : oil.coordinate_ids) {
				int i = ij / input.n;
				int j = ij % input.n;
				oil.max_i = Math.max(oil.max_i, i);
				oil.max_j = Math.max(oil.max_j, j);
//				int ij = i * input.n + j;
//				oil.mask.set(ij);
			}
		}
		return input;
	}

	static final double SMALL_VALUE = 1e-6; // すごく小さい値

	/**
	 * 油田1個分の計算状態を保持する構造体
	 */
	static class OilState {
		// topLeftQueryVolumes.get(topLeft).get(q) は、
		// この油田の左上座標が topLeft にあるとき、q番目のクエリ座標集合に含まれる埋蔵量の合計
		List<List<Integer>> top_left_query_volumes;
		// この油田の左上座標ごとのzobrist hash
		long[] hashes;
		OilState(Input input) {
			this.top_left_query_volumes = new ArrayList<>(input.n2);
			for (int i = 0; i < input.n2; i++) {
				this.top_left_query_volumes.add(new ArrayList<>());
			}
			this.hashes = new long[input.n2];
		}
	}

	/**
	 * 全体の配置状態を管理するクラス
	 */
	static class State {
		List<OilState> oil_states; // 油田の状態リスト (M個)
		int[] top_lefts; // 各油田の現在の左上座標ID (M個)
		int[] volumes; // 各マスの現在の油の埋蔵量 (N*N個)
		BitSet mask;
		List<Integer> query_volumes; // 各クエリ(q番目)で占った座標集合の現在の埋蔵量合計
		long hash;
		Input input;

		// 全ての油田が(0,0)にある状態を初期状態とする
		State(Input input) {
			this.input = input;
			this.oil_states = new ArrayList<>(input.m);
			for (int oil_id = 0; oil_id < input.m; oil_id++) {
				this.oil_states.add(new OilState(input));
				OilState oil_state = oil_states.get(oil_id);
				if (oil_id > 0 && Arrays.equals(input.oils[oil_id - 1].coordinate_ids, input.oils[oil_id].coordinate_ids)) {
					oil_state.hashes = oil_states.get(oil_id - 1).hashes;
				} else {
					for (int ij = 0; ij < input.n2; ij++) {
						oil_state.hashes[ij] = rng.next();
					}
				}
			}
			// zobrist hashを初期化
			this.hash = 0;
			for (OilState oil_state : oil_states) {
				this.hash ^= oil_state.hashes[0];
			}
			this.top_lefts = new int[input.m]; // 初期値は 0 (座標 (0,0))
			this.volumes=null;
			this.mask=null;
			this.query_volumes = new ArrayList<>();
		}

		// 油田oil_idをnew_top_leftに移動する
		// 移動自体はtop_leftsの更新だが、
		// 移動に伴い、query_volumes,volumesを更新する
		void move_to(int oil_id, int new_top_left) {
			OilState oil_state = oil_states.get(oil_id);
			int old_top_left = top_lefts[oil_id];
			hash ^= oil_state.hashes[old_top_left] ^ oil_state.hashes[new_top_left];
			if (query_volumes != null) {
				for (int q = 0; q < query_volumes.size(); q++) {
					query_volumes.set(q,
							query_volumes.get(q)
							+ oil_state.top_left_query_volumes.get(new_top_left).get(q)
							- oil_state.top_left_query_volumes.get(old_top_left).get(q));
				}
			}
			if (volumes != null && mask != null) {
				for (int ij : input.oils[oil_id].coordinate_ids) {
					if (volumes[ij + old_top_left] == 1) { // 1から0になる瞬間
						mask.clear(ij + old_top_left);
					}
					volumes[ij + old_top_left]--;
					if (volumes[ij + new_top_left] == 0) { // 0から1になる瞬間
						mask.set(ij + new_top_left);
					}
					volumes[ij + new_top_left]++;
				}
			}
			top_lefts[oil_id] = new_top_left;
		}

		// 占いクエリを投げた後、今回占った座標集合に含まれる埋蔵量を記録する
		void add_query(List<Integer> query_coordinates) {
			boolean[] in_query = new boolean[input.n2];
			for (int id : query_coordinates) {
				in_query[id] = true;
			}
			for (int oilId = 0; oilId < input.m; oilId++) {
				OilShape oil = input.oils[oilId];
				OilState oil_state = oil_states.get(oilId);
				for (int di = 0; di < input.n - oil.max_i; di++) {
					for (int dj = 0; dj < input.n - oil.max_j; dj++) {
						int top_left = di * input.n + dj;
						int c = 0;
						for (int id : oil.coordinate_ids) {
							if (in_query[top_left + id]) {
								c++;
							}
						}
						oil_state.top_left_query_volumes.get(top_left).add(c);
					}
				}
			}
			if (query_volumes != null) {
				int[] volume = input.get_volume(top_lefts);
				int c = 0;
				for (int ij : query_coordinates) {
					c += volume[ij];
				}
				query_volumes.add(c);
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(Arrays.toString(top_lefts)).append("\n");
			sb.append(mask).append("\n");
			for (int y=0; y<input.n; y++) {
				for (int x=0; x<input.n; x++) {
					sb.append(""+volumes[y*input.n+x]).append(" ");
				}
				sb.append("\n");
			}
			return sb.toString();
//			return "State [oil_states=" + oil_states + ", top_lefts=" + Arrays.toString(top_lefts) + ", volumes="
//					+ Arrays.toString(volumes) + ", mask=" + mask + ", query_volumes=" + query_volumes + ", hash="
//					+ hash + ", input=" + input + "]";
		}
	}

	// 過去の占いの(油田配置、占い結果)の集合
	// vector<pair<vector<size_t>, size_t>> queries;
//	static class QueryHistory {
//		List<Integer> coords;
//		int ret;
//		QueryHistory(List<Integer> coords, int ret) {
//			this.coords=coords;
//			this.ret=ret;
//		}
//	}
	static class PreCalc {
		// クエリサイズk、埋蔵量総量Sに対して、
		// pr_if_xがもつrの値の下限
		int lb;
		// 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
		// 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
		// クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
		// 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
		// pr_if_x[k][S][r-lb] = (prob, log(prob))
		double[] pr_if_x;
		double[] ln_pr_if_x;
		PreCalc(int lb, List<Double> list) {
			this.lb = lb;
			this.pr_if_x = new double[list.size()];
			this.ln_pr_if_x = new double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				double p = list.get(i);
				pr_if_x[i] = p;
				ln_pr_if_x[i] = Math.log(p);
			}
		}
	}
	// 占い結果の管理と確率計算
	static class Sim {
		Scanner sc;
		int n;
		int n2;
		int m;
		int total;
		double eps;
		// 過去の占いの(油田配置、占い結果)の集合
//		List<QueryHistory> queries=new ArrayList<>();
		// 既に油田配置を答えるクエリを投げて失敗した油田配置の集合
//		List<int[]> failed = new ArrayList<>();
		Map<Integer, List<BitSet>> failedMap = new TreeMap<>();
		PreCalc[][] precalc;
		double[] ln_pr_buffer;
		// クエリごとにあり得る埋蔵量総量Sごとに
		// 埋蔵量Sのときにそのクエリで得られた結果になる確率P(r|S)の対数を記録
		List<double[]> ln_pr_if_s_query = new ArrayList<>();
		// 残りクエリ回数. 2*N*N回までクエリを投げられる
		int rem;

		Sim(Scanner sc, Input input) {
			this.sc = sc;
			this.n = input.n;
			this.n2 = input.n2;
			this.m = input.m;
			this.total = input.total;
			this.eps = input.eps;
			this.rem = n * n * 2;
			this.precalc = new PreCalc[input.n2 + 1][input.total + 1];
			this.ln_pr_buffer = new double[input.n2 + 100];

			// 尤度の事前計算 (Source 42-43)
			for (int k = 1; k <= input.n2; k++) {
				for (int s = 0; s <= input.total; s++) {
					int lb = 0;
					double mu = (double)(k - s) * input.eps + s * (1.0 - input.eps);
					double sigma = Math.sqrt(k * input.eps * (1.0 - input.eps));
					List<Double> list = new ArrayList<>();
					int startR = (int)Math.round(mu);
					
					for (int r = startR; r >= 0; r--) {
						double p = likelihood(mu, sigma, r);
						if (p < SMALL_VALUE) { lb = r + 1; break; }
						list.add(p);
					}
					Collections.reverse(list);
					for (int r = startR + 1; ; r++) {
						double p = likelihood(mu, sigma, r);
						if (p < SMALL_VALUE) break;
						list.add(p);
					}
					precalc[k][s] = new PreCalc(lb, list);
				}
			}
		}

		// 油田配置をあてるクエリを投げる
		// 失敗した場合は失敗した座標郡をfailedMapに記録する
		boolean ans(BitSet T) {
			if (get_time() > 2.9) {
				System.err.println("!log timeup by ans");
				throw new RuntimeException("timeup");
			}
			if (rem == 0) {
				System.err.println("!log giveup by ans");
				throw new RuntimeException("giveup");
			}
			rem--;
			System.out.print("a " + T.cardinality());
			for (int ij = T.nextSetBit(0); ij >= 0; ij = T.nextSetBit(ij + 1)) {
				System.out.print(" " + (ij / n) + " " + (ij % n));
			}
			System.out.println();
			System.out.flush();
			int ret = sc.nextInt();
			if (ret == 1) return true;
			cost++;
			miss++;
			int key = T.hashCode();
			List<BitSet> list = failedMap.get(key);
			if (list == null) {
				list = new ArrayList<>();
				failedMap.put(key, list);
			}
			list.add(T);
			return false;
		}

		// 指定したマスの集合の埋蔵量を占う
		int query(List<Integer> query_coordinates) {
			if (get_time() > 2.9) {
				System.err.println("!log timeup by query");
				throw new RuntimeException("timeup");
			}
			if (rem == 0) {
				System.err.println("!log giveup by query");
				throw new RuntimeException("giveup");
			}
			rem--;
			System.out.print("q " + query_coordinates.size());
			for (int ij : query_coordinates) {
				System.out.print(" " + (ij / n) + " " + (ij % n));
			}
			System.out.println();
			System.out.flush();
			int ret = sc.nextInt();
			cost+=1.0/Math.sqrt(query_coordinates.size());
			// クエリの結果を記録する
//			queries.add(new QueryHistory(query_coordinates, ret));
			// 埋蔵量Sごとの対数確率を事前計算
			double[] ln_pr_if_s = new double[total + 1];
			int k = query_coordinates.size();
			for (int s = 0; s <= total; s++) {
				double kS = (double)k - (double)s;
				double kSeps = kS * eps;
				double meps = 1.0 - eps;
				double mu = kSeps + s * meps;
				double sigma = Math.sqrt(k * eps * meps);
				ln_pr_if_s[s] = Math.log(likelihood(mu, sigma, ret));
			}

			// 尤度が非常に小さい場合、隣の値から計算しやすい値に更新する
			// これはあとで焼きなましの時にpoolに入れるかどうかの相対閾値として使う
			for (int i = 0; i < ln_pr_if_s.length - 1; ++i) {
				if (!Double.isInfinite(ln_pr_if_s[i]) && Double.isInfinite(ln_pr_if_s[i + 1])) {
					ln_pr_if_s[i + 1] = ln_pr_if_s[i] - 10.0;
				}
			}
			for (int i = ln_pr_if_s.length - 1; i > 0; --i) {
				if (!Double.isInfinite(ln_pr_if_s[i]) && Double.isInfinite(ln_pr_if_s[i - 1])) {
					ln_pr_if_s[i - 1] = ln_pr_if_s[i] - 10.0;
				}
			}

			ln_pr_if_s_query.add(ln_pr_if_s);
			return ret;
		}

		// 指定したマスの埋蔵量を取得する
		// 1マスなら正確な値がわかる
		int mine(int i, int j) {
			if (get_time() > 2.9) {
				System.err.println("!log timeup by mine");
				throw new RuntimeException("timeup");
			}
			if (rem == 0) {
				System.err.println("!log giveup by mine");
				throw new RuntimeException("giveup");
			}
			rem--;
			System.out.println("q 1 " + i + " " + j);
			System.out.flush();
			int ret = sc.nextInt();
			cost++;
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
			double b = res + 0.5;
			if (res == 0) {
				return normal_cdf(mean, std_dev, b);
			} else {
				double a = res - 0.5;
				return normal_cdf(mean, std_dev, b) - normal_cdf(mean, std_dev, a);
			}
		}

		// 累積分布関数
		// 平均mean, 標準偏差std_devに従う正規分布において、x以下の確率を求める
		double normal_cdf(double mean, double std_dev, double x) {
			return 0.5 * (1.0 + erf((x - mean) / (std_dev * Math.sqrt(2.0))));
		}

		/// 時間切れしたときはBFSで掘る。
		void giveup(List<IntDouble> elist) {
			System.err.println("!log giveup start");
			Deque<Point> que = new ArrayDeque<>();
			for (IntDouble id : elist) {
				que.addLast(new Point(id.i/n, id.i%n));
			}
			BitSet ans = new BitSet();
			int cnt = total;
			boolean[][] used = new boolean[n][n];
			while (!que.isEmpty()) {
				Point p = que.pollFirst();
				int i = p.r;
				int j = p.c;
				if (used[i][j]) continue;
				used[i][j] = true;
				int ret = mine(i, j);
				if (ret > 0) {
					ans.set(i * n + j);
					cnt -= ret;
					if (cnt == 0) break;
					for (int[] dij : DIJ) {
						int di = dij[0];
						int dj = dij[1];
						int i2 = i + di;
						int j2 = j + dj;
						if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < n) {
							que.addFirst(new Point(i2, j2));
						}
					}
				}
			}
			ans(ans);
			throw new RuntimeException("giveup");
		}

		// volumesとfailed_coordinatesが異なるかどうかを返す
//		boolean is_different(int[] volumes, int[] failed_coordinates) {
//			for (int ij : failed_coordinates) {
//				if (volumes[ij] == 0) {
//					return true;
//				}
//			}
//			return false;
//		}
		
		// 油田配置がtop_leftsにある場合、
		// q番目のクエリで占った油田集合の埋蔵量合計
		int get_query_volume(List<OilState> oil_states, int q, int[] top_lefts) {
			int S = 0;
			for (int oil_id = 0; oil_id < top_lefts.length; oil_id++) {
				OilState oil_state_p = oil_states.get(oil_id);
				int ij = top_lefts[oil_id];
				int p_volume = oil_state_p.top_left_query_volumes.get(ij).get(q);
				if (p_volume > 0) {
					S += p_volume;
				}
			}
			return S;
		}
		
		// 油田配置がこの状態になる確率を求める
		// vs: 各座標の埋蔵量
		// top_lefts: 油田の左上座標郡
		double get_ln_pR_if_x(List<OilState> oil_states, int[] volumes, int[] top_lefts, BitSet mask) {
			int key = mask.hashCode();
			List<BitSet> list = failedMap.get(key);
			if (list != null) {
				for (BitSet bs : list) {
					if (mask.equals(bs)) {
						return -1e20;
					}
				}
			}
			// 既に失敗した油田配置の集合に含まれる油田配置の場合、対数尤度を非常に小さい値にする
//			for (int[] failed_coordinates : failed) {
//				boolean skip = is_different(volumes, failed_coordinates);
//
//				if (!skip) {
//					int size = 0;
//					for (int ij = 0; ij < n2; ++ij) {
//						if (volumes[ij] > 0) {
//							size++;
//						}
//					}
//					if (size == failed_coordinates.length) {
//						return -1e20;
//					}
//				}
//			}

			// 今までのクエリ結果Rから、
			// 配置x=oil_statesにおける尤度P(R|x)を求める
			// 公式は以下の通り
			// P(R|x) = ΠP(ret|x) for ret in R
			// P(ret|x)は非常に小さい値でdoubleに収まらない可能性があるため、対数尤度を計算する
			// 対数に変形すると
			// log(P(R|x)) = Σlog(P(ret|x)) for ret in R
			// となる
			double ln_pR_if_x = 0.0;
			for (int q = 0; q < ln_pr_if_s_query.size(); q++) {
				// この油田配置において、q番目のクエリで占った油田の埋蔵量の合計を求める
				// q番目のクエリを打った時のlog(P(ret|S))は記録済みであり、
				// 配置xにおけるSを求めることで、
				// log(P(ret|x)) = log(P(ret|S))を求めることができる
				int S = get_query_volume(oil_states, q, top_lefts);
				ln_pR_if_x += ln_pr_if_s_query.get(q)[S];
			}
			return ln_pR_if_x;
		}

		double ln_prob_state(State state) {
			if (failedMap.size() > 0) {
				int key = state.mask.hashCode();
				List<BitSet> list = failedMap.get(key);
				if (list != null) {
					for (BitSet bs : list) {
						if (state.mask.equals(bs)) {
							return -1e20;
						}
					}
				}
			}
//			for (int[] failed_coordinates : failed) {
//				boolean skip = is_different(state.volumes, failed_coordinates);
//				if (!skip) {
//					int size = 0;
//					for (int ij = 0; ij < n2; ++ij) {
//						if (state.volumes[ij] > 0) {
//							++size;
//						}
//					}
//					if (size == failed_coordinates.length) {
//						return -1e20;
//					}
//				}
//			}
			double prob = 0.0;
			for (int q = 0; q < ln_pr_if_s_query.size(); ++q) {
				prob += ln_pr_if_s_query.get(q)[state.query_volumes.get(q)];
			}
			return prob;
		}
	}

	static class Query {
		boolean[] in_query; // ある位置の油の埋蔵量がクエリされているか : N*N個
		int[] volume; // 油田の埋蔵量のリスト : M個
		int coordinate_size; // クエリに含めるマスの数
		List<OilLayout> pool; // 油田の状態についてのリスト

		Query(Input input, List<OilLayout> pool) {
			this.in_query = new boolean[input.n2];
			this.volume = new int[pool.size()];
			this.coordinate_size = 0;
			this.pool = pool;
		}
		// 指定したマスをクエリに含めるかどうかを反転する
		void flip(int ij) {
			if (in_query[ij]) {
				in_query[ij] = false;
				for (int x = 0; x < pool.size(); x++) {
					volume[x] -= pool.get(x).volume[ij];
				}
				coordinate_size--;
			} else {
				in_query[ij] = true;
				for (int x = 0; x < pool.size(); x++) {
					volume[x] += pool.get(x).volume[ij];
				}
				coordinate_size++;
			}
		}
		// 占うマス集合を取得する
		List<Integer> get_coordinates() {
			List<Integer> result = new ArrayList<>();
			for (int ij = 0; ij < in_query.length; ij++) {
				if (in_query[ij]) {
					result.add(ij);
				}
			}
			return result;
		}
		// 占いの良さを評価する
		// add_k: クエリに含める座標の数. 0ならすでにインスタンスに含めているものだけ使う
		// add_v: クエリに含める座標の埋蔵量合計. 0ならすでにインスタンスに含めているものだけ使う
		double eval(Sim sim, int add_k, int add_v) {
			eval_cnt++;
			int k = coordinate_size + add_k;
			if (k == 0) return -1e20;
			// ln_pr[r] = クエリ結果がrとなる確率の対数
			// 最後にlogをとるまで、普通の確率として計算する
			Arrays.fill(sim.ln_pr_buffer, 0);
			for (int x = 0; x < pool.size(); x++) {
				int v = volume[x] + add_k;
				int lb = sim.precalc[k][v].lb;
				double[] probs = sim.precalc[k][v].pr_if_x;
//				while (ln_pr.size() < lb + probs.size()) {
//					ln_pr.add(0.0);
//				}
				double px = pool.get(x).px_if_R;
				// 公式 p(r)=Σp(r|x)p(x)
				// この公式を使って、p(r)を求める
				for (int pi = 0; pi < probs.length; pi++) {
					double pr_if_x = probs[pi];
					sim.ln_pr_buffer[lb + pi] += pr_if_x * px;
				}
			}
			for (int x = 0; x < sim.ln_pr_buffer.length; x++) {
				sim.ln_pr_buffer[x] = Math.log(sim.ln_pr_buffer[x] + 1e-20);
			}
			// I(X;R) = ΣΣp(x,r)log(p(x,r)/(p(x)p(r))
			//        = ΣΣp(r|x)p(x)log(p(r|x)p(x)/p(x)p(r))
			//        = ΣΣp(r|x)p(x)log(p(r|x)/p(r))
			//        = ΣΣp(r|x)p(x)(log(p(r|x))-log(p(r)))
			// この式を使って、相互情報量を求める
			double info = 0.0;
			for (int x = 0; x < pool.size(); x++) {
				double px = pool.get(x).px_if_R;
				int v = volume[x] + add_v;
				int lb = sim.precalc[k][v].lb;
				double[] probs = sim.precalc[k][v].pr_if_x;
				double[] probs2 = sim.precalc[k][v].ln_pr_if_x;
				for (int pi = 0; pi < probs.length; pi++) {
					double pr_if_x = probs[pi];
					double ln_pr_if_x = probs2[pi];
					double ln_prr = sim.ln_pr_buffer[lb + pi];
					info += pr_if_x * px * (ln_pr_if_x - ln_prr);
				}
			}
			// コストは1/sqrt(k)なので、
			// sqrt(k)倍することは、コストで割ることと同じ
			// コストあたりの相互情報慮を求める
			double cost = 1.0 / Math.sqrt(k);
			return info / cost;
		}
	}

	// プールの確率を正規化する
	static void normalize_pool(List<OilLayout> pool) {
		double total = 0;
		for (OilLayout layout : pool) {
			total += layout.px_if_R;
		}
		for (OilLayout layout : pool) {
			layout.px_if_R /= total;
		}
	}

	// プールの油田配置の全座標の埋蔵量を計算する
	static void set_volume(List<OilLayout> pool, Input input) {
		for (OilLayout layout : pool) {
			if (layout.volume == null) {
				layout.volume = input.get_volume(layout.top_lefts);
			}
			if (layout.mask == null) {
				layout.mask = input.get_positives(layout.top_lefts);
			}
		}
	}

	// プールを小さくして探索範囲を狭める
	static void concat_pool(List<OilLayout> pool, Input input, int ITER) {
		double tmp1 = ITER * 0.01;
		double tmp2 = Math.min(3.0 - get_time(), 1.0);
		int size = Math.min(pool.size(), Math.max((int)(tmp1 * tmp2), 2));
		// 尤度が低すぎる要素を削除
		while (size > 2 && pool.get(0).px_if_R * 1e-4 > pool.get(size - 1).px_if_R) {
			size--;
		}
		while (pool.size() > size) {
			pool.remove(pool.size() - 1);
		}
	}
	
	// 占いクエリを取得する
	static List<Integer> getDivinationQuery(Input input, List<OilLayout> pool, Sim sim) {
		int size = pool.size();
		// 全ての油田配置が同じ埋蔵量を持つかどうかを座標ごとに調べる
		boolean[] same = new boolean[input.n2];
		for (int x = 1; x < size; x++) {
			OilLayout layout = pool.get(x);
			for (int ij = 0; ij < input.n2; ij++) {
				same[ij] = same[ij] && layout.volume[ij] == pool.get(0).volume[ij];
			}
		}

		// クエリを作成
		Query query = new Query(input, pool);
		// 全ての配置が同じ埋蔵量だと想定される座標以外で1点だけ占うクエリを評価する
		List<Double> query_coordinate_evals = new ArrayList<>();
		for (int ij = 0; ij < input.n2; ij++) {
			if (!same[ij]) {
				// 評価前後にflipを挟むことで、
				// この座標1個だけでクエリを作成するときの情報量を計算する
				query.flip(ij); // クエリにこの座標を含める
				double ev = query.eval(sim, 0, 0); // クエリにこの座標を含めたときの情報量を計算
				query.flip(ij); // クエリにこの座標を含めない
				query_coordinate_evals.add(ev);
			}
		}
		List<Integer> query_coordinate_idx = new ArrayList<>();
		for(int ij = 0; ij < input.n2; ij++) {
			query_coordinate_idx.add(ij);
		}
		query_coordinate_idx.sort((a, b) -> Double.compare(query_coordinate_evals.get(b), query_coordinate_evals.get(a)));

		// 全ての配置が同じ埋蔵量だと想定される座標をクエリに含める
		List<Integer> no_info_coordinates = new ArrayList<>();
		for (int ij = 0; ij < input.n2; ij++) {
			if (same[ij]) {
				no_info_coordinates.add(ij);
			}
		}

		// クエリに含める座標をランダムにソート
		List<IntDouble> evaluation_values = new ArrayList<>();
		for (int ij : no_info_coordinates) {
			int evaluation_value = pool.get(0).volume[ij] * 1000 + rng.randrange(1000);
			evaluation_values.add(new IntDouble(ij, evaluation_value));
		}

		Collections.sort(evaluation_values);
		for (int i = 0; i < evaluation_values.size(); i++) {
			no_info_coordinates.set(i, evaluation_values.get(i).i);
		}

		long st2=System.currentTimeMillis();
		double crt = -1e20;
		int add_k = 0; // 情報量0のマスを追加する数
		int add_v = 0; // 情報量0のマスを追加することで増える埋蔵量
		OilLayout best_layout = pool.get(0);
		for (int iter = 0; iter < 3; iter++) {
			boolean change = false;
			long ed2 = System.currentTimeMillis();
			for (int ij : query_coordinate_idx) {
				ed2 = System.currentTimeMillis();
//				if (ed2-st2 > 100) {
//					System.err.println("Timeout "+(ed2-st2)+"ms");
//					break;
//				}
				query.flip(ij);
				double eval = query.eval(sim, add_k, add_v);
				if (eval > crt) {
					crt = eval;
					change = true;
				} else {
					query.flip(ij);
				}
			}
			{
				// 情報量0のマスの追加
				while (add_k < no_info_coordinates.size()) {
					double eval = query.eval(sim, add_k + 1, add_v + best_layout.volume[no_info_coordinates.get(add_k)]);
					if (crt < eval) {
						crt = eval;
						add_v += pool.get(0).volume[no_info_coordinates.get(add_k)];
						add_k += 1;
						change = true;
					} else {
						break;
					}
				}
				while (add_k > 0) {
					double eval = query.eval(sim, add_k - 1, add_v - best_layout.volume[no_info_coordinates.get(add_k - 1)]);
					if (crt < eval) {
						crt = eval;
						add_v -= pool.get(0).volume[no_info_coordinates.get(add_k - 1)];
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
//			if (ed2-st2 > 100) break;
		}
		List<Integer> query_coordinates = query.get_coordinates();
		while (no_info_coordinates.size() > add_k) {
			no_info_coordinates.remove(no_info_coordinates.size() - 1);
		}
		query_coordinates.addAll(no_info_coordinates);
		return query_coordinates;
	}
	
	static void sort_pool(List<OilLayout> pool) {
		Collections.sort(pool);
	}

	static double[][] calculatePxMap(int N, List<OilLayout> pool) {
	    double[][] entropyMap=new double[N][N];
	    double totalWeight=0;
	    // 1. 各マスの「油がある期待値」を計算
	    double[][] probMap=new double[N][N];
	    for (OilLayout ol : pool) {
	        double weight=ol.px_if_R; // 対数尤度を実数に戻す
	        totalWeight+=weight;
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
	                if (ol.volume[y * N + x] > 0) {
	                    probMap[y][x]+=weight;
	                }
	            }
	        }
	    }
	    // 2. エントロピーに変換
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
	            double p=probMap[y][x]/totalWeight;
	            entropyMap[y][x]=p;
	        }
	    }
	    return entropyMap;
	}
	static double[][] calculateBinaryEntropyMap(int N, List<OilLayout> pool) {
	    double[][] entropyMap=new double[N][N];
	    double totalWeight=0;
	    // 1. 各マスの「油がある期待値」を計算
	    double[][] probMap=new double[N][N];
	    for (OilLayout ol : pool) {
	        double weight=ol.px_if_R; // 対数尤度を実数に戻す
	        totalWeight+=weight;
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
	                if (ol.volume[y * N + x] > 0) {
	                    probMap[y][x]+=weight;
	                }
	            }
	        }
	    }
	    // 2. エントロピーに変換
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
	            double p=probMap[y][x]/totalWeight;
	            entropyMap[y][x]=calculateBinaryEntropy(p);
	        }
	    }
	    return entropyMap;
	}
	static double calculateBinaryEntropy(double p) {
	    if (p<=0 || p>=1) return 0; // 確信している状態
	    return -(p*Math.log(p)+(1-p)*Math.log(1-p))/Math.log(2);
	}
	static List<IntDouble> sort(int N, double[][] aad) {
		List<IntDouble> list=new ArrayList<>();
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				list.add(new IntDouble(y * N + x, aad[y][x]));
			}
		}
		Collections.sort(list);
		return list;
	}

	// vector<vector<vector<pair<size_t, size_t>>>>のvector<pair<size_t, size_t>>部分
	static class Cell {
		List<int[]> list = new ArrayList<>();
	}
	static void simulated_annealing(Input input, Sim sim, State state, List<OilLayout> pool, Cell[][] swaps, HashMap<Long, Double> hash_ln_lilelihood, int ITER) {
		// 一番尤度の高い配置からスタートし、焼き鈍し法を実行することで配置候補を沢山生成する
		// 途中過程で見つけた配置は、よほど小さいもの以外は全てpoolに入れる
		double bef_time = get_time();
		double crt = pool.get(0).ln_pR_if_x;
		for (int oil_id = 0; oil_id < input.m; oil_id++) {
			state.move_to(oil_id, pool.get(0).top_lefts[oil_id]);
		}
		double max_crt = crt;
		double T0 = 2.0;
		double T1 = 1.0;
		// TLEにならないように残り時間が少なくなったら反復回数を減らす
		ITER = (int)(ITER * Math.min(3.0 - get_time(), 1.0));
		for (int t = 0; t < ITER; ++t) {
			double temp = T0 + (T1 - T0) * t / (double)ITER;

			int slide_threshold = 30;
			int warp_threshold = slide_threshold + 10;
			int swap_threshold = warp_threshold + 60;
			int coin = rng.randrange(swap_threshold);
			if (coin < slide_threshold) {
				// ポリオミノをランダムに選び、上下左右に1マス移動
				int oil_id = rng.randrange(input.m);
				OilShape oil = input.oils[oil_id];
				int[] dij = DIJ[rng.randrange(4)];
				int di = dij[0];
				int dj = dij[1];
				int i2 = state.top_lefts[oil_id] / input.n + di;
				int j2 = state.top_lefts[oil_id] % input.n + dj;
				if (i2 >= 0 && i2 < input.n - oil.max_i && j2 >= 0 && j2 < input.n - oil.max_j) {
					int bk = state.top_lefts[oil_id];
					state.move_to(oil_id, i2 * input.n + j2);
					// 移動後の配置の対数尤度を計算
					// 既に計算している場合はその値を使用
					// zobrist hashを使用しているので、既に計算しているかどうかはO(1)で判定可能
					double next = hash_ln_lilelihood.containsKey(state.hash) ? hash_ln_lilelihood.get(state.hash) : sim.ln_prob_state(state);
					if (!hash_ln_lilelihood.containsKey(state.hash)) {
						hash_ln_lilelihood.put(state.hash, next);
						// -∞になるときはpoolに入れない
						// これはquery関数でprobを計算したときに-∞になるときに隣のprob-10.0としたことで計算可能にしている
						if (next - max_crt >= -10.0) {
							pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes, state.mask));
						}
					}
					if (crt <= next || rng.gen_bool(Math.exp((next - crt) / temp))) {
						crt = next;
					} else {
						state.move_to(oil_id, bk);
					}
				}
			} else if (coin < warp_threshold) {
				// ポリオミノをランダムに選び、ランダムな位置に移動
				int oil_id = rng.randrange(input.m);
				OilShape oil = input.oils[oil_id];
				int i2 = rng.randrange(input.n - oil.max_i);
				int j2 = rng.randrange(input.n - oil.max_j);
				int bk = state.top_lefts[oil_id];
				state.move_to(oil_id, i2 * input.n + j2);
				double next = hash_ln_lilelihood.containsKey(state.hash) ? hash_ln_lilelihood.get(state.hash) : sim.ln_prob_state(state);
				if (!hash_ln_lilelihood.containsKey(state.hash)) {
					hash_ln_lilelihood.put(state.hash, next);
					if (next - max_crt >= -10.0) {
						pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes, state.mask));
					}
				}
				if (crt <= next || rng.gen_bool(Math.exp((next - crt) / temp))) {
					crt = next;
				} else {
					state.move_to(oil_id, bk);
				}
			} else {
				// ポリオミノを2つランダムに選び、互いの位置を交換
				int oil_id_a = rng.randrange(input.m);
				OilShape oil_a = input.oils[oil_id_a];
				int oil_id_b = rng.randrange(input.m);
				OilShape oil_b = input.oils[oil_id_b];
				if (oil_id_a == oil_id_b) {
					continue;
				}
				int ai = state.top_lefts[oil_id_a] / input.n;
				int aj = state.top_lefts[oil_id_a] % input.n;
				int bi = state.top_lefts[oil_id_b] / input.n;
				int bj = state.top_lefts[oil_id_b] % input.n;
				int[] daij = swaps[oil_id_b][oil_id_a].list.get(rng.randrange(swaps[oil_id_a][oil_id_b].list.size()));
				int dai = daij[0];
				int daj = daij[1];
				int[] dbij = swaps[oil_id_a][oil_id_b].list.get(rng.randrange(swaps[oil_id_b][oil_id_a].list.size()));
				int dbi = dbij[0];
				int dbj = dbij[1];
				int ai2 = bi + dai;
				int aj2 = bj + daj;
				if (ai2 < 0 || ai2 >= input.n - oil_a.max_i || aj2 < 0 || aj2 >= input.n - oil_a.max_j) {
					continue;
				}
				int bi2 = ai + dbi;
				int bj2 = aj + dbj;
				if (bi2 < 0 || bi2 >= input.n - oil_b.max_i || bj2 < 0 || bj2 >= input.n - oil_b.max_j) {
					continue;
				}
				state.move_to(oil_id_a, ai2 * input.n + aj2);
				state.move_to(oil_id_b, bi2 * input.n + bj2);
				double next = hash_ln_lilelihood.containsKey(state.hash) ? hash_ln_lilelihood.get(state.hash) : sim.ln_prob_state(state);
				if (!hash_ln_lilelihood.containsKey(state.hash)) {
					hash_ln_lilelihood.put(state.hash, next);
					if (next - max_crt >= -10.0) {
						pool.add(new OilLayout(state.hash, next, 0.0, state.top_lefts, state.volumes, state.mask));
					}
				}
				if (crt <= next || rng.gen_bool(Math.exp((next - crt) / temp))) {
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
		sort_pool(pool);
	}

	static Cell[][] get_swaps(Input input) {
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

		Cell[][] swaps = new Cell[input.m][input.m];
		for (int oil_id_a = 0; oil_id_a < input.m; ++oil_id_a) {
			OilShape oil_a = input.oils[oil_id_a];
			// ポリオミノpを0,0に配置したときの座標ならtrue, それ以外ならfalse
			boolean[] is_a_coordinate = new boolean[input.n2];
			for (int ij : oil_a.coordinate_ids) {
				is_a_coordinate[ij] = true;
			}
			for (int oil_id_b = 0; oil_id_b < input.m; ++oil_id_b) {
				OilShape oil_b = input.oils[oil_id_b];
				if (oil_id_a == oil_id_b) {
					continue;
				}
				// 二つのポリオミノが重なっているマスの数と、そのときの位置のずれのリストを計算
				List<IntAryInt> list = new ArrayList<>();
				for (int di = -(int)oil_b.max_i; di <= (int)oil_a.max_i; ++di) {
					for (int dj = -(int)oil_b.max_j; dj <= (int)oil_a.max_j; ++dj) {
						int volume = 0;
						for (int ij : oil_b.coordinate_ids) {
							int i = ij / input.n + di;
							int j = ij % input.n + dj;
							if (i >= 0 && j >= 0 && i < input.n && j < input.n && is_a_coordinate[i * input.n + j]) {
								volume++;
							}
						}
						int[] ary = {di, dj};
						list.add(new IntAryInt(ary, volume));
					}
				}
				// 重なっているマスの数が多い順にソート
				Collections.sort(list);
				// 4個である必要はないが、近傍の多様性のために複数持っておく
				while (list.size() > 4) {
					list.remove(list.size() - 1);
				}
				swaps[oil_id_a][oil_id_b] = new Cell();
				for (IntAryInt iai : list) {
					swaps[oil_id_a][oil_id_b].list.add(iai.ary);
				}
			}
		}
		return swaps;
	}

	static void solve(Scanner sc) {
		Input input = read_input(sc);

//		if (input.m != 2) {
//			Solve17 s = new Solve17(input);
//			s.solve(sc);
//			return;
//		}

		// 2つのポリオミノの位置を入れ替える操作を行うために、入れ替えた際にどれだけ位置をずらせばよいかを予め計算しておく
		// swaps[p][q] := pとq+Δが出来るだけ一致するようなΔ
		Cell[][] swaps = get_swaps(input);

		Sim sim = new Sim(sc, input);
		State state = new State(input);
//		state.volumes = input.get_volume(state.top_lefts);
//		state.mask = input.get_positives(state.top_lefts);
//		System.err.println(get_time()+":state="+state);
		List<OilLayout> pool = new ArrayList<>();
		int ITER = 4000*1000 / (2 * input.n2);
//		int ITER = 8000*1000 / (2 * input.n2);
		for (int t = 0;; t++) {
			if (sim.rem == 0) {
				System.err.println("!There is no more query");
				break;
			}
			if (get_time() > 2.8) {
//				double[][] emap=calculateBinaryEntropyMap(input.n, pool);
				double[][] emap=calculatePxMap(input.n, pool);
//				for (int y=0; y<input.n; y++) {
//					System.err.println(Arrays.toString(emap[y]));
//				}
				List<IntDouble> elist=sort(input.n, emap);
//				System.err.println(elist);
				sim.giveup(elist);
				break;
			}

			// 各配置の対数尤度を更新する
			// t=0のときはpoolになにも入っていないので何もしない
			for (OilLayout layout : pool) {
//				if (layout.volume == null && !sim.failed.isEmpty()) {
//					layout.volume = input.get_volume(layout.top_lefts);
//				}
				layout.ln_pR_if_x = sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts, layout.mask);
			}
			// 同じ尤度の配置を散らすためにシャッフル
			Collections.shuffle(pool);
			// 対数尤度が高い順に配置候補をソート
			sort_pool(pool);
			// プールに存在する配置と対数尤度を記録しておく
			HashMap<Long, Double> hash_ln_lilelihood = new HashMap<>();
			for (OilLayout layout : pool) {
				hash_ln_lilelihood.put(layout.hash, layout.ln_pR_if_x);
			}

			if (t == 0) {
				// 1ターン目は全ての配置が等確率なので、ランダムに候補を生成する
				for (int i_ = 0; i_ < ITER; i_++) {
					for (int oil_id = 0; oil_id < input.m; oil_id++) {
						OilShape oil = input.oils[oil_id];
						int i = rng.randrange(input.n - oil.max_i);
						int j = rng.randrange(input.n - oil.max_j);
						state.move_to(oil_id, i * input.n + j);
					}
					// まだプールに追加していない配置なら追加する
					if (!hash_ln_lilelihood.containsKey(state.hash)) {
//						System.err.println("i_="+i_+":state.hash="+state.hash);
						hash_ln_lilelihood.put(state.hash, 0.0);
						pool.add(new OilLayout(state.hash, 0.0, 0.0, state.top_lefts, state.volumes, state.mask));
					}
				}
//				System.err.println(get_time()+":pool.size="+pool.size());
//				System.err.println(get_time()+":state="+state);
			} else {
				simulated_annealing(input, sim, state, pool, swaps, hash_ln_lilelihood, ITER);
			}

			// この時点でpoolはソート済み
			double max_prob = pool.get(0).ln_pR_if_x;
			// 対数尤度から尤度に戻す
			// p(R|x) = exp(log(p(R|x)))
			for (OilLayout layout : pool) {
				layout.px_if_R = Math.exp(layout.ln_pR_if_x - max_prob);
			}

			// 尤度から事後確率に変換する
			// p(x|R) = p(R|x)/Σp(R|x)
			normalize_pool(pool);

			// 尤度が低すぎる配置を削除
			while (pool.size() > 1 && pool.get(pool.size() - 1).px_if_R < 1e-9) {
				pool.remove(pool.size() - 1);
			}
//			System.err.println(get_time()+":pool.size="+pool.size());
			OilLayout best_layout = pool.get(0);
//			BitSet best_bits = best_layout.mask;
//			BitSet best_bits = input.get_positives(best_layout.top_lefts);

			double best_pool_prob = best_layout.px_if_R;
			concat_pool(pool, input, ITER);
			// poolを切り取ったことで合計100%じゃなくなったので再度正規化
			normalize_pool(pool);
			// 油田配置の全座標の埋蔵量を計算
			set_volume(pool, input);
			BitSet best_bits = best_layout.mask;

//			System.err.println(get_time()+":pool.size="+pool.size());
			boolean bHit=true;
			{
				double[][] emap=calculatePxMap(input.n, pool);
//				for (int y=0; y<input.n; y++) {
//					System.err.println(Arrays.toString(emap[y]));
//				}
				List<IntDouble> elist=sort(input.n, emap);
				while (elist.size()>0 && elist.get(elist.size()-1).v<0.9) elist.remove(elist.size()-1);
//				System.err.println(elist);
				if (best_bits.cardinality()!=elist.size()) bHit=false;
				else {
					for (IntDouble id : elist) {
						if (!best_bits.get(id.i)) bHit=false;
					}
				}
			}
			long elap = System.currentTimeMillis();
			if (!RELEASE)
				System.err.println(
						(t + 1) + "(" + (elap - start) + "):size="+pool.size()
						+ " id=" + best_layout.id + " px_if_R=" + best_pool_prob);

			if (bHit || best_pool_prob > 0.8) {
				// 自信があるとき、推測をう
				// Mが大きい時は焼き鈍しに失敗して真の解を見落としている可能性が高く推測に失敗することがあるので、
				// いきなり推測を行わず、一旦それらのマス以外に対して占いを実行
//				int[] T_vec = new int[best_bits.cardinality()];
//				int idx = 0;
//				for (int ij = 0; ij < input.n2; ij++) {
//					if (best_bits.get(ij)) {
//						T_vec[idx] = ij;
//					}
//				}
//				List<Integer> T_vec = new ArrayList<>();
//				List<Integer> T_vec_reverse = new ArrayList<>();
//				for (int ij = 0; ij < input.n2; ij++) {
//					if (best_bits.get(ij)) {
//						T_vec.add(ij);
//					} else {
//						T_vec_reverse.add(ij);
//					}
//				}
//				for (int i = best_bits.nextSetBit(0); i >= 0; i = best_bits.nextSetBit(i + 1)) {
//					T_vec.add(i);
//				}
				BitSet T_vec = best_bits;
//				if (input.m <= 4 ||
//						(sim.queries.size() > 0 && sim.queries.get(sim.queries.size() - 1).coords.equals(T_vec_reverse))) {
					if (sim.ans(T_vec)) {
						break;
					} else if (sim.failedMap.size() == 1) {
						state.volumes = input.get_volume(state.top_lefts);
						state.mask = input.get_positives(state.top_lefts);
					}
//				} else {
//					sim.query(T_vec_reverse);
//					state.add_query(T_vec_reverse);
//				}
			} else {
				List<Integer> query_coordinates = getDivinationQuery(input, pool, sim);
				sim.query(query_coordinates);
				state.add_query(query_coordinates);
			}
			iteration++;
		}
	}

	static class Solve17 {
		static final boolean DEBUG = false;
		static final boolean RELEASE = true;
		int N;
		int M;
		int V;
		Solve17(Input in) {
			N = in.n;
			M = in.m;
			V = in.total;
		}

		void print(int y, int x) {
			System.out.println("q 1 " + y + " " + x);
			System.out.flush();
		}

		void print(List<String> list) {
			System.out.print("a " + list.size());
			for (String s : list) {
				System.out.print(" " + s);
			}
			System.out.println();
			System.out.flush();
		}

		void solve(Scanner sc) {
//			Scanner sc=new Scanner(System.in);
//			init(sc);
			int T = 2 * N * N;
//			System.err.println("T="+T);
//			double cost=0;
			int[][] flag = new int[N][N];
			for (int y = 0; y < N; y++) {
				Arrays.fill(flag[y], -1);
			}
			Deque<Point> que = new ArrayDeque<>();
			que.add(new Point(N / 2, N / 2));
			int cnt = 0;
			int t = 0;
			while (que.size() > 0) {
				Point p = que.poll();
				int y = p.r;
				int x = p.c;
				if (flag[y][x] >= 0)
					continue;
				print(y, x);
				int rc = sc.nextInt();
				if (!RELEASE)
					System.err.println((t + 1) + ":" + y + "," + x + ",rc=" + rc);
				flag[y][x] = rc;
				cnt += rc;
				cost += 1;
				t++;
				if (t >= T)
					break;
				if (cnt == V)
					break;
				for (int d = 0; d < DY.length; d++) {
					int ny = y + DY[d];
					int nx = x + DX[d];
					if (ny >= 0 && ny < N && nx >= 0 && nx < N && flag[ny][nx] < 0) {
						if (rc == 0)
							que.addLast(new Point(ny, nx));
						else
							que.addFirst(new Point(ny, nx));
					}
				}
				iteration++;
			}
			List<String> list = new ArrayList<>();
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (flag[y][x] > 0)
						list.add(y + " " + x);
				}
			}
			print(list);
			int rc = sc.nextInt();
			iteration++;
			if (rc == 1)
				return;
			cost++;
		}

		static final int[] DY = { -1, 1, 0, 0 }; // U,D,L,R
		static final int[] DX = { 0, 0, -1, 1 }; // U,D,L,R
		static final String DS = "UDLR";
	}
	public static void main(String[] args) {
		if (null != System.getProperty("RELEASE")) RELEASE = true;
		start = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		try {
			solve(sc);
		} catch (Exception e) {
			if (!RELEASE) e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long score = Math.round(1e6 * cost);
		if (!RELEASE) {
			System.err.println("--- Result ---");
			System.err.println("iteration: " + iteration);
			System.err.println("score    : " + score);
		}
		{
			System.err.println("elaps    : " + (end-start));
			System.err.println("miss     : " + miss);
			System.err.println("eval_cnt : " + eval_cnt);
		}
	}
	static long start;
	static int iteration = 0;
	static double cost=0;
	static int miss=0;
	static int eval_cnt=0;
}

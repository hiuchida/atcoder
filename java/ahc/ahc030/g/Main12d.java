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
		// 油田配置をこの配置xだと過程したとき
		// 今までのクエリ結果Rが得られる確率の対数.
		// 対数尤度ともいう。
		double ln_pR_if_x;
		// 今までのクエリの結果Rから計算した,この配置になる事後確率P(x|R)
		double px_if_R;
		int[] top_lefts; // 油田の左上の座標
		int[] volume; // ある位置の油の埋蔵量
		OilLayout(double ln, double px, int[] tls, int[] vol) {
			this.ln_pR_if_x = ln;
			this.px_if_R = px;
			this.top_lefts = tls.clone();
			this.volume = (vol != null) ? vol.clone() : null;
		}
		@Override
		public int compareTo(OilLayout that) {
			return -1*Double.compare(this.ln_pR_if_x, that.ln_pR_if_x);
		}
	}

	// 油田の形についての情報
	static class OilShape {
		int max_i, max_j; // 油田が収まる正方形の大きさ
		int[] coordinate_ids; // 座標(i,j)の組を1変数で表したもの
//		List<Integer> coordinate_ids = new ArrayList<>(); // 座標(i,j)の組を1変数で表したもの
		// read_input()内しか使わないため、coordinatesは未使用
//		List<Point> coordinates = new ArrayList<>(); // 座標(i,j)の組
		// 座標(i,j)の組をマスクしたもの
		// 島の大きさN<=20なので、20*20のビットセットで表現できる
		// javaではBitSetのシフトができないため、maskは未使用
//		BitSet mask = new BitSet(20 * 20);
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
		}
		input.total = 0;
		for (int oil_id = 0; oil_id < input.m; oil_id++) {
			input.total += input.oils[oil_id].coordinate_ids.length;
//			input.total += input.oils[oil_id].coordinate_ids.size();
		}

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
		OilState(Input input) {
			this.top_left_query_volumes = new ArrayList<>(input.n2);
			for (int i = 0; i < input.n2; i++) {
				this.top_left_query_volumes.add(new ArrayList<>());
			}
		}
	}

	/**
	 * 全体の配置状態を管理するクラス
	 */
	static class State {
		List<OilState> oil_states; // 油田の状態リスト (M個)
		int[] top_lefts; // 各油田の現在の左上座標ID (M個)
		int[] volumes; // 各マスの現在の油の埋蔵量 (N*N個)
		List<Integer> query_volumes; // 各クエリ(q番目)で占った座標集合の現在の埋蔵量合計
		Input input;

		// 全ての油田が(0,0)にある状態を初期状態とする
		State(Input input) {
			this.input = input;
			this.oil_states = new ArrayList<>(input.m);
			for (int oil_id = 0; oil_id < input.m; oil_id++) {
				this.oil_states.add(new OilState(input));
			}
			this.top_lefts = new int[input.m]; // 初期値は 0 (座標 (0,0))
			this.volumes=null;
			this.query_volumes=null;
		}

		// 油田oil_idをnew_top_leftに移動する
		// 移動自体はtop_leftsの更新だが、
		// 移動に伴い、query_volumes,volumesを更新する
		void move_to(int oil_id, int new_top_left) {
			OilState oil_state = oil_states.get(oil_id);
			int old_top_left = top_lefts[oil_id];
			if (query_volumes != null) {
				for (int q = 0; q < query_volumes.size(); q++) {
					query_volumes.set(q,
							query_volumes.get(q)
							+ oil_state.top_left_query_volumes.get(new_top_left).get(q)
							- oil_state.top_left_query_volumes.get(old_top_left).get(q));
				}
			}
			if (volumes != null) {
				for (int ij : input.oils[oil_id].coordinate_ids) {
					volumes[ij + old_top_left]--;
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
	}

	// 過去の占いの(油田配置、占い結果)の集合
	// vector<pair<vector<size_t>, size_t>> queries;
	static class QueryHistory {
		List<Integer> coords;
		int ret;
		QueryHistory(List<Integer> coords, int ret) {
			this.coords=coords;
			this.ret=ret;
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
		List<QueryHistory> queries=new ArrayList<>();
		// 既に油田配置を答えるクエリを投げて失敗した油田配置の集合
		List<int[]> failed = new ArrayList<>();
		// クエリサイズk、埋蔵量総量Sに対して、
		// pr_if_xがもつrの値の下限
//		int[][] pr_if_x_lb;
		// 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
		// 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
		// クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
		// 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
		// pr_if_x[k][S][r-lb] = (prob, log(prob))
//		List<Map<Integer, List<double[]>>> pr_if_x; 
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
		}

		// 油田配置をあてるクエリを投げる
		// 失敗した場合は失敗した座標郡をfailedに記録する
		boolean ans(List<Integer> T) {
			if (get_time() > 2.9) {
				System.err.println("!log timeup by ans");
				throw new RuntimeException("timeup");
			}
			if (rem == 0) {
				System.err.println("!log giveup by ans");
				throw new RuntimeException("giveup");
			}
			rem--;
			System.out.print("a " + T.size());
			for (int ij : T) {
				System.out.print(" " + (ij / n) + " " + (ij % n));
			}
			System.out.println();
			System.out.flush();
			int ret = sc.nextInt();
			if (ret == 1) return true;
			cost++;
			miss++;
			{ //failed.add(T);
				int[] ary = new int[T.size()];
				for (int i = 0; i < T.size(); i++) {
					ary[i] = T.get(i);
				}
				failed.add(ary);
			}
			return false;
		}

		boolean ans(int[] T) {
			if (get_time() > 2.9) {
				System.err.println("!log timeup by ans");
				throw new RuntimeException("timeup");
			}
			if (rem == 0) {
				System.err.println("!log giveup by ans");
				throw new RuntimeException("giveup");
			}
			rem--;
			System.out.print("a " + T.length);
			for (int ij : T) {
				System.out.print(" " + (ij / n) + " " + (ij % n));
			}
			System.out.println();
			System.out.flush();
			int ret = sc.nextInt();
			if (ret == 1) return true;
			cost++;
			miss++;
			failed.add(T);
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
			queries.add(new QueryHistory(query_coordinates, ret));
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
		void giveup() {
			System.err.println("!log giveup start");
			Deque<Point> que = new ArrayDeque<>();
			que.add(new Point(n / 2, n / 2));
			List<Integer> list = new ArrayList<>();
			int rem = total;
			boolean[][] used = new boolean[n][n];
			while (!que.isEmpty()) {
				Point p = que.pollFirst();
				int i = p.r;
				int j = p.c;
				if (used[i][j]) continue;
				used[i][j] = true;
				int ret = mine(i, j);
				if (ret > 0) {
					list.add(i * n + j);
					rem -= ret;
					if (rem == 0) break;
				}
				for (int[] dij : DIJ) {
					int di = dij[0];
					int dj = dij[1];
					int i2 = i + di;
					int j2 = j + dj;
					if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < n) {
						if (ret == 0) {
							que.addLast(new Point(i2, j2));
						} else {
							que.addFirst(new Point(i2, j2));
						}
					}
				}
			}
			ans(list);
			{ //ans(list);
//				int[] ary = new int[list.size()];
//				int idx = 0;
//				for (int i = 0; i < list.size(); i++) {
//					ary[i] = list.get(i);
//				}
//				ans(ary);
			}
			throw new RuntimeException("giveup");
		}

		// volumesとfailed_coordinatesが異なるかどうかを返す
		boolean is_different(int[] volumes, int[] failed_coordinates) {
			for (int ij : failed_coordinates) {
				if (volumes[ij] == 0) {
					return true;
				}
			}
			return false;
		}
		
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
		double get_ln_pR_if_x(List<OilState> oil_states, int[] volumes, int[] top_lefts) {
			// 既に失敗した油田配置の集合に含まれる油田配置の場合、対数尤度を非常に小さい値にする
			for (int[] failed_coordinates : failed) {
				boolean skip = is_different(volumes, failed_coordinates);

				if (!skip) {
					int size = 0;
					for (int ij = 0; ij < n2; ++ij) {
						if (volumes[ij] > 0) {
							size++;
						}
					}
					if (size == failed_coordinates.length) {
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
			for (int q = 0; q < queries.size(); q++) {
				// この油田配置において、q番目のクエリで占った油田の埋蔵量の合計を求める
				// q番目のクエリを打った時のlog(P(ret|S))は記録済みであり、
				// 配置xにおけるSを求めることで、
				// log(P(ret|x)) = log(P(ret|S))を求めることができる
				int S = get_query_volume(oil_states, q, top_lefts);
				ln_pR_if_x += ln_pr_if_s_query.get(q)[S];
			}
			return ln_pR_if_x;
		}
	}

	static class Query {
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
//	static void set_volume(List<OilLayout> pool, Input input) {
//		for (OilLayout layout : pool) {
//			layout.volume = input.get_volume(layout.top_lefts);
//		}
//	}

	// 占いクエリを取得する
	static List<Integer> getDivinationQuery(Input input, List<OilLayout> pool) {
		// クエリを作成
		List<Integer> query_coordinates = new ArrayList<>();
		for (int ij = 0; ij < input.n2; ++ij) {
			if (rng.gen_bool(0.5)) {
				query_coordinates.add(ij);
			}
		}
		assert(query_coordinates.size() > 0);
		return query_coordinates;
	}
	
	static void sort_pool(List<OilLayout> pool) {
		Collections.sort(pool);
	}

	static void solve(Scanner sc) {
		Input input = read_input(sc);

		if (input.m != 2) {
			Solve17 s = new Solve17(input);
			s.solve(sc);
			return;
		}

		Sim sim = new Sim(sc, input);
		State state = new State(input);
		List<OilLayout> pool = new ArrayList<>();
		int ITER = 4000*1000 / (2 * input.n2);
		// 全ての配置を生成
		assert(input.m == 2);
		OilShape oil_a = input.oils[0];
		OilShape oil_b = input.oils[1];
		for (int i_a = 0; i_a < input.n - oil_a.max_i; i_a++) {
			for (int j_a = 0; j_a < input.n - oil_a.max_j; j_a++) {
				state.move_to(0, i_a * input.n + j_a);
				for (int i_b = 0; i_b < input.n - oil_b.max_i; i_b++) {
					for (int j_b = 0; j_b < input.n - oil_b.max_j; j_b++) {
						state.move_to(1, i_b * input.n + j_b);
						pool.add(new OilLayout(0.0, 0.0, state.top_lefts, state.volumes));
					}
				}
			}
		}

		for (int t = 0;; t++) {
			if (sim.rem == 0) {
				System.err.println("!There is no more query");
				break;
			}
			if (get_time() > 2.8) {
				sim.giveup();
				break;
			}

			// 各配置の対数尤度を更新する
			// t=0のときはpoolになにも入っていないので何もしない
			for (OilLayout layout : pool) {
				if (layout.volume == null && !sim.failed.isEmpty()) {
					layout.volume = input.get_volume(layout.top_lefts);
				}
				layout.ln_pR_if_x = sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts);
			}
			// 同じ尤度の配置を散らすためにシャッフル
			Collections.shuffle(pool);
			// 対数尤度が高い順に配置候補をソート
			sort_pool(pool);

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

			OilLayout best_layout = pool.get(0);
			BitSet best_bits = input.get_positives(best_layout.top_lefts);

			double best_pool_prob = best_layout.px_if_R;
			// layout.volume == null && !sim.failed.isEmpty()の時に生成するため、毎回生成するのはコスト
//			set_volume(pool, input);

			long elap = System.currentTimeMillis();
			if (!RELEASE)
				System.err.println(
						(t + 1) + "(" + (elap - start) + "):size="+pool.size()
						+ " id=" + best_layout.id + " px_if_R=" + best_pool_prob);

			if (best_pool_prob > 0.8) {
//				int[] T_vec = new int[best_bits.cardinality()];
//				int idx = 0;
//				for (int ij = 0; ij < input.n2; ij++) {
//					if (best_bits.get(ij)) {
//						T_vec[idx] = ij;
//					}
//				}
				List<Integer> T_vec = new ArrayList<>();
				for (int ij = 0; ij < input.n2; ij++) {
					if (best_bits.get(ij)) {
						T_vec.add(ij);
					}
				}
//				for (int i = best_bits.nextSetBit(0); i >= 0; i = best_bits.nextSetBit(i + 1)) {
//					T_vec.add(i);
//				}
				if (sim.ans(T_vec)) {
					break;
				} else if (sim.failed.size() == 1) {
					state.volumes = input.get_volume(state.top_lefts);
				}
			} else {
				List<Integer> query_coordinates = getDivinationQuery(input, pool);
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
			System.err.println("miss     : " + miss);
		}
		{
			System.err.println("elaps    : " + (end-start));
		}
	}
	static long start;
	static int iteration = 0;
	static double cost=0;
	static int miss=0;
}

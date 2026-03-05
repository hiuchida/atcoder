import java.util.*;

public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	
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
        // [0.0, 1.0]
        public double random() {
            long v=next();
            v=v & Long.MAX_VALUE;
            return (double)v/Long.MAX_VALUE;
        }
        public long next() {
            x ^= x << 13;
            x ^= x >>> 17;
            x ^= x << 5;
            return x;
        }
        public int randRange(int stop) {
            long v=next();
            v=v & Long.MAX_VALUE;
            return (int)(v % stop);
        }
        public boolean gen_bool(double p) {
//        	return rand.nextBoolean();
            double r = random();
            return r < p;
        }
    }
    static Xorshift rng = new Xorshift(1);

    static class OilLayout implements Comparable<OilLayout> {
    	int id=seq();
        double ln_pR_if_x;  // 対数尤度
        double px_if_R;    // 事後確率
        int[] top_lefts;
        int[] volume;
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

    static class OilShape {
        int max_i, max_j;
        List<Integer> coordinate_ids = new ArrayList<>();
        List<Point> coordinates = new ArrayList<>();
    }

    static class Input {
        int n;
        int n2;
        int m;
        double eps;
        OilShape[] oils;
        int total;

        // 指定した配置で埋蔵量1以上のマスを返す
        List<Integer> get_positives(int[] topLefts) {
            BitSet bits = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinate_ids) bits.set(id + topLefts[i]);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) res.add(i);
            return res;
        }

        // 指定した配置での各マスの埋蔵量を計算
        int[] get_volume(int[] topLefts) {
            int[] volume = new int[n2];
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinate_ids) {
                    volume[id + topLefts[i]]++;
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
        return (double)(now-start)/1000.0;
    }

    static Input read_input(Scanner sc) {
        Input in = new Input();
        in.n = sc.nextInt();
        in.m = sc.nextInt();
        in.eps = sc.nextDouble();
        in.n2 = in.n * in.n;
        in.oils = new OilShape[in.m];
        for (int i = 0; i < in.m; i++) {
            in.oils[i] = new OilShape();
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) {
                int r = sc.nextInt(), c = sc.nextInt();
                in.oils[i].coordinates.add(new Point(r, c));
                in.oils[i].coordinate_ids.add(r * in.n + c);
                in.oils[i].max_i = Math.max(in.oils[i].max_i, r);
                in.oils[i].max_j = Math.max(in.oils[i].max_j, c);
            }
            in.total += size;
        }
        return in;
    }

    static final double SMALL_VALUE = 1e-6;

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
        int[] top_lefts;           // 各油田の現在の左上座標ID (M個)
        int[] volumes;            // 各マスの現在の油の埋蔵量 (N*N個)
        List<Integer> query_volumes; // 各クエリ(q番目)で占った座標集合の現在の埋蔵量合計
        Input input;

        // 全ての油田が(0,0)にある状態を初期状態とする
        State(Input input) {
            this.input = input;
            this.oil_states = new ArrayList<>(input.m);
            for (int i = 0; i < input.m; i++) {
                this.oil_states.add(new OilState(input));
            }
            this.top_lefts = new int[input.m]; // 初期値は 0 (座標 (0,0))
            this.volumes=null;
            this.query_volumes = new ArrayList<>();
        }

        /**
         * 指定した油田 (oilId) を新しい位置 (newTopLeft) に移動し、
         * 埋蔵量グリッドやクエリ合計を差分更新する
         */
        void moveTo(int oilId, int newTopLeft) {
            OilState os = oil_states.get(oilId);
            int oldTopLeft = top_lefts[oilId];
            // 各クエリにおける埋蔵量合計の差分更新
            for (int q = 0; q < query_volumes.size(); q++) {
                query_volumes.set(q, query_volumes.get(q)
                		+ os.top_left_query_volumes.get(newTopLeft).get(q)
                		- os.top_left_query_volumes.get(oldTopLeft).get(q));
            }

            // グリッド上の埋蔵量 (volumes) の差分更新
            // 以前の位置から引き、新しい位置に足す
            if (volumes!=null) {
                for (int idCoord : input.oils[oilId].coordinate_ids) {
                    volumes[idCoord + oldTopLeft]--;
                    volumes[idCoord + newTopLeft]++;
                }
            }
            top_lefts[oilId]=newTopLeft;
        }

        /**
         * 占いクエリを実行した際、その座標集合との重なりを全候補地について記録する
         */
        void addQuery(List<Integer> queryCoords) {
            // 高速判定用のフラグ配列
            boolean[] inQuery = new boolean[input.n2];
            for (int id : queryCoords) inQuery[id] = true;
            for (int oilId = 0; oilId < input.m; oilId++) {
                OilShape oil = input.oils[oilId];
                OilState os = oil_states.get(oilId);
                // この油田が配置可能な全ての (di, dj) について重なりを計算
                for (int di = 0; di < input.n - oil.max_i; di++) {
                    for (int dj = 0; dj < input.n - oil.max_j; dj++) {
                        int topLeft = di * input.n + dj;
                        int count = 0;
                        for (int id : oil.coordinate_ids) {
                            if (inQuery[topLeft + id]) {
                                count++;
                            }
                        }
                        os.top_left_query_volumes.get(topLeft).add(count);
                    }
                }
            }

            // 現在の配置におけるこのクエリの埋蔵量合計を計算して保持
            int[] currentVolume = input.get_volume(top_lefts);
            int totalCount = 0;
            for (int ij : queryCoords) {
                totalCount += currentVolume[ij];
            }
            query_volumes.add(totalCount);
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
//        int[][] pr_if_x_lb;
        // 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
        // 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
        // クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
        // 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
        // pr_if_x[k][S][r-lb] = (prob, log(prob))
//        List<Map<Integer, List<double[]>>> pr_if_x; 
        // クエリごとにあり得る埋蔵量総量Sごとに
        // 埋蔵量Sのときにそのクエリで得られた結果になる確率P(r|S)の対数を記録
        List<double[]> ln_pr_if_s_query = new ArrayList<>();
        // 残りクエリ回数. 2*N*N回までクエリを投げられる
        int rem;

        Sim(Scanner sc, Input input) {
        	this.sc=sc;
            this.n = input.n;
            this.n2 = input.n2;
            this.m = input.m;
            this.total = input.total;
            this.eps = input.eps;
            this.rem = n * n * 2;
        }

        // 回答クエリの実行
        boolean ans(List<Integer> list) {
            if (rem == 0) {
            	System.err.println("!log giveup ");
            	System.exit(0);
            }
            rem--;
            System.out.print("a " + list.size());
            for (int id : list) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println();
            System.out.flush();
            int ret = sc.nextInt();
            if (ret == 1) return true;
            int[] failedArr = new int[list.size()];
            for(int i=0; i<list.size(); i++) failedArr[i] = list.get(i);
            failed.add(failedArr);
            return false;
        }

        // 占いクエリの実行
        int query(List<Integer> coords) {
            if (rem == 0) {
            	System.err.println("!log giveup ");
            	System.exit(0);
            }
            rem--;
            System.out.print("q " + coords.size());
            for (int id : coords) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println();
            System.out.flush();
            int ret = sc.nextInt();
            // クエリの結果を記録する
            queries.add(new QueryHistory(coords, ret));
            // 埋蔵量Sごとの対数確率を事前計算
            double[] lnPrIfS = new double[total + 1];
            int k = coords.size();
            for (int s = 0; s <= total; s++) {
                double mu = (k - s) * eps + s * (1.0 - eps);
                double sigma = Math.sqrt(k * eps * (1.0 - eps));
                lnPrIfS[s] = Math.log(likelihood(mu, sigma, ret));
            }
            ln_pr_if_s_query.add(lnPrIfS);
            return ret;
        }

        int mine(int r, int c) {
            if (rem == 0) {
            	System.err.println("!log giveup ");
            	System.exit(0);
            }
            rem--;
            System.out.println("q 1 " + r + " " + c);
            System.out.flush();
            int ret = sc.nextInt();
            return ret;
        }

        double likelihood(double mu, double sigma, int res) {
            double b = res + 0.5;
            if (res == 0) return normalCdf(mu, sigma, b);
            return normalCdf(mu, sigma, b) - normalCdf(mu, sigma, res - 0.5);
        }

        double normalCdf(double mu, double sigma, double x) {
            return 0.5 * (1.0 + erf((x - mu) / (sigma * Math.sqrt(2.0))));
        }

        // 誤差関数erfの近似
        double erf(double x) {
            double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
            double ans = 1 - t * Math.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                         t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                         t * (-0.82215223 + t * 0.17087277)))))))));
            return (x >= 0) ? ans : -ans;
        }

        // 時間切れやクエリ切れの救済措置
        void giveup() {
            Deque<Point> que = new ArrayDeque<>();
            que.add(new Point(n / 2, n / 2));
            List<Integer> list = new ArrayList<>();
            int rCount = total;
            boolean[][] used = new boolean[n][n];
            while (!que.isEmpty()) {
                Point p = que.pollFirst();
                if (used[p.r][p.c]) continue;
                used[p.r][p.c] = true;
                int ret = mine(p.r, p.c);
                if (ret > 0) {
                    list.add(p.r * n + p.c);
                    rCount -= ret;
                    if (rCount == 0) break;
                }
                for (int[] d : DIJ) {
                	int nr = p.r + d[0], nc = p.c + d[1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && !used[nr][nc]) {
                        if (ret == 0) que.addLast(new Point(nr, nc)); // 空なら後回し
                        else que.addFirst(new Point(nr, nc)); // 油田があれば優先
                    }
                }
            }
            ans(list);
            System.exit(0);
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
        int get_query_volume(List<OilState> oilStates, int q, int[] topLefts) {
            int S = 0;
            for (int oil_id = 0; oil_id < topLefts.length; ++oil_id) {
            	OilState oil_state_p = oilStates.get(oil_id);
                int ij = topLefts[oil_id];
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
        double get_ln_pR_if_x(List<OilState> oilStates, int[] volumes, int[] topLefts) {
            // 既に失敗した油田配置の集合に含まれる油田配置の場合、対数尤度を非常に小さい値にする
        	for (int[] failed_coordinates : failed) {
                boolean skip = is_different(volumes, failed_coordinates);
                if (!skip) {
                	int size = 0;
                	for (int ij = 0; ij < n2; ++ij) {
                		if (volumes[ij]>0) {
                			size++;
                		}
                	}
//                	System.err.println("DEBUG get_ln_pR_if_x "+size+" "+failed_coordinates.length);
                	if (size==failed_coordinates.length) {
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
                int S = get_query_volume(oilStates, q, topLefts);
                ln_pR_if_x += ln_pr_if_s_query.get(q)[S];
            }
            return ln_pR_if_x;
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
            layout.volume = input.get_volume(layout.top_lefts);
        }
    }

	// 占いクエリを取得する
    static List<Integer> getDivinationQuery(Input input, List<OilLayout> pool) {
        // ランダムな占い (各マス50%)
		List<Integer> coords = new ArrayList<>();
        for (int ij = 0; ij < input.n2; ++ij) {
            if (rng.gen_bool(0.5)) {
            	coords.add(ij);
            }
        }
        assert(coords.size() > 0);
        return coords;
    }
    
    static void sort_pool(List<OilLayout> pool) {
    	Collections.sort(pool);
    }

    public static void main(String[] args) {
        start=System.currentTimeMillis();
        Scanner sc=new Scanner(System.in);
        Input input=read_input(sc);

        if (input.m!=2) {
        	Solve17 s=new Solve17(input);
        	s.solve(sc);
        	return;
        }

        Sim sim = new Sim(sc, input);
        State state=new State(input);
        List<OilLayout> pool = new ArrayList<>();
        int ITER = 4000000 / (2 * input.n2);
        
        // 全パターンの生成 (M=2を前提とした二重ループ)
        OilShape oilA = input.oils[0]; 
        OilShape oilB = input.oils[1];
        for (int iA = 0; iA <= input.n - 1 - oilA.max_i; iA++) {
            for (int jA = 0; jA <= input.n - 1 - oilA.max_j; jA++) {
                for (int iB = 0; iB <= input.n - 1 - oilB.max_i; iB++) {
                    for (int jB = 0; jB <= input.n - 1 - oilB.max_j; jB++) {
                        int[] tls = {iA * input.n + jA, iB * input.n + jB};
                        pool.add(new OilLayout(0.0, 0.0, tls, state.volumes));
//                        pool.add(new OilLayout(0.0, 0.0, tls, in.get_volume(tls)));
                    }
                }
            }
        }

        for (int t=0; t<2*input.n2; t++) {
            if (sim.rem == 0) {
                System.err.println("!There is no more query");
                break;
            }
            if (get_time() > 2.9) {
                sim.giveup();
                break;
            }
            // 各配置の対数尤度を更新する
            for (OilLayout layout : pool) {
                if (layout.volume==null && !sim.failed.isEmpty()) {
                    layout.volume=input.get_volume(layout.top_lefts);
                }
                layout.ln_pR_if_x=sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts);
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
            // 事後確率の正規化
//            double maxLn = pool.stream().mapToDouble(l -> l.ln_pR_if_x).max().orElse(0);
//            double sumExp = pool.stream().mapToDouble(l -> Math.exp(l.ln_pR_if_x - maxLn)).sum();
//            for (OilLayout l : pool) l.px_if_R = Math.exp(l.ln_pR_if_x - maxLn) / sumExp;

//            pool.sort((a, b) -> Double.compare(b.px_if_R, a.px_if_R));
            OilLayout best = pool.get(0);
            set_volume(pool, input);
            long elap=System.currentTimeMillis();
            if (!RELEASE) System.err.println((t+1)+"("+(elap-start)+"):id="+best.id+" px_if_R="+best.px_if_R);
            if (best.px_if_R > 0.8) {
                List<Integer> target = input.get_positives(best.top_lefts);
                if (sim.ans(target)) break;
            } else {
                List<Integer> qCoords = getDivinationQuery(input, pool);
                sim.query(qCoords);
                state.addQuery(qCoords);
            }
        }
    }
    
    static class Solve17 {
    	static final boolean DEBUG = false;
    	static final boolean RELEASE = true;
    	int N;
    	int M;
    	int V;
    	Solve17(Input in) {
    		N=in.n;
    		M=in.m;
    		V=0;
    		for (int i=0; i<M; i++) {
    			V+=in.oils[i].coordinate_ids.size();
    		}
    	}
    	void print(int y, int x) {
    		System.out.println("q 1 "+y+" "+x);
    		System.out.flush();
    	}
    	void print(List<String> list) {
    		System.out.print("a "+list.size());
    		for (String s : list) {
    			System.out.print(" "+s);
    		}
    		System.out.println();
    		System.out.flush();
    	}
    	void solve(Scanner sc) {
//    		Scanner sc=new Scanner(System.in);
//    		init(sc);
    		int T=2*N*N;
//    		System.err.println("T="+T);
    		double cost=0;
    		int[][] flag=new int[N][N];
    		for (int y=0; y<N; y++) {
    			Arrays.fill(flag[y], -1);
    		}
            Deque<Point> que = new ArrayDeque<>();
            que.add(new Point(N/2, N/2));
    		int cnt=0;
    		int t=0;
    		while (que.size()>0) {
    			Point p=que.poll();
    			int y=p.r;
    			int x=p.c;
    			if (flag[y][x]>=0) continue;
    			print(y, x);
    			int rc=sc.nextInt();
    			if (!RELEASE) System.err.println((t+1)+":"+y+","+x+",rc="+rc);
    			flag[y][x]=rc;
    			cnt+=rc;
    			cost+=1;
    			t++;
    			if (t>=T) break;
    			if (cnt==V) break;
    			for (int d=0; d<DY.length; d++) {
    				int ny=y+DY[d];
    				int nx=x+DX[d];
    				if (ny>=0 && ny<N && nx>=0 && nx<N && flag[ny][nx]<0) {
    					if (rc==0) que.addLast(new Point(ny, nx));
    					else que.addFirst(new Point(ny, nx));
    				}
    			}
    		}
    		List<String> list=new ArrayList<>();
    		for (int y=0; y<N; y++) {
    			for (int x=0; x<N; x++) {
    				if (flag[y][x]>0) list.add(y+" "+x);
    			}
    		}
    		print(list);
    		int rc=sc.nextInt();
//    		while (true) {
//    			if (isTimeout()) break;
//    			iteration++;
//    		}
    		long end=System.currentTimeMillis();
    		long score=Math.round(1e6*cost);
    		System.err.println("--- Result ---");
    		System.err.println("elaps    : " + (end-start));
    		System.err.println("iteration: " + iteration);
    		System.err.println("score    : " + score);
    	}
    	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
    	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
    	static final String DS = "UDLR";
    }
	static long start;
	static int iteration = 0;
}

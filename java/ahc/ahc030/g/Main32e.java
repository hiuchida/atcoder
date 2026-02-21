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
//    static final int[] DR = {0, 1, 0, -1};
//    static final int[] DC = {1, 0, -1, 0};

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
        long hash;
        double ln_pR_if_x;  // 対数尤度
        double px_if_R;    // 事後確率
        int[] top_lefts;
        int[] volume;
        OilLayout(long hash, double ln, double px, int[] tls, int[] vol) {
            this.hash = hash;
            this.ln_pR_if_x = ln;
            this.px_if_R = px;
            this.top_lefts = tls.clone();
            this.volume = (vol != null) ? vol.clone() : null;
        }
		@Override
		public int compareTo(OilLayout that) {
			return -1*Double.compare(this.ln_pR_if_x, that.ln_pR_if_x);
		}
        @Override
        public String toString() {
        	StringBuilder sb=new StringBuilder();
        	sb.append("id="+id+" ln_pR_if_x="+ln_pR_if_x+" px_if_R="+px_if_R);
        	return sb.toString();
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

        List<Integer> get_positives(int[] topLefts) {
            BitSet bits = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinate_ids) bits.set(id + topLefts[i]);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) res.add(i);
            return res;
        }

        int[] get_volume(int[] topLefts) {
            int[] vol = new int[n2];
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinate_ids) vol[id + topLefts[i]]++;
            }
            return vol;
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
        long[] hashes;
        OilState(Input input) {
            this.top_left_query_volumes = new ArrayList<>(input.n2);
            for (int i = 0; i < input.n2; i++) {
                this.top_left_query_volumes.add(new ArrayList<>());
            }
            hashes = new long[input.n2];
        }
        @Override
        public String toString() {
        	return top_left_query_volumes.toString();
        }
    }

    static class State {
        List<OilState> oil_states; // 油田の状態リスト (M個)
        int[] top_lefts;
        int[] volumes;
        List<Integer> query_volumes; // 各クエリ(q番目)で占った座標集合の現在の埋蔵量合計
        long hash;
        Input input;
        State(Input input) {
            this.input = input;
            this.oil_states = new ArrayList<>(input.m);
            for (int i = 0; i < input.m; i++) {
                this.oil_states.add(new OilState(input));
                if (i > 0 && input.oils[i-1].coordinate_ids.equals(input.oils[i].coordinate_ids)) {
                	oil_states.get(i).hashes = oil_states.get(i-1).hashes;
                } else {
                    for (int j = 0; j < input.n2; j++) {
                    	oil_states.get(i).hashes[j] = rng.next();
                    }
                }
            }
            this.hash = 0;
            for (OilState os : oil_states) this.hash ^= os.hashes[0];
            this.top_lefts = new int[input.m]; // 初期値は 0 (座標 (0,0))
//            this.volumes = new int[input.n2];
            this.volumes=null;
            this.query_volumes = new ArrayList<>();
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oil_states.get(oilId);
            int oldTopLeft = top_lefts[oilId];
            hash ^= os.hashes[top_lefts[oilId]] ^ os.hashes[newTopLeft];
            for (int q = 0; q < query_volumes.size(); q++) {
                int diff = os.top_left_query_volumes.get(newTopLeft).get(q) 
                         - os.top_left_query_volumes.get(oldTopLeft).get(q);
                query_volumes.set(q, query_volumes.get(q) + diff);
            }
            if (volumes!=null) {
                for (int idCoord : input.oils[oilId].coordinate_ids) {
                    volumes[idCoord + top_lefts[oilId]]--;
                    volumes[idCoord + newTopLeft]++;
                }
            }
            top_lefts[oilId]=newTopLeft;
        }

        void addQuery(List<Integer> queryCoords) {
            boolean[] inQ = new boolean[input.n2];
            for (int id : queryCoords) inQ[id] = true;
            for (int oilId = 0; oilId < input.m; oilId++) {
                OilShape oil = input.oils[oilId];
                OilState os = oil_states.get(oilId);
                for (int di = 0; di <= input.n - 1 - oil.max_i; di++) {
                    for (int dj = 0; dj <= input.n - 1 - oil.max_j; dj++) {
                        int tl = di * input.n + dj;
                        int c = 0;
                        for (int id : oil.coordinate_ids) {
                        	if (inQ[tl + id]) c++;
                        }
                        os.top_left_query_volumes.get(tl).add(c);
                    }
                }
            }
            int[] currentVol = input.get_volume(top_lefts);
            int sum = 0;
            for (int id : queryCoords) {
            	sum += currentVol[id];
            }
            query_volumes.add(sum);
        }
        @Override
        public String toString() {
        	StringBuilder sb=new StringBuilder();
//        	for (OilState os : oil_states) {
//        		sb.append(os.toString()).append("\n");
//        	}
        	if (volumes!=null) {
            	for (int y=0; y<input.n; y++) {
            		for (int x=0; x<input.n; x++) {
            			sb.append(volumes[y*input.n+x]).append(" ");
            		}
            		sb.append("\n");
            	}
        	}
        	return sb.toString();
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
        int[][] pr_if_x_lb;
        // 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
        // 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
        // クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
        // 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
        // pr_if_x[k][S][r-lb] = (prob, log(prob))
        List<Map<Integer, List<double[]>>> pr_if_x; 
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
            this.pr_if_x_lb = new int[input.n2 + 1][input.total + 1];
            this.pr_if_x = new ArrayList<>(input.n2 + 1);
            for(int i=0; i<=input.n2; i++) pr_if_x.add(new HashMap<>());
            
            // 尤度の事前計算 (Source 42-43)
            for (int k = 1; k <= input.n2; k++) {
                for (int s = 0; s <= input.total; s++) {
                    double mu = (double)(k - s) * input.eps + s * (1.0 - input.eps);
                    double sigma = Math.sqrt(k * input.eps * (1.0 - input.eps));
                    List<double[]> list = new ArrayList<>();
                    int startR = (int)Math.round(mu);
                    
                    for (int r = startR; r >= 0; r--) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) { pr_if_x_lb[k][s] = r + 1; break; }
                        list.add(new double[]{p, Math.log(p)});
                    }
                    Collections.reverse(list);
                    for (int r = startR + 1; ; r++) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) break;
                        list.add(new double[]{p, Math.log(p)});
                    }
                    pr_if_x.get(k).put(s, list);
                }
            }
        }

        boolean ans(List<Integer> list) {
            rem--;
            System.out.print("a " + list.size());
            for (int id : list) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println();
            System.out.flush();
            int ret = sc.nextInt();
            if (ret == 1) return true;
            int[] f = new int[list.size()];
            for(int i=0; i<list.size(); i++) f[i] = list.get(i);
            failed.add(f);
            return false;
        }

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

        double erf(double x) {
            double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
            double ans = 1 - t * Math.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                         t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                         t * (-0.82215223 + t * 0.17087277)))))))));
            return (x >= 0) ? ans : -ans;
        }

        void giveup() {
            Deque<Point> que = new ArrayDeque<>();
            que.add(new Point(n / 2, n / 2));
            List<Integer> list = new ArrayList<>();
            int remT = total;
            boolean[][] used = new boolean[n][n];
            while (!que.isEmpty()) {
            	Point p = que.pollFirst();
                if (used[p.r][p.c]) continue;
                used[p.r][p.c] = true;
                int ret = mine(p.r, p.c);
                if (ret > 0) {
                    list.add(p.r * n + p.c);
                    remT -= ret; if (remT <= 0) break;
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
//                System.err.println("DEBUG ij="+ij+" q="+q+" get(ij)="+oil_state_p.top_left_query_volumes.get(ij));
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

    static class Query {
        boolean[] in_query;
        int[] volume;
        int coordinate_size;
        List<OilLayout> pool;

        Query(Input input, List<OilLayout> pool) {
            this.in_query = new boolean[input.n2];
            this.volume = new int[pool.size()];
            this.coordinate_size = 0;
            this.pool = pool;
        }

        void flip(int ij) {
            if (in_query[ij]) {
            	in_query[ij] = false;
                for (int x = 0; x < pool.size(); x++) volume[x] -= pool.get(x).volume[ij];
                coordinate_size--;
            } else {
            	in_query[ij] = true;
                for (int x = 0; x < pool.size(); x++) volume[x] += pool.get(x).volume[ij];
                coordinate_size++;
            }
        }

        // 相互情報量 I(X;R) / cost の評価 (Source 53-54)
        double eval(Sim sim) {
            if (coordinate_size == 0) return -1e20;
            int k = coordinate_size;
            double cost = 1.0 / Math.sqrt(k);
            List<Double> lnPrR = new ArrayList<>();
            
            for (int x = 0; x < pool.size(); x++) {
                int v = volume[x];
                int lb = sim.pr_if_x_lb[k][v];
                List<double[]> probs = sim.pr_if_x.get(k).get(v);
                while (lnPrR.size() < lb + probs.size()) lnPrR.add(0.0);
                double px = pool.get(x).px_if_R;
                for (int i = 0; i < probs.size(); i++) {
                    lnPrR.set(lb + i, lnPrR.get(lb + i) + probs.get(i)[0] * px);
                }
            }
            for (int i = 0; i < lnPrR.size(); i++) lnPrR.set(i, Math.log(lnPrR.get(i) + 1e-20));

            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                double px = pool.get(x).px_if_R;
                int v = volume[x];
                int lb = sim.pr_if_x_lb[k][v];
                List<double[]> probs = sim.pr_if_x.get(k).get(v);
                for (int i = 0; i < probs.size(); i++) {
                    info += probs.get(i)[0] * px * (probs.get(i)[1] - lnPrR.get(lb + i));
                }
            }
            return info / cost;
        }

        List<Integer> getCoords() {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < in_query.length; i++) if (in_query[i]) res.add(i);
            return res;
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
    static List<Integer> getDivinationQuery(Input input, List<OilLayout> pool, Sim sim) {
        // 山登り法による相互情報量最大のクエリ探索 (Source 195-197)
    	Query query=new Query(input, pool);
        List<Double> evals = new ArrayList<>();
        for (int i = 0; i < input.n2; i++) {
        	query.flip(i);
            evals.add(query.eval(sim));
            query.flip(i);
        }
        List<Integer> indices = new ArrayList<>();
        for(int i=0; i<input.n2; i++) {
        	indices.add(i);
        }
        indices.sort((a, b) -> Double.compare(evals.get(b), evals.get(a)));
        
//        while (indices.size()>50) indices.remove(indices.size()-1);
        
        long st2=System.currentTimeMillis();
        double bestEval = -1e20;
        for (int iter = 0; iter < 3; iter++) {
            boolean changed = false;
        	long ed2=System.currentTimeMillis();
            for (int idx : indices) {
            	ed2=System.currentTimeMillis();
            	if (ed2-st2>100) {
//            		System.err.println("Timeout "+(ed2-st2)+"ms");
            		break;
            	}
            	query.flip(idx);
                double e=query.eval(sim);
                if (e>bestEval) {
                	bestEval = e;
                	changed = true;
                } else {
                	query.flip(idx);
                }
            }
            if (!changed) break;
        	if (ed2-st2>100) break;
        }
        return query.getCoords();
    }
    
	// 占いクエリを取得する
    static List<Integer> getDivinationQuery12c(Input input, List<OilLayout> pool) {
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

    public static void main(String[] args) {
		start=System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        Input input=read_input(sc);

        Sim sim = new Sim(sc, input);
        State state = new State(input);
//        System.err.println(state);
        List<OilLayout> pool = new ArrayList<>();
        int ITER = 4000000 / (2 * input.n2);
//        int ITER = 100;

        for (int t=0; t<2*input.n2; t++) {
            if (sim.rem == 0) {
                System.err.println("!There is no more query");
                break;
            }
            if (get_time() > 2.9) {
                sim.giveup();
                break;
            }

            Map<Long, Double> hashLogL = new HashMap<>();
            for (OilLayout l : pool) {
            	hashLogL.put(l.hash, l.ln_pR_if_x);
            }
            
            for (int i = 0; i < ITER; i++) {
                for (int oilId = 0; oilId < input.m; oilId++) {
                    OilShape oil = input.oils[oilId]; // ループ内でインデックスを指定して取得
                    int r = rng.randRange(input.n - oil.max_i);
                    int c = rng.randRange(input.n - oil.max_j);
                    state.moveTo(oilId, r * input.n + c);
                }
                if (!hashLogL.containsKey(state.hash)) {
                    hashLogL.put(state.hash, 0.0);
                    OilLayout ol=new OilLayout(state.hash, 0.0, 0.0, state.top_lefts, state.volumes);
                    pool.add(ol);
                }
            }

            // 各配置の対数尤度を更新
//            int li=0;
            for (OilLayout layout : pool) {
                if (layout.volume == null && !sim.failed.isEmpty()) {
                    layout.volume = input.get_volume(layout.top_lefts);
                }
                layout.ln_pR_if_x=sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts);
//                System.err.println(li+":"+layout);
//                li++;
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

            // 正規化とプールの削減
//            double maxLn = pool.get(0).ln_pR_if_x;
//            for (OilLayout l : pool) l.px_if_R = Math.exp(l.ln_pR_if_x - maxLn);
//            double sumExp = pool.stream().mapToDouble(l -> l.px_if_R).sum();
//            for (OilLayout l : pool) l.px_if_R /= sumExp;

            while (pool.size() > 1 && pool.get(pool.size()-1).px_if_R < 1e-9) {
            	pool.remove(pool.size()-1);
            }
            
            // プールサイズの動的変更
            long elap=System.currentTimeMillis();
            double timeRem = Math.max(0, 1.0 - (elap - start) / 3000.0);
            int targetSize = (int) Math.max(2, (ITER * 0.01) * timeRem);
            if (pool.size() > targetSize) {
                int s = pool.size();
                while (s > 2 && pool.get(0).px_if_R * 1e-4 > pool.get(s - 1).px_if_R) s--;
                if (pool.size() > s) pool = new ArrayList<>(pool.subList(0, Math.max(2, s)));
            }

            // 自信があれば回答
            OilLayout best = pool.get(0);
            set_volume(pool, input);
            double bestProb = pool.get(0).px_if_R;
//            long elap=System.currentTimeMillis();
            if (!RELEASE) System.err.println((t+1)+"("+(elap-start)+"):id="+best.id+" px_if_R="+best.px_if_R);
//            System.err.println(state);
            if (bestProb > 0.9) {
            	List<Integer> target = input.get_positives(pool.get(0).top_lefts);
                if (sim.ans(target)) break;
            } else {
                List<Integer> qCoords = getDivinationQuery(input, pool, sim);
//                List<Integer> qCoords = getDivinationQuery12c(input, pool);
                sim.query(qCoords);
                state.addQuery(qCoords);
            }
        }
    }
	static long start;
	static int iteration = 0;
}

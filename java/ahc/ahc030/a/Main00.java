import java.util.*;

public class Main {
	static final Random rng = new Random(0);

	// 累積分布関数 (CDF) のための誤差関数 (erf) の近似
	static double erf(double x) {
		double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
		double tau = t * Math
				.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + t * (-0.18628806 + t
						* (0.27886807 + t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 + t * 0.17087277)))))))));
		if (x >= 0)
			return 1 - tau;
		else
			return tau - 1;
	}

	static double normalCDF(double mean, double stdDev, double x) {
		return 0.5 * (1.0 + erf((x - mean) / (Math.sqrt(2.0) * stdDev)));
	}

	static class Oil {
		int id;
		int n;
		int[][] cells;
		int maxI, maxJ;
		Oil(Scanner sc, int id) {
			this.id = id;
			this.n = sc.nextInt();
			cells = new int[n][2];
			maxI = 0;
			maxJ = 0;
			for (int i = 0; i < n; i++) {
				cells[i][0] = sc.nextInt();
				cells[i][1] = sc.nextInt();
				maxI = Math.max(maxI, cells[i][0]);
				maxJ = Math.max(maxJ, cells[i][1]);
			}
		}
	}

	static class Candidate implements Comparable<Candidate> {
		int id;
		int[] topI; // 各油田の左上座標I
		int[] topJ; // 各油田の左上座標J
		double lnLikelihood;
		double likelihood;
		Candidate(int m) {
			topI = new int[m];
			topJ = new int[m];
			lnLikelihood = 0.0;
			likelihood = 0.0;
		}
		@Override
		public int compareTo(Candidate that) {
			return -1*Double.compare(this.likelihood, that.likelihood);
		}
		@Override
		public String toString() {
			return "\r\n"+"#"+id+","+Arrays.toString(topI)+","+Arrays.toString(topJ)+","+likelihood;
		}
	}

	static class Query {
		int N;
		List<Integer> coords=new ArrayList<>();
		int result;
		Query(int n) {
			N=n;
			while (coords.size()==0) {
				for (int i = 0; i < N * N; i++) {
					if (rng.nextBoolean())
						coords.add(i);
				}
			}
		}
		Query(List<Integer> coords, int result) {
			this.coords = coords;
			this.result = result;
		}
		void print() {
			StringBuilder sb = new StringBuilder();
			sb.append("q " + coords.size());
			for (int pos : coords) {
				sb.append(" " + (pos / N) + " " + (pos % N));
			}
			System.out.println(sb.toString());
			System.out.flush();
		}
	}
	static class LnLikelihoodCache {
		Map<Integer,double[]> map=new TreeMap<>();
		int key(int k, int res) {
			int key=k*10000+res;
			return key;
		}
		void update(int k, int res, int total, double eps) {
			int key=key(k, res);
			if (map.containsKey(key)) return;
			double[] ad=new double[total+1];
			double sigma = Math.sqrt(k * eps * (1.0 - eps));
			for (int S=0; S<=total; S++) {
				double v=calcLnLikelihood(k, res, S, eps, sigma);
				ad[S]=v;
			}
			map.put(key, ad);
		}
		double get(int k, int res, int S) {
			int key=key(k, res);
			double[] ad=map.get(key);
			return ad[S];
		}
		@Override
		public String toString() {
			return map.keySet().toString();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 入力受取
		int N = sc.nextInt();
		int M = sc.nextInt();
		if (M>2) return;
		double EPS = sc.nextDouble();
		int TOTAL=0;
		Oil[] oils = new Oil[M];
		for (int i = 0; i < M; i++) {
			oils[i] = new Oil(sc, i);
			TOTAL+=oils[i].n;
//			System.err.println("i="+i+",maxI="+oils[i].maxI+",maxJ="+oils[i].maxJ);
		}
//		System.err.println("TOTAL="+TOTAL);

		// すべての配置候補(Candidate)を生成 (※M=2以下を想定)
		List<Candidate> pool = generateAllCandidates(N, M, oils);
//		System.err.println("pool.size="+pool.size());
		List<Query> history = new ArrayList<>();
		LnLikelihoodCache cache=new LnLikelihoodCache();
		int R = N * N * 2;
		double cost=0;
		while (R > 0) {
//			System.err.println(R+":"+cache);
			normalizePool(pool);
			Collections.sort(pool);
//			System.err.println(R+":"+pool);
			// 最も尤度が高い候補を選択
			Candidate best = pool.get(0);
			double maxL = best.likelihood;
			for (Candidate c : pool) {
				if (c.likelihood > maxL) {
					maxL = c.likelihood;
					best = c;
				}
			}
//			System.err.println(R + ":" + maxL);
			// 回答を試みる (簡易的に最も尤度が高いものを出力)
			// 本来は確率が十分高まってから行う
			if (maxL > 0.8) { // 条件は適宜調整
				String ans = answer(N, M, oils, best);
				System.out.println(ans);
				System.out.flush();
				R--;
				int judge = sc.nextInt();
//				System.err.println("judge=" + judge);
				if (judge == 1) {
					// 正解ならプログラム終了
					break;
				} else {
					// 不正解なら、この候補の尤度を極端に下げて二度と選ばれないようにする
					best.lnLikelihood = -1e100;
					cost+=1;
				}
			}

			// ランダムな占いクエリ
			Query query=new Query(N);
			query.print();
			R--;
			cost+=1.0/Math.sqrt(query.coords.size());
			int res = sc.nextInt();
			query.result=res;
//			System.err.println("res=" + res);
			cache.update(query.coords.size(), res, TOTAL, EPS);
			// ベイズ更新
			updatePool(cache, pool, query.coords, res, oils, EPS, N);
		}
		System.err.println("cost="+cost);
	}

	// 全候補生成 (再帰)
	static List<Candidate> generateAllCandidates(int N, int M, Oil[] oils) {
		List<Candidate> res = new ArrayList<>();
		dfs(0, N, M, oils, new Candidate(M), res);
		for (int i=0; i<res.size(); i++) {
			res.get(i).id=i+1;
		}
		return res;
	}

	static void dfs(int m, int N, int M, Oil[] oils, Candidate current, List<Candidate> res) {
		if (m == M) {
			Candidate copy = new Candidate(M);
			System.arraycopy(current.topI, 0, copy.topI, 0, M);
			System.arraycopy(current.topJ, 0, copy.topJ, 0, M);
			res.add(copy);
			return;
		}
		for (int i = 0; i <= N - 1 - oils[m].maxI; i++) {
			for (int j = 0; j <= N - 1 - oils[m].maxJ; j++) {
				current.topI[m] = i;
				current.topJ[m] = j;
				dfs(m + 1, N, M, oils, current, res);
			}
		}
	}
	static double calcLnLikelihood(int k, int res, int S, double eps, double sigma) {
		// 尤度 q(k, S, res) の計算
		double mu = (k - S) * eps + S * (1.0 - eps);
		double prob;
		if (res == 0) {
			prob = normalCDF(mu, sigma, 0.5);
		} else {
			prob = normalCDF(mu, sigma, res + 0.5) - normalCDF(mu, sigma, res - 0.5);
		}
		return Math.log(Math.max(prob, 1e-18));
	}
	static void updatePool(LnLikelihoodCache cache, List<Candidate> pool, List<Integer> query, int res, Oil[] oils, double eps, int N) {
		int k = query.size();
		for (Candidate cand : pool) {
			// この候補における真の埋蔵量Sを計算
			int S = 0;
			int[][] island = new int[N][N];
			for (int m = 0; m < oils.length; m++) {
				for (int[] cell : oils[m].cells) {
					island[cand.topI[m] + cell[0]][cand.topJ[m] + cell[1]]++;
				}
			}
			for (int pos : query) {
				S += island[pos / N][pos % N];
			}
			// 対数尤度を加算
			cand.lnLikelihood += cache.get(k, res, S);
		}
	}
	static void normalizePool(List<Candidate> pool) {
		double maxLnL = pool.get(0).lnLikelihood;
		for (Candidate c : pool) {
			if (c.lnLikelihood > maxLnL) {
				maxLnL = c.lnLikelihood;
			}
		}
		for (Candidate c : pool) {
			c.likelihood=Math.exp(c.lnLikelihood-maxLnL);
		}
		double total=0;
		for (Candidate c : pool) {
			total+=c.likelihood;
		}
		for (Candidate c : pool) {
			c.likelihood/=total;
		}
	}
	static String answer(int N, int M, Oil[] oils, Candidate best) {
		// 1. 最も尤度が高い候補(best)から、島全体の埋蔵量マップを再現する
		int[][] island = new int[N][N];
		for (int m = 0; m < M; m++) {
			for (int[] cell : oils[m].cells) {
				// 油田mの相対座標を、配置位置(topI, topJ)に足す
				int realI = best.topI[m] + cell[0];
				int realJ = best.topJ[m] + cell[1];
				island[realI][realJ]++;
			}
		}

		// 2. 埋蔵量が 1 以上のマスをすべて数え、リストに加える
		List<String> resultCoords = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (island[i][j] > 0) {
					resultCoords.add(i + " " + j);
				}
			}
		}

		// 3. 正解形式で出力
		// a (マスの総数) (座標リスト...)
		StringBuilder sb = new StringBuilder();
		sb.append("a ").append(resultCoords.size());
		for (String s : resultCoords) {
			sb.append(" ").append(s);
		}
		return sb.toString();
	}
}

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

    static int N;
    static int M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    
    static class Configuration {
    	int id=seq();
        Point[] topLefts;
        int[][] amount;
        double logLikelihood = 0;
        boolean isPossible = true;

        Configuration(Point[] tls) {
            this.topLefts = tls;
            this.amount = new int[N][N];
            for (int i = 0; i < M; i++) {
                for (Point p : oilShapes.get(i)) {
                    amount[tls[i].r + p.r][tls[i].c + p.c]++;
                }
            }
        }
        List<String> toList() {
        	List<String> list=new ArrayList<>();
        	for (int r=0; r<N; r++) {
        		for (int c=0; c<N; c++) {
        			if (amount[r][c]>0) list.add(r+" "+c);
        		}
        	}
        	return list;
        }
    }

    public static void main(String[] args) {
		start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        EPS = sc.nextDouble();
        for (int i = 0; i < M; i++) {
            int d = sc.nextInt();
            List<Point> shape = new ArrayList<>();
            for (int j = 0; j < d; j++) {
                shape.add(new Point(sc.nextInt(), sc.nextInt()));
            }
            oilShapes.add(shape);
        }

        if (M!=2) {
        	Solve17 s=new Solve17();
        	s.solve(sc);
        	return;
        }

        // 全ての配置候補（プール）を生成
        List<Configuration> pool = generateAllConfigurations();
        Random rand = new Random(0);

        for (int t=0; t<2*N*N; t++) {
            // 1. 対数尤度を事後確率に正規化し、最も確実な候補を探す
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (Configuration conf : pool) {
                if (conf.isPossible) maxLogL = Math.max(maxLogL, conf.logLikelihood);
            }

            double sumExp = 0;
            for (Configuration conf : pool) {
                if (conf.isPossible) sumExp += Math.exp(conf.logLikelihood - maxLogL);
            }

            int bestIdx = -1;
            double maxProb = 0;
            for (int i = 0; i < pool.size(); i++) {
                Configuration conf = pool.get(i);
                if (conf.isPossible) {
                    double prob = Math.exp(conf.logLikelihood - maxLogL) / sumExp;
                    if (prob > maxProb) {
                        maxProb = prob;
                        bestIdx = i;
                    }
                }
            }
            Configuration best = pool.get(bestIdx);
            if (!RELEASE) System.err.println((t+1)+":id="+best.id+" maxProb="+maxProb);
            // 2. 自信（確率）が0.8を超えたら回答
            if (bestIdx != -1 && maxProb > 0.8) {
//                Configuration best = pool.get(bestIdx);
                List<String> list=best.toList();
                System.out.print("a " + list.size());
                for (String s : list) System.out.print(" " + s);
                System.out.println();
                System.out.flush();
                if (sc.nextInt() == 1) break; // 正解
                else {
                    best.isPossible = false; // 外れた配置を除外
                    continue;
                }
            }

            // 3. ランダムな占い (各マスを0.5の確率で選択)
            List<Point> queryCells = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (rand.nextBoolean()) queryCells.add(new Point(r, c));
                }
            }
            int k = queryCells.size();
            if (k == 0) continue;

            System.out.print("q " + k);
            for (Point p : queryCells) System.out.print(" " + p.r + " " + p.c);
            System.out.println();
            System.out.flush();
            int result = sc.nextInt();

            // 4. クエリ結果に基づき対数尤度を更新
            // 各配置候補における埋蔵量合計Sごとに、占い結果が得られる対数確率を事前計算
            int maxPossibleS = M * (N * N); // 理論上の最大埋蔵量
            double[] ln_pr_if_s = new double[maxPossibleS + 1];
            for (int s = 0; s <= maxPossibleS; s++) {
                ln_pr_if_s[s] = Math.log(calcProbability(k, s, result));
            }

            for (Configuration conf : pool) {
                if (!conf.isPossible) continue;
                int currentS = 0;
                for (Point p : queryCells) currentS += conf.amount[p.r][p.c];
                conf.logLikelihood += ln_pr_if_s[currentS];
            }
        }
    }

    // 誤差関数を用いた確率計算
    static double calcProbability(int k, int s, int r) {
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigma = Math.sqrt(k * EPS * (1 - EPS));
        if (r == 0) return cdf(0.5, mu, sigma);
        return cdf(r + 0.5, mu, sigma) - cdf(r - 0.5, mu, sigma);
    }

    static double cdf(double x, double mu, double sigma) {
        return 0.5 * (1.0 + erf((x - mu) / (Math.sqrt(2) * sigma)));
    }

    static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                     t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                     t * (-0.82215223 + t * 0.17087277)))))))));
        return (z >= 0) ? ans : -ans;
    }

    static List<Configuration> generateAllConfigurations() {
        List<Configuration> res = new ArrayList<>();
        generateRecursive(0, new Point[M], res);
        return res;
    }

    static void generateRecursive(int oilIdx, Point[] currentTLs, List<Configuration> res) {
        if (oilIdx == M) {
            res.add(new Configuration(currentTLs.clone()));
            return;
        }
        int maxH = 0, maxW = 0;
        for (Point p : oilShapes.get(oilIdx)) {
            maxH = Math.max(maxH, p.r);
            maxW = Math.max(maxW, p.c);
        }
        for (int r = 0; r <= N - 1 - maxH; r++) {
            for (int c = 0; c <= N - 1 - maxW; c++) {
                currentTLs[oilIdx] = new Point(r, c);
                generateRecursive(oilIdx + 1, currentTLs, res);
            }
        }
    }

    static class Solve17 {
    	int V;
    	Solve17() {
    		V=0;
    		for (int i=0; i<M; i++) {
    			V+=oilShapes.get(i).size();
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

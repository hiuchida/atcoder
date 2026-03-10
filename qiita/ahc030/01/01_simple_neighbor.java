import java.util.*;
import java.io.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;

    // 乱数生成器 (Xorshift)
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }

        public long next() {
            x ^= x << 13;
            x ^= x >>> 17; // Javaでは論理右シフトを使用
            x ^= x << 5;
            return x;
        }

        public long randRange(long stop) {
            return (next() & Long.MAX_VALUE) % stop;
        }

        public double random() {
            // doubleの仮数部53ビットの精度に合わせて生成
            return (next() >>> 11) * 0x1.0p-53;
        }

        public boolean genBool(double p) {
            return random() < p;
        }
    }

    static Xorshift rng = new Xorshift(1);

    // 油田の配置情報
    static class OilLayout {
        long hash;
        double lnPRifX;
        double pxIfR;
        int[] topLefts;
        byte[] volume;

        OilLayout(long hash, double lnPRifX, double pxIfR, int[] topLefts, byte[] volume) {
            this.hash = hash;
            this.lnPRifX = lnPRifX;
            this.pxIfR = pxIfR;
            this.topLefts = topLefts.clone();
            this.volume = (volume != null) ? volume.clone() : null;
        }
    }

    // 油田の形状情報
    static class OilShape {
        int maxI, maxJ;
        int[] coordinateIds;
        List<int[]> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400); 
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        BitSet getPositives(int[] topLefts) {
            BitSet positives = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                int pij = topLefts[i];
                for (int id : oils[i].coordinateIds) positives.set(id + pij);
            }
            return positives;
        }

        byte[] getVolume(int[] topLefts) {
            byte[] vol = new byte[n2];
            for (int i = 0; i < m; i++) {
                int pij = topLefts[i];
                for (int ij : oils[i].coordinateIds) vol[ij + pij]++;
            }
            return vol;
        }
    }

    static class OilState {
        List<List<Byte>> topLeftQueryVolumes;
        long[] hashes;

        OilState(int n2) {
            topLeftQueryVolumes = new ArrayList<>(n2);
            for (int i = 0; i < n2; i++) topLeftQueryVolumes.add(new ArrayList<>());
            hashes = new long[n2];
        }
    }

    static class State {
        OilState[] oilStates;
        int[] topLefts;
        byte[] volumes;
        List<Byte> queryVolumes = new ArrayList<>();
        long hash;
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) {
                this.oilStates[i] = new OilState(input.n2);
                if (i > 0 && Arrays.equals(input.oils[i-1].coordinateIds, input.oils[i].coordinateIds)) {
                    this.oilStates[i].hashes = this.oilStates[i-1].hashes;
                } else {
                    for (int ij = 0; ij < input.n2; ij++) this.oilStates[i].hashes[ij] = rng.next();
                }
            }
            this.topLefts = new int[input.m];
            this.volumes = new byte[input.n2];
            this.hash = 0;
            for (int i = 0; i < input.m; i++) this.hash ^= oilStates[i].hashes;
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oilStates[oilId];
            hash ^= os.hashes[topLefts[oilId]] ^ os.hashes[newTopLeft];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int oldQ = queryVolumes.get(q) & 0xFF;
                int diff = (os.topLeftQueryVolumes.get(newTopLeft).get(q) & 0xFF) 
                         - (os.topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF);
                queryVolumes.set(q, (byte)(oldQ + diff));
            }
            if (volumes != null) {
                for (int ij : input.oils[oilId].coordinateIds) {
                    volumes[ij + topLefts[oilId]]--;
                    volumes[ij + newTopLeft]++;
                }
            }
            topLefts[oilId] = newTopLeft;
        }

        void addQuery(List<Integer> coords) {
            boolean[] inQ = new boolean[input.n2];
            for (int ij : coords) inQ[ij] = true;
            for (int i = 0; i < input.m; i++) {
                for (int tl = 0; tl < input.n2; tl++) {
                    int r = tl / input.n, c = tl % input.n;
                    if (r > input.n - input.oils[i].maxI - 1 || c > input.n - input.oils[i].maxJ - 1) {
                        oilStates[i].topLeftQueryVolumes.get(tl).add((byte)0);
                    } else {
                        byte count = 0;
                        for (int ij : input.oils[i].coordinateIds) if (inQ[tl + ij]) count++;
                        oilStates[i].topLeftQueryVolumes.get(tl).add(count);
                    }
                }
            }
            byte[] currentVol = input.getVolume(topLefts);
            byte sum = 0;
            for (int ij : coords) sum += currentVol[ij];
            queryVolumes.add(sum);
        }
    }

    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<List<Double>> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> failed = new ArrayList<>();
        Scanner sc;

        Sim(Input input, Scanner sc) {
            this.n = input.n; this.n2 = input.n2; this.m = input.m;
            this.total = input.total; this.eps = input.eps;
            this.rem = n * n * 2; this.sc = sc;
        }

        double erf(double x) {
            double p = 0.3275911, a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429;
            int sign = (x < 0) ? -1 : 1; x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
            return sign * y;
        }

        double normalCdf(double mu, double sig, double x) {
            return 0.5 * (1.0 + erf((x - mu) / (sig * Math.sqrt(2.0))));
        }

        double likelihood(double mu, double sig, int r) {
            double b = r + 0.5;
            if (r == 0) return normalCdf(mu, sig, b);
            return normalCdf(mu, sig, b) - normalCdf(mu, sig, r - 0.5);
        }

        int query(List<Integer> coords) {
            if (rem-- <= 0) System.exit(0);
            System.out.print("q " + coords.size());
            for (int ij : coords) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int res = sc.nextInt();
            List<Double> lnPr = new ArrayList<>();
            for (int s = 0; s <= total; s++) {
                double mu = (coords.size() - s) * eps + s * (1.0 - eps);
                double sig = Math.sqrt(coords.size() * eps * (1.0 - eps));
                lnPr.add(Math.log(likelihood(mu, sig, res)));
            }
            lnPrIfSQuery.add(lnPr);
            return res;
        }

        boolean ans(List<Integer> T) {
            if (rem-- <= 0) System.exit(0);
            System.out.print("a " + T.size());
            for (int ij : T) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int res = sc.nextInt();
            if (res == 1) return true;
            failed.add(T);
            return false;
        }

        double lnProbState(State state) {
            for (List<Integer> f : failed) {
                int count = 0;
                for (int i = 0; i < n2; i++) if (state.volumes[i] > 0) count++;
                if (count != f.size()) continue;
                boolean same = true;
                for (int ij : f) if (state.volumes[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }
            double prob = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                prob += lnPrIfSQuery.get(q).get(state.queryVolumes.get(q) & 0xFF);
            }
            return prob;
        }

        double getLnPRifX(OilState[] os, byte[] vol, int[] tls) {
            for (List<Integer> f : failed) {
                int count = 0;
                for (int i = 0; i < n2; i++) if (vol[i] > 0) count++;
                if (count != f.size()) continue;
                boolean same = true;
                for (int ij : f) if (vol[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }
            double lnPR = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int S = 0;
                for (int i = 0; i < m; i++) S += os[i].topLeftQueryVolumes.get(tls[i]).get(q) & 0xFF;
                lnPR += lnPrIfSQuery.get(q).get(S);
            }
            return lnPR;
        }
        
        void giveup() { System.exit(0); }
    }

    static class QueryEvaluator {
        boolean[] inQ;
        int[] layoutVols;
        int k;
        List<OilLayout> pool;

        QueryEvaluator(Input in, List<OilLayout> pool) {
            this.inQ = new boolean[in.n2];
            this.layoutVols = new int[pool.size()];
            this.pool = pool;
        }

        void flip(int ij) {
            int sign = inQ[ij] ? -1 : 1;
            for (int x = 0; x < pool.size(); x++) layoutVols[x] += sign * (pool.get(x).volume[ij] & 0xFF);
            k += sign;
            inQ[ij] = !inQ[ij];
        }

        double eval(Sim sim, Input input, int addK, int addV) {
            int tk = k + addK;
            if (tk == 0) return -1e100;
            double[] pr_r = new double[sim.total + 1];
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVols[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total; r++) pr_r[r] += sim.likelihood(mu, sig, r) * pool.get(x).pxIfR;
            }
            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVols[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total; r++) {
                    double p_r_x = sim.likelihood(mu, sig, r);
                    if (p_r_x > SMALL_VALUE && pr_r[r] > SMALL_VALUE) {
                        info += p_r_x * pool.get(x).pxIfR * (Math.log(p_r_x) - Math.log(pr_r[r]));
                    }
                }
            }
            return info * Math.sqrt(tk);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Input input = new Input();
        input.n = sc.nextInt(); input.m = sc.nextInt(); input.eps = sc.nextDouble();
        input.n2 = input.n * input.n;
        input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            input.oils[i] = new OilShape();
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) input.oils[i].coordinates.add(new int[]{sc.nextInt(), sc.nextInt()});
            input.total += size;
            OilShape os = input.oils[i];
            os.coordinateIds = new int[size];
            for (int j = 0; j < size; j++) {
                os.maxI = Math.max(os.maxI, os.coordinates.get(j));
                os.maxJ = Math.max(os.maxJ, os.coordinates.get(j));
                os.coordinateIds[j] = os.coordinates.get(j) * input.n + os.coordinates.get(j);
            }
        }

        Sim sim = new Sim(input, sc);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();
        long start = System.currentTimeMillis();
        int ITER = 4000000 / (2 * input.n2);

        for (int t = 0; ; t++) {
            if (System.currentTimeMillis() - start > 2900) sim.giveup();

            for (OilLayout l : pool) {
                if (l.volume == null) l.volume = input.getVolume(l.topLefts);
                l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            }
            Collections.shuffle(pool, new Random(rng.next()));
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            Map<Long, Double> hashLikelihood = new HashMap<>();
            for (OilLayout l : pool) hashLikelihood.put(l.hash, l.lnPRifX);

            if (t == 0) {
                for (int i = 0; i < ITER; i++) {
                    for (int m = 0; m < input.m; m++) {
                        state.moveTo(m, (int)rng.randRange(input.n - input.oils[m].maxI) * input.n + (int)rng.randRange(input.n - input.oils[m].maxJ));
                    }
                    if (!hashLikelihood.containsKey(state.hash)) {
                        hashLikelihood.put(state.hash, 0.0);
                        pool.add(new OilLayout(state.hash, 0, 0, state.topLefts, state.volumes));
                    }
                }
            } else {
                // 焼きなまし法によるプール更新
                double maxCrt = pool.get(0).lnPRifX;
                for (int i = 0; i < input.m; i++) state.moveTo(i, pool.get(0).topLefts[i]);
                double crt = maxCrt;
                for (int i = 0; i < ITER; i++) {
                    double temp = 2.0 + (1.0 - 2.0) * i / ITER;
                    int m = (int)rng.randRange(input.m);
                    int bk = state.topLefts[m];
                    state.moveTo(m, (int)rng.randRange(input.n - input.oils[m].maxI) * input.n + (int)rng.randRange(input.n - input.oils[m].maxJ));
                    double next = hashLikelihood.getOrDefault(state.hash, sim.lnProbState(state));
                    if (!hashLikelihood.containsKey(state.hash)) {
                        hashLikelihood.put(state.hash, next);
                        if (next - maxCrt >= -10.0) pool.add(new OilLayout(state.hash, next, 0, state.topLefts, state.volumes));
                    }
                    if (crt <= next || rng.genBool(Math.exp((next - crt) / temp))) crt = next;
                    else state.moveTo(m, bk);
                }
            }

            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn);
                sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            while (pool.size() > 2 && pool.get(pool.size()-1).pxIfR < 1e-9) pool.remove(pool.size()-1);

            if (pool.get(0).pxIfR > 0.9) {
                BitSet bits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (bits.get(i)) T.add(i);
                if (sim.ans(T)) break;
            } else {
                QueryEvaluator qe = new QueryEvaluator(input, pool);
                List<Integer> bestQ = new ArrayList<>();
                // 簡易的なランダム占いを生成
                for (int i = 0; i < input.n2; i++) if (rng.genBool(0.5)) bestQ.add(i);
                if (bestQ.isEmpty()) bestQ.add(0);
                sim.query(bestQ);
                state.addQuery(bestQ);
            }
        }
    }
}

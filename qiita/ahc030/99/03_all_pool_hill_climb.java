import java.util.*;
public class Main {
    Scanner sc = new Scanner(System.in);
    long start = System.currentTimeMillis();
    double erf0(double x) {
        // 誤差関数の近似
        double p = 0.3275911, a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429;
        int sign = (x < 0) ? -1 : 1;
        x = Math.abs(x);
        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
        return sign * y;
    }
    double erf(double a) { return erf0(a); }
    double exp(double a) { return Math.exp(a); }
    double log(double a) { return Math.log(a); }
    int max(int a, int b) { return Math.max(a, b); }
    long round(double a) { return Math.round(a); }
    void shuffle(List<OilLayout> list, Xorshift rnd) {
        int size = list.size();
        for (int i=size; i>1; i--) {
            int p=i-1;
            int q=(int)rnd.randrange(i);
            list.set(p, list.set(q, list.get(p)));
        }
    }
    double sqrt(double a) { return Math.sqrt(a); }
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
            if (cmp!=0) return cmp;
            cmp = Integer.compare(a.second, b.second);
            if (cmp!=0) return cmp;
            return 0;
        }
    }
    class PairListInt {
        IntList query_coordinates;
        int ret;
        PairListInt(IntList q, int r) {
            this.query_coordinates = q;
            this.ret = r;
        }
    }
    class BoolList {
        ArrayList<Boolean> list;
        BoolList() {
            this.list = new ArrayList<>();
        }
        BoolList(int size, boolean def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
        }
        void set(int idx, boolean val) {
            list.set(idx, val);
        }
        boolean get(int idx) {
            return list.get(idx);
        }
        int size() {
            return list.size();
        }
    }
    class BoolListList {
        ArrayList<ArrayList<Boolean>> list;
        BoolListList() {
            list = new ArrayList<>();
        }
        BoolListList(int size1, int size2, boolean def) {
            list = new ArrayList<>(size1);
            for (int i = 0; i < size1; i++) {
                list.add(new ArrayList<>(size2));
                for (int j = 0; j < size2; j++) {
                    list.get(i).add(def);
                }
            }
        }
        void set(int i, int j, boolean val) {
            list.get(i).set(j, val);
        }
        boolean get(int i, int j) {
            return list.get(i).get(j);
        }
    }
    class ByteList {
        ArrayList<Byte> list;
        ByteList() {
            this.list = new ArrayList<>();
        }
        ByteList(int size, byte def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
        }
        void add(byte val) {
            list.add(val);
        }
        void add(int idx, byte val) {
            list.set(idx, (byte)(list.get(idx) + val));
        }
        byte get(int idx) {
            return list.get(idx);
        }
        int size() {
            return list.size();
        }
        boolean empty() {
            return list.isEmpty();
        }
    }
    class ByteListList {
        ArrayList<ArrayList<Byte>> list;
        ByteListList() {
            this.list = new ArrayList<>();
        }
        ByteListList(int size1) {
            this.list = new ArrayList<>();
            for (int i = 0; i < size1; i++) {
                list.add(new ArrayList<>());
            }
        }
        ByteListList(int size1, int size2, byte def) {
            this.list = new ArrayList<>(size1);
            for (int i = 0; i < size1; i++) {
                list.add(new ArrayList<>(size2));
                for (int j = 0; j < size2; j++) {
                    list.get(i).add(def);
                }
            }
        }
        void add(int idx1, byte val) {
            list.get(idx1).add(val);
        }
        byte get(int idx1, int idx2) {
            return list.get(idx1).get(idx2);
        }
    }
    class DoubleList {
        ArrayList<Double> list;
        DoubleList() {
            this.list = new ArrayList<>();
        }
        DoubleList(int size, double def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
        }
        void add(double val) {
            list.add(val);
        }
        void add(int idx, double val) {
            list.set(idx, list.get(idx) + val);
        }
        void set(int idx, double val) {
            list.set(idx, val);
        }
        double get(int idx) {
            return list.get(idx);
        }
        int size() {
            return list.size();
        }
    }
    class DoubleListList {
        ArrayList<DoubleList> list;
        DoubleListList() {
            this.list = new ArrayList<>();
        }
        DoubleListList(int size1, int size2, double def) {
            this.list = new ArrayList<>(size1);
            for (int i = 0; i < size1; i++) {
                list.add(new DoubleList(size2, def));
            }
        }
        void add(DoubleList val) {
            list.add(val);
        }
        double get(int idx1, int idx2) {
            return list.get(idx1).get(idx2);
        }
        int size() {
            return list.size();
        }
    }
    class IntList implements Iterable<Integer> {
        ArrayList<Integer> list;
        IntList() {
            this.list = new ArrayList<>();
        }
        IntList(int size, int def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
        }
        IntList(IntList that) {
            this.list = new ArrayList<>(that.list);
        }
        void add(int val) {
            list.add(val);
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
        public Iterator<Integer> iterator() {
            return list.iterator();
        }
    }
    class IntListList implements Iterable<IntList> {
        ArrayList<IntList> list;
        IntListList() {
            this.list = new ArrayList<>();
        }
        IntListList(int size1, int size2, int def) {
            this.list = new ArrayList<>(size1);
            for (int i = 0; i < size1; i++) {
                list.add(new IntList(size2, def));
            }
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
    class PairDoubleList {
        ArrayList<PairDouble> list;
        PairDoubleList() {
            this.list = new ArrayList<>();
        }
        PairDoubleList(int size, PairDouble def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
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
    class PairDoubleIntList implements Iterable<PairDoubleInt> {
        ArrayList<PairDoubleInt> list;
        PairDoubleIntList() {
            this.list = new ArrayList<>();
        }
        PairDoubleIntList(int size, PairDoubleInt def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
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
    class PairDoubleListListList {
        ArrayList<ArrayList<PairDoubleList>> list;
        PairDoubleListListList() {
            this.list = new ArrayList<>();
        }
        PairDoubleListListList(int size1, int size2) {
            this.list = new ArrayList<>(size1);
            for (int i = 0; i < size1; i++) {
                list.add(new ArrayList<>(size2));
                for (int j = 0; j < size2; j++) {
                    list.get(i).add(new PairDoubleList());
                }
            }
        }
        PairDoubleList get(int i, int j) {
            return list.get(i).get(j);
        }
    }
    class PairIntList implements Comparable<PairIntList>, Iterable<PairInt> {
        ArrayList<PairInt> list;
        PairIntList() {
            this.list = new ArrayList<>();
        }
        PairIntList(int size, PairInt def) {
            this.list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(def);
            }
        }
        void add(PairInt val) {
            list.add(val);
        }
        PairInt get(int idx) {
            return list.get(idx);
        }
        int size() {
            return list.size();
        }
        @Override
        public int compareTo(PairIntList that) {
            for (int i = 0; i < size(); i++) {
                PairInt pi1 = this.get(i);
                PairInt pi2 = that.get(i);
                int cmp = PairInt.compare(pi1, pi2);
                if (cmp!=0) return cmp;
            }
            return 0;
        }
        @Override
        public Iterator<PairInt> iterator() {
            return list.iterator();
        }
    }
/*
相互情報量を用いて占う
*/

final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

class Xorshift
{
    Xorshift(long seed) { x_ = seed; }

    // [0, stop)
    long randrange(long stop)
    {
        assert(stop > 0);
        next();
        return x_ % stop;
    }

    // [start, stop)
    long randrange(long start, long stop)
    {
        assert(start < stop);
        next();
        return start + x_ % (stop - start);
    }

    // [a, b]
    long randint(long a, long b)
    {
        assert(a <= b);
        return randrange(a, b + 1);
    }

    // [0.0, 1.0]
    double random()
    {
        next();
        return (double)(x_) * (1.0 / (double)(Long.MAX_VALUE)); //java UINT64_MAXからINT64_MAX相当
    }

    // [a, b] or [b, a]
    double uniform(double a, double b) { return a + (b - a) * random(); }

    long next()
    {
        x_ ^= x_ << 13;
        x_ ^= x_ >>> 17; //java 論理シフト
        x_ ^= x_ << 5;
        x_ &= max(); //java 64ビットを63ビット
        return x_;
    }

    boolean gen_bool(double p)
    {
        return random() < p;
    }

    long min() { return 0; }
    long max() { return Long.MAX_VALUE; } //java UINT64_MAXからINT64_MAX相当

    long x_;
}
Xorshift rng = new Xorshift(1);

// 油田の配置についての情報
class OilLayout
{
    // 油田配置をこの配置xだと過程したとき
    // 今までのクエリ結果Rが得られる確率の対数.
    // 対数尤度ともいう。
    double ln_pR_if_x;
    // 今までのクエリの結果Rから計算した,この配置になる事後確率P(x|R)
    double px_if_R;
    IntList top_lefts; // 油田の左上の座標
    ByteList volume;   // ある位置の油の埋蔵量
    OilLayout(double ln, double px, IntList tl, ByteList v) {
        this.ln_pR_if_x = ln;
        this.px_if_R = px;
        this.top_lefts = new IntList(tl);
        this.volume = v;
    }
}

// 油田の形についての情報
class OilShape implements Comparable<OilShape>
{
    int max_i, max_j;                      // 油田が収まる正方形の大きさ
    IntList coordinate_ids;            // 座標(i,j)の組を1変数で表したもの
    PairIntList coordinates; // 座標(i,j)の組
    // 座標(i,j)の組をマスクしたもの
    // 島の大きさN<=20なので、20*20のビットセットで表現できる
    BitSet mask = new BitSet(20 * 20);
    @Override
    public int compareTo(OilShape that) {
        return this.coordinates.compareTo(that.coordinates);
    }
}

class Input
{
    int n;              // 島の大きさ 10<=N<=20
    int n2;             // n*n
    int m;              // 油田の数 2<=M<=10
    double eps;            // 占い結果に用いるエラーパラメータ 0.01<=eps<=0.2
    ArrayList<OilShape> oils; // 油田の形に関する情報 .size()==M
    int total;          // 島全体の油田の埋蔵量の合計

    // 油田の左上の座標を受け取り、埋蔵量が1以上のマスの集合をbitsetで返す
    BitSet get_positives(final IntList top_lefts)
    {
        BitSet positives = new BitSet(20 * 20);
        for (int oil_id = 0; oil_id < m; ++oil_id)
        {
            int pij = top_lefts.get(oil_id);
            for (int ij : oils.get(oil_id).coordinate_ids)
            {
                positives.set(ij + pij);
            }
        }
        return positives;
    }

    // M個の油田の左上の座標を受け取り、その位置の油の埋蔵量を返す
    ByteList get_volume(final IntList top_lefts)
    {
        ByteList volume = new ByteList(n2, (byte)0);
        for (int oil_id = 0; oil_id < top_lefts.size(); ++oil_id)
        {
            int pij = top_lefts.get(oil_id);
            for (int ij : oils.get(oil_id).coordinate_ids)
            {
                volume.add(ij + pij, (byte)1);
            }
        }
        return volume;
    }
}

// プログラムが始まってからの時間を計測する
double get_time()
{
    // startをstaticにすることで、
    // 2回目以降のget_time()の呼び出しでも
    // プログラムが始まってからの時間を計測できる
    long now = System.currentTimeMillis();
    return (now - start) / 1000.0; //java ミリ秒単位の整数を秒単位の小数
}

Input read_input()
{
    Input input = new Input();
    input.n = sc.nextInt();
    input.m = sc.nextInt();
    input.eps = sc.nextDouble();
    input.n2 = input.n * input.n;
    input.oils = new ArrayList<>(input.m);
    for (int oil_id = 0; oil_id < input.m; ++oil_id) input.oils.add(new OilShape());

    for (int oil_id = 0; oil_id < input.m; ++oil_id)
    {
        int t_size;
        t_size = sc.nextInt();
        input.oils.get(oil_id).coordinates = new PairIntList();
        for (int i = 0; i < t_size; ++i)
        {
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();
            input.oils.get(oil_id).coordinates.add(new PairInt(x, y));
        }
    }
    input.total = 0;
    for (int oil_id = 0; oil_id < input.m; ++oil_id)
    {
        input.total += input.oils.get(oil_id).coordinates.size();
    }

    // 同じ図形かの判定を行うためにポリオミノをソートしてく
    Collections.sort(input.oils);

    for (int oil_id = 0; oil_id < input.m; ++oil_id)
    {
        var oil = input.oils.get(oil_id);
        oil.max_i = 0;
        oil.max_j = 0;
        for (PairInt pi : oil.coordinates)
        {
            int i = pi.first;
            int j = pi.second;
            oil.max_i = max(oil.max_i, i);
            oil.max_j = max(oil.max_j, j);
        }
        oil.coordinate_ids = new IntList();
        for (int i = 0; i < oil.coordinates.size(); ++i)
        {
            oil.coordinate_ids.add(oil.coordinates.get(i).first * input.n + oil.coordinates.get(i).second);
        }
        oil.mask.clear();
        for (int ij : oil.coordinate_ids)
        {
            oil.mask.set(ij, true);
        }
    }

    return input;
}

final double SMALL_VALUE = 1e-6; // すごく小さい値

// 油田1個分の状態
class OilState
{
    // top_left_query_volumes[top_left][q]は、
    // この油田の左上座標がtop_leftにあるとき、
    // q番目のクエリで占った座標集合の埋蔵量の合計を示している
    ByteListList top_left_query_volumes;
    OilState(final Input input)
    {
        top_left_query_volumes = new ByteListList(input.n2);
    }
}

class State
{
    ArrayList<OilState> oil_states;   // 油田の状態についてのリスト: M個
    IntList top_lefts;      // 油田の左上の座標. oil_satesに含めたかったが、OilLayoutにコピーして使うので別で持つ: M個
    ByteList volumes;       // ある位置の油の埋蔵量: N*N個
    ByteList query_volumes; // q番目のクエリで占った座標集合の埋蔵量の合計
    final Input input;

    // 全ての油田が0,0にあるときの状態を初期状態とする
    State(final Input input)
    {
        this.input = input;
        this.oil_states = new ArrayList<>(input.m);
        for (int oil_id = 0; oil_id < input.m; ++oil_id) oil_states.add(new OilState(input));
        top_lefts = new IntList(input.m, 0);
        volumes = new ByteList();
        query_volumes = new ByteList();
    }

    // 油田oil_idをnew_top_leftに移動する
    // 移動自体はtop_leftsの更新だが、
    // 移動に伴い、query_volumes,volumesを更新する
    void move_to(int oil_id, int new_top_left)
    {
        var oil_state = oil_states.get(oil_id);
        for (int q = 0; q < query_volumes.size(); ++q)
        {
            query_volumes.add(q, (byte)
                    (oil_state.top_left_query_volumes.get(new_top_left, q) 
                    - oil_state.top_left_query_volumes.get(top_lefts.get(oil_id), q)));
        }
        if (!volumes.empty())
        {
            for (int ij : input.oils.get(oil_id).coordinate_ids)
            {
                volumes.add(ij + top_lefts.get(oil_id), (byte)(-1));
                volumes.add(ij + new_top_left, (byte)(1));
            }
        }
        top_lefts.set(oil_id, new_top_left);
    }

    // 占いクエリを投げた後、今回占った座標集合に含まれる埋蔵量を記録する
    void add_query(final IntList query_coordinates)
    {
        BoolList in_query=new BoolList(input.n2, false);
        for (int ij : query_coordinates)
        {
            in_query.set(ij, true);
        }
        for (int oil_id = 0; oil_id < input.m; ++oil_id)
        {
            var oil = input.oils.get(oil_id);
            var oil_state = oil_states.get(oil_id);
            for (int di = 0; di < input.n - oil.max_i; ++di)
            {
                for (int dj = 0; dj < input.n - oil.max_j; ++dj)
                {
                    int top_left = di * input.n + dj;
                    byte c = 0;
                    for (int ij : input.oils.get(oil_id).coordinate_ids)
                    {
                        if (in_query.get(top_left + ij))
                        {
                            c += 1;
                        }
                    }
                    oil_state.top_left_query_volumes.add(top_left, c);
                }
            }
        }
        ByteList volume = input.get_volume(top_lefts);
        byte c = 0;
        for (int ij : query_coordinates)
        {
            c += volume.get(ij);
        }
        query_volumes.add(c);
    }
}

class Sim
{
    int n, n2, m, total;
    double eps;
    // 過去の占いの(油田配置、占い結果)の集合
    ArrayList<PairListInt> queries;
    // 既に油田配置を答えるクエリを投げて失敗した油田配置の集合
    IntListList failed;
    // クエリサイズk、埋蔵量総量Sに対して、
    // pr_if_xがもつrの値の下限
    IntListList pr_if_x_lb;
    // 真の配置xを過程したときに占い結果がrになる確率(尤度とも呼ぶ)
    // 真の配置xを過程したときはクエリサイズk、埋蔵量総量Sも固定されるため、
    // クエリサイズk、埋蔵量総量Sの時に占い結果がrになる確率を記録しておけばいい
    // 小さすぎる確率は無視するため、配列はr=lb以上のものだけ格納する。
    // pr_if_x[k][S][r-lb] = (prob, log(prob))
    PairDoubleListListList pr_if_x;
    // クエリごとにあり得る埋蔵量総量Sごとに
    // 埋蔵量Sのときにそのクエリで得られた結果になる確率P(r|S)の対数を記録
    DoubleListList ln_pr_if_s_query;
    // 残りクエリ回数. 2*N*N回までクエリを投げられる
    int rem;

    Sim(final Input input)
    {
        this.n = input.n;
        this.n2 = input.n2;
        this.m = input.m;
        this.total = input.total;
        this.eps = input.eps;
        this.rem = input.n * input.n * 2;
        this.queries = new ArrayList<>();
        this.failed = new IntListList();
        // クエリサイズk、埋蔵量総量S、クエリの結果rに対する尤度は事前に計算しておく
        this.pr_if_x_lb = new IntListList(n * n + 1, total + 1, 0);
        this.pr_if_x = new PairDoubleListListList(n * n + 1, total + 1);
        this.ln_pr_if_s_query = new DoubleListList();
        for (int k = 1; k <= n * n; ++k)
        {
            for (int S = 0; S <= total; ++S)
            {
                // muとsigmaは問題文の計算式をそのまま使うだけで求まる
                double mu = ((double)k - (double)S) * eps + (double)S * (1.0 - eps);
                double sigma = sqrt(k * eps * (1.0 - eps));
                // 尤度が小さすぎるrがどこかわからないため、
                // 尤度が最も高いmuから順に尤度を求め、
                // 尤度が小さすぎたタイミングで止める
                for (int r = (int)(round(mu)); r >= 0; --r)
                {
                    double prob = likelihood(mu, sigma, r);
                    if (prob < SMALL_VALUE)
                    {
                        pr_if_x_lb.set(k, S, r + 1);
                        break;
                    }
                    pr_if_x.get(k, S).add(new PairDouble(prob, log(prob)));
                }
                // rの値について降順になっているため、昇順になおす
                pr_if_x.get(k, S).reverse();
                // muを基準に対称なので、muより大きいrについても同様に計算する
                for (int r = (int)(round(mu)) + 1;; ++r)
                {
                    double prob = likelihood(mu, sigma, r);
                    if (prob < SMALL_VALUE)
                    {
                        break;
                    }
                    pr_if_x.get(k, S).add(new PairDouble(prob, log(prob)));
                }
            }
        }
    }

    // 油田配置をあてるクエリを投げる
    // 失敗した場合は失敗した座標郡をfailedに記録する
    boolean ans(final IntList T)
    {
        if (rem == 0)
        {
            System.err.println("!log giveup ");
            System.exit(0);
        }
        --rem;
        System.out.print("a " + T.size() + " ");
        for (int ij : T)
        {
            System.out.print(ij / n + " " + ij % n + " ");
        }
        System.out.println();
        int ret;
        ret = sc.nextInt();
        if (ret == 1)
        {
            return true;
        }
        failed.add(T);
        return false;
    }

    // 指定したマスの集合の埋蔵量を占う
    int query(final IntList query_coordinates)
    {
        if (rem == 0)
        {
            System.err.println("!log giveup ");
            System.exit(0);
        }
        --rem;
        System.out.print("q " + query_coordinates.size() + " ");
        for (int ij : query_coordinates)
        {
            System.out.print(ij / n + " " + ij % n + " ");
        }
        System.out.println();
        int ret;
        ret = sc.nextInt();
        // クエリの結果を記録する
        queries.add(new PairListInt(query_coordinates, ret));
        // 結果retが得られた.
        // 指定したマス集合の真の埋蔵量総量がわからないため、
        // あり得る埋蔵量総量全てについて、
        // 埋蔵量Sのときに結果がretになる確率P(ret|S)を求める
        DoubleList ln_pr_if_s = new DoubleList(total + 1, 0.0);
        int k = query_coordinates.size();
        for (int S = 0; S <= total; ++S)
        {
            double kS = (double)k - (double)S;
            double kSeps = kS * eps;
            double meps = 1.0 - eps;
            double mu = kSeps + S * meps;
            double sigma = sqrt(k * eps * meps);
            ln_pr_if_s.set(S, log(likelihood(mu, sigma, ret)));
        }

        ln_pr_if_s_query.add(ln_pr_if_s);
        return ret;
    }

    // 指定したマスの埋蔵量を取得する
    // 1マスなら正確な値がわかる
    int mine(int i, int j)
    {
        if (rem == 0)
        {
            System.err.println("!log giveup");
            System.exit(0);
        }
        --rem;
        System.out.println("q 1 " + i + " " + j);
        int ret;
        ret = sc.nextInt();
        return ret;
    }

    // 平均mean, 標準偏差std_devに従う正規分布において、
    // resが観測される確率を求める
    double likelihood(double mean, double std_dev, int res)
    {
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
        double b = (double)res + 0.5;

        if (res == 0)
        {
            return normal_cdf(mean, std_dev, b);
        }
        else
        {
            double a = (double)res - 0.5;
            return normal_cdf(mean, std_dev, b) - normal_cdf(mean, std_dev, a);
        }
    }

    // 平均mean, 標準偏差std_devに従う正規分布において、a以上b以下の確率を求める
    double probability_in_range(double mean, double std_dev, double a, double b)
    {
        double p_a = normal_cdf(mean, std_dev, a);
        double p_b = normal_cdf(mean, std_dev, b);
        return p_b - p_a;
    }

    // 累積分布関数
    // 平均mean, 標準偏差std_devに従う正規分布において、x以下の確率を求める
    double normal_cdf(double mean, double std_dev, double x)
    {
        return 0.5 * (1.0 + erf((x - mean) / (std_dev * sqrt(2.0))));
    }

    /// 時間切れしたときはBFSで掘る。
    void giveup()
    {
        System.err.println("!log giveup");
        ArrayDeque<PairInt> que = new ArrayDeque<>();
        que.add(new PairInt(n / 2, n / 2));
        IntList list = new IntList();
        int rem = total;
        BoolListList used = new BoolListList(n, n, false);

        while (!que.isEmpty())
        {
            PairInt pi = que.poll();
            int i = pi.first;
            int j = pi.second;
            if (used.get(i, j))
                continue;
            used.set(i, j, true);

            int ret = mine(i, j);
            if (ret > 0)
            {
                list.add(i * n + j);
                rem -= ret;
                if (rem == 0)
                    break;
            }

            for (final var dij : DIJ)
            {
                int di = dij[0];
                int dj = dij[1];
                int i2 = i + di;
                int j2 = j + dj;
                if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < n)
                {
                    if (ret == 0)
                    {
                        que.add(new PairInt(i2, j2));
                    }
                    else
                    {
                        que.addFirst(new PairInt(i2, j2));
                    }
                }
            }
        }
        ans(list);
    }

    // volumesとfailed_coordinatesが異なるかどうかを返す
    boolean is_different(final ByteList volumes, final IntList failed_coordinates)
    {
        for (final var ij : failed_coordinates)
        {
            if (volumes.get(ij) == 0)
            {
                return true;
            }
        }
        return false;
    }

    // 油田配置がtop_leftsにある場合、
    // q番目のクエリで占った油田集合の埋蔵量合計
    byte get_query_volume(final ArrayList<OilState> oil_states, int q, final IntList top_lefts)
    {
        byte S = 0;
        for (int oil_id = 0; oil_id < top_lefts.size(); ++oil_id)
        {
            var oil_state_p = oil_states.get(oil_id);
            var ij = top_lefts.get(oil_id);
            int p_volume = oil_state_p.top_left_query_volumes.get(ij, q);
            if (p_volume > 0)
            {
                S += p_volume;
            }
        }
        return S;
    }

    // 油田配置がこの状態になる確率を求める
    // vs: 各座標の埋蔵量
    // top_lefts: 油田の左上座標郡
    double get_ln_pR_if_x(final ArrayList<OilState> oil_states, final ByteList volumes, final IntList top_lefts)
    {
        // 既に失敗した油田配置の集合に含まれる油田配置の場合、対数尤度を非常に小さい値にする
        for (final var failed_coordinates : failed)
        {
            boolean skip = is_different(volumes, failed_coordinates);

            if (!skip)
            {
                int size = 0;
                for (int ij = 0; ij < n2; ++ij)
                {
                    if (volumes.get(ij) > 0)
                    {
                        ++size;
                    }
                }
                if (size == failed_coordinates.size())
                {
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
        for (int q = 0; q < queries.size(); ++q)
        {
            // この油田配置において、q番目のクエリで占った油田の埋蔵量の合計を求める
            // q番目のクエリを打った時のlog(P(ret|S))は記録済みであり、
            // 配置xにおけるSを求めることで、
            // log(P(ret|x)) = log(P(ret|S))を求めることができる
            byte S = get_query_volume(oil_states, q, top_lefts);
            ln_pR_if_x += ln_pr_if_s_query.get(q, S);
        }
        return ln_pR_if_x;
    }

    double ln_prob_state(final State state)
    {
        for (final var failed_coordinates : failed)
        {
            boolean skip = is_different(state.volumes, failed_coordinates);
            if (!skip)
            {
                int size = 0;
                for (int ij = 0; ij < n2; ++ij)
                {
                    if (state.volumes.get(ij) > 0)
                    {
                        ++size;
                    }
                }
                if (size == failed_coordinates.size())
                {
                    return -1e20;
                }
            }
        }
        double prob = 0.0;
        for (int q = 0; q < ln_pr_if_s_query.size(); ++q)
        {
            prob += ln_pr_if_s_query.get(q, state.query_volumes.get(q));
        }
        return prob;
    }
}

class Query
{
    BoolList in_query;  // ある位置の油の埋蔵量がクエリされているか : N*N個
    ByteList volume; // 油田の埋蔵量のリスト : M個
    int coordinate_size; // クエリに含めるマスの数
    ArrayList<OilLayout> pool; // 油田の状態についてのリスト

    Query(final Input input, ArrayList<OilLayout> pool)
    {
        this.in_query = new BoolList(input.n2, false);
        this.volume = new ByteList(pool.size(), (byte)0);
        this.coordinate_size = 0;
        this.pool = pool;
    }
    // 指定したマスをクエリに含めるかどうかを反転する
    void flip(int ij)
    {
        if (in_query.get(ij))
        {
            in_query.set(ij, false);
            for (int x = 0; x < pool.size(); ++x)
            {
                volume.add(x, (byte)(-pool.get(x).volume.get(ij)));
            }
            --coordinate_size;
        }
        else
        {
            in_query.set(ij, true);
            for (int x = 0; x < pool.size(); ++x)
            {
                volume.add(x, (byte)(pool.get(x).volume.get(ij)));
            }
            ++coordinate_size;
        }
    }
    // 占うマス集合を取得する
    IntList get_coordinates()
    {
        IntList result = new IntList();
        for (int ij = 0; ij < in_query.size(); ++ij)
        {
            if (in_query.get(ij))
            {
                result.add(ij);
            }
        }
        return result;
    }
    // 占いの良さを評価する
    double eval(Sim sim)
    {
        int k = coordinate_size;
        // ln_pr[r] = クエリ結果がrとなる確率の対数
        // 最後にlogをとるまで、普通の確率として計算する
        DoubleList ln_pr = new DoubleList();
        for (int x = 0; x < pool.size(); ++x)
        {
            int v = volume.get(x);
            int lb = sim.pr_if_x_lb.get(k, v);
            while (ln_pr.size() < lb + sim.pr_if_x.get(k, v).size())
            {
                ln_pr.add(0.0);
            }
            double px = pool.get(x).px_if_R;
            // 公式 p(r)=Σp(r|x)p(x)
            // この公式を使って、p(r)を求める
            for (int pi = 0; pi < sim.pr_if_x.get(k, v).size(); ++pi)
            {
                final var pr_if_x = sim.pr_if_x.get(k, v).get(pi).first;
                ln_pr.add(lb + pi, pr_if_x * px);
            }
        }
        for (int x = 0; x < ln_pr.size(); ++x)
        {
            ln_pr.set(x, log(ln_pr.get(x)));
        }
        // I(X;R) = ΣΣp(x,r)log(p(x,r)/(p(x)p(r))
        //        = ΣΣp(r|x)p(x)log(p(r|x)p(x)/p(x)p(r))
        //        = ΣΣp(r|x)p(x)log(p(r|x)/p(r))
        //        = ΣΣp(r|x)p(x)(log(p(r|x))-log(p(r)))
        // この式を使って、相互情報量を求める
        double info = 0.0;
        for (int x = 0; x < pool.size(); ++x)
        {
            double px = pool.get(x).px_if_R;
            int v = volume.get(x);
            int lb = sim.pr_if_x_lb.get(k, v);
            for (int pi = 0; pi < sim.pr_if_x.get(k, v).size(); ++pi)
            {
                final var pr_if_x = sim.pr_if_x.get(k, v).get(pi).first;
                final var ln_pr_if_x = sim.pr_if_x.get(k, v).get(pi).second;
                final var ln_prr = ln_pr.get(lb + pi);
                info += pr_if_x * px * (ln_pr_if_x - ln_prr);
            }
        }
        // コストは1/sqrt(k)なので、
        // sqrt(k)倍することは、コストで割ることと同じ
        // コストあたりの相互情報慮を求める
        return info * sqrt((double)k);
    }
}

// プールの確率を正規化する
void normalize_pool(ArrayList<OilLayout> pool)
{
    double total = 0;
    for (final var layout : pool)
    {
        total += layout.px_if_R;
    }
    for (var layout : pool)
    {
        layout.px_if_R /= total;
    }
}

// プールの油田配置の全座標の埋蔵量を計算する
void set_volume(ArrayList<OilLayout> pool, final Input input)
{
    for (var layout : pool)
    {
        layout.volume = input.get_volume(layout.top_lefts);
    }
}

// 占いクエリを取得する
IntList getDivinationQuery(
    final Input input, final ArrayList<OilLayout> pool,
    Sim sim)
{
    // クエリを作成
    Query query = new Query(input, pool);
    // 全ての配置が同じ埋蔵量だと想定される座標以外で1点だけ占うクエリを評価する
    PairDoubleIntList query_coordinate_evals = new PairDoubleIntList();
    for (int ij = 0; ij < input.n2; ++ij)
    {
        // 評価前後にflipを挟むことで、
        // この座標1個だけでクエリを作成するときの情報量を計算する
        query.flip(ij);              // クエリにこの座標を含める
        double ev = query.eval(sim); // クエリにこの座標を含めたときの情報量を計算
        query.flip(ij);              // クエリにこの座標を含めない
        query_coordinate_evals.add(new PairDoubleInt(ev, ij));
    }
    query_coordinate_evals.sort((a, b) -> Double.compare(b.first, a.first));

    double crt = -1e100;
    for (int _i = 0; _i < 3; ++_i)
    {
        boolean change = false;
        // クエリに含める座標を1つずつ増やすか減らすかして、
        // 相互情報量/コストを最大化する山登り
        for (final PairDoubleInt pdi : query_coordinate_evals)
        {
            int ij = pdi.second;
            query.flip(ij);
            double eval = query.eval(sim);

            if (crt < eval)
            {
                crt = eval;
                change = true;
            }
            else
            {
                query.flip(ij);
            }
        }

        if (!change)
        {
            // どの座標を1点変えても相互情報量/コストが変わらない場合は終了
            break;
        }
    }
    IntList query_coordinates = query.get_coordinates();
    return query_coordinates;
}

void sort_pool(ArrayList<OilLayout> pool)
{
    pool.sort((a, b) -> Double.compare(b.ln_pR_if_x, a.ln_pR_if_x));
}

int main()
{
    Input input = read_input();

    Sim sim = new Sim(input);
    State state = new State(input);
    ArrayList<OilLayout> pool = new ArrayList<>();
    int ITER = 4000000 / (2 * input.n2);
    // 全ての配置を生成
    assert(input.m == 2);
    final var oil_a = input.oils.get(0);
    final var oil_b = input.oils.get(1);
    for (int i_a = 0; i_a < input.n - oil_a.max_i; ++i_a)
    {
        for (int j_a = 0; j_a < input.n - oil_a.max_j; ++j_a)
        {
            state.move_to(0, i_a * input.n + j_a);
            for (int i_b = 0; i_b < input.n - oil_b.max_i; ++i_b)
            {
                for (int j_b = 0; j_b < input.n - oil_b.max_j; ++j_b)
                {
                    state.move_to(1, i_b * input.n + j_b);
                    pool.add(
                        new OilLayout(0.0, 0.0, state.top_lefts, state.volumes));
                }
            }
        }
    }
    for (int t = 0;; ++t)
    {
        if (sim.rem == 0)
        {
            System.err.println("!There is no more query");
            break;
        }
        if (get_time() > 2.9)
        {
            sim.giveup();
            break;
        }

        // 各配置の対数尤度を更新する
        // t=0のときはpoolになにも入っていないので何もしない
        for (var layout : pool)
        {
            if (layout.volume.empty() && !sim.failed.empty())
            {
                layout.volume = input.get_volume(layout.top_lefts);
            }
            layout.ln_pR_if_x = sim.get_ln_pR_if_x(state.oil_states, layout.volume, layout.top_lefts);
        }
        // 同じ尤度の配置を散らすためにシャッフル
        shuffle(pool, rng);
        // 対数尤度が高い順に配置候補をソート
        sort_pool(pool);

        // この時点でpoolはソート済み
        var max_prob = pool.get(0).ln_pR_if_x;
        // 対数尤度から尤度に戻す
        // p(R|x) = exp(log(p(R|x)))
        for (var layout : pool)
        {
            layout.px_if_R = exp(layout.ln_pR_if_x - max_prob);
        }

        // 尤度から事後確率に変換する
        // p(x|R) = p(R|x)/Σp(R|x)
        normalize_pool(pool);

        final var best_layout = pool.get(0);
        var best_bits = input.get_positives(best_layout.top_lefts);

        double best_pool_prob = best_layout.px_if_R;
        set_volume(pool, input);

        if (best_pool_prob > 0.8)
        {
            // 自信があるとき、推測をう
            IntList T_vec = new IntList();
            for (int ij = 0; ij < input.n2; ++ij)
            {
                if (best_bits.get(ij))
                {
                    T_vec.add(ij);
                }
            }
            if (sim.ans(T_vec))
            {
                break;
            }
            else if (sim.failed.size() == 1)
            {
                state.volumes = input.get_volume(state.top_lefts);
            }
        }
        else
        {
            var query_coordinates = getDivinationQuery(input, pool, sim);
            // 占いの評価値の方が高い場合、占いを行う
            sim.query(query_coordinates);
            state.add_query(query_coordinates);
        }
    }

    System.err.println("!Time = " + get_time());
    System.err.println("!log miss " + sim.failed.size());
    System.err.println("!main end");
    return 0;
}
public static void main(String[] args) {
    new Main().main();
}
}

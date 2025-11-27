/**
 * 木を1つ構築することを1手とみなしたビームサーチ解法（悪い例）
 */
#include <bits/stdc++.h>
using namespace std;

class Xorshift
{
public:
    using result_type = uint64_t;

    explicit Xorshift(uint64_t seed) : x_(seed) { assert(seed); }

    // [0, stop)
    uint64_t randrange(uint64_t stop)
    {
        assert(stop > 0);
        next();
        return x_ % stop;
    }

    // [start, stop)
    uint64_t randrange(uint64_t start, uint64_t stop)
    {
        assert(start < stop);
        next();
        return start + x_ % (stop - start);
    }

    // [a, b]
    uint64_t randint(uint64_t a, uint64_t b)
    {
        assert(a <= b);
        return randrange(a, b + 1);
    }

    // [0.0, 1.0]
    double random()
    {
        next();
        return static_cast<double>(x_) * (1.0 / static_cast<double>(UINT64_MAX));
    }

    // [a, b] or [b, a]
    double uniform(double a, double b) { return a + (b - a) * random(); }

    uint64_t next()
    {
        x_ ^= x_ << 13;
        x_ ^= x_ >> 17;
        x_ ^= x_ << 5;
        return x_;
    }

    bool gen_bool(double p)
    {
        return random() < p;
    }

    // Add this operator to use with std::shuffle
    uint64_t operator()()
    {
        return next();
    }

    static constexpr uint64_t min() { return 0; }
    static constexpr uint64_t max() { return UINT64_MAX; }

private:
    uint64_t x_;
};
Xorshift rng(1);
constexpr int N = 1000, H = 10;
int M;
vector<vector<int>> G(N);
int A[N], X[N], Y[N];
void input()
{
    int _;
    cin >> _ >> M >> _;
    for (int i = 0; i < N; i++)
        cin >> A[i];
    for (int i = 0; i < M; i++)
    {
        int x, y;
        cin >> x >> y;
        G[x].push_back(y);
        G[y].push_back(x);
    }
    for (int i = 0; i < N; i++)
        cin >> X[i] >> Y[i];
}

struct PreProcessedData
{
    vector<uint64_t> using_hash;
    void preProcess()
    {
        setUsingHash();
    }

    void setUsingHash()
    {
        using_hash.resize(N);
        for (int i = 0; i < N; i++)
            using_hash[i] = rng.next();
    }
};
PreProcessedData pre_processed_data;

/// @brief 各頂点の親を出力
/// @param answer 頂点iの親の頂点
void output(vector<int> &answer)
{
    for (int i = 0; i < N; i++)
        cout << answer[i] << " \n"[i + 1 == N];
}

struct BeamSearch
{
    using ScoreType = int; // ゲームの評価スコアの型を決めておく。
    const int depth = H;
    const int beam_width = 150;

    /// @brief 状態
    struct State
    {
        ScoreType score;
        uint64_t hash;
        vector<int> parents; // 各頂点の親の頂点。-1は根、-2は未確定
        State(ScoreType score = 0) : score(score), parents(N, -2)
        {
        }
        bool operator<(const State &other) const
        {
            return score < other.score;
        }
        bool operator>(const State &other) const
        {
            return score > other.score;
        }

        void bfs(int root_i)
        {
            if (parents[root_i] != -2)
                return;
            queue<pair<int, int>> q;
            parents[root_i] = -1;
            q.push({0, root_i});
            hash ^= pre_processed_data.using_hash[root_i];
            score += A[root_i];
            while (!q.empty())
            {
                auto [d, parent] = q.front();
                q.pop();
                for (auto &&child : G[parent])
                {
                    if (parents[child] == -2 && d < H)
                    {
                        score += A[child] * (d + 2);
                        parents[child] = parent;
                        q.push({d + 1, child});
                        hash ^= pre_processed_data.using_hash[child];
                    }
                }
            }
        }

        /// @brief 現在の状態から、次の状態リストを作る
        /// @param next_states 次の状態
        /// @param hashes 検討済みの状態のハッシュ値リスト
        void expand(
            vector<State> &next_states, unordered_set<uint64_t> &hashes) const
        {
            for (int i = 0; i < N; i++)
            {
                State ps = *this;
                ps.bfs(i);
                if (hashes.insert(ps.hash).second)
                {
                    next_states.push_back(ps);
                }
            }
        }
    };

    /// @brief ビームサーチで行動を決定する
    /// @param state 初期状態
    /// @param beam_widths 深さごとのビーム幅
    /// @param beam_depth ビームの深さ
    /// @return 最も良い最終状態
    State solve(const State &state)
    {
        vector<State> now_beam;
        now_beam.emplace_back(state);
        vector<State> next_beam;
        while (true)
        {
            next_beam.clear();
            unordered_set<uint64_t> hashes;

            for (int i = 0; i < now_beam.size(); i++)
            {
                now_beam[i].expand(next_beam, hashes);
            }

            // 上位beam_width個を取り出す
            partial_sort(next_beam.begin(), next_beam.begin() + beam_width, next_beam.end(), greater<>());
            if (next_beam.size() > beam_width)
            {
                next_beam.resize(beam_width);
            }
            now_beam.swap(next_beam);
            auto best_state = now_beam[0];
            // 全ての頂点を使い終わっていたら終了
            bool is_all_determined = true;
            for (int i = 0; i < N; i++)
                if (best_state.parents[i] == -2)
                {
                    is_all_determined = false;
                    break;
                }
            if (is_all_determined)
                break;
        }
        auto best_state = now_beam[0];
        return best_state;
    }

    /// @brief greedyで解を求め、必要な情報を抜き出して出力する
    vector<int> getAnswer()
    {
        State best_state = solve(State());
        cerr << "score: " << 1 + best_state.score << endl;
        return best_state.parents;
    }
};
int main()
{
    ios::sync_with_stdio(false); // cin, coutの同期を切る
    cin.tie(0);                  // cinのバッファリングを切る
    cout.tie(0);                 // coutのバッファリングを切る
    input();
    pre_processed_data.preProcess();
    BeamSearch beam_search;
    vector<int> parents = beam_search.getAnswer();
    output(parents);
}
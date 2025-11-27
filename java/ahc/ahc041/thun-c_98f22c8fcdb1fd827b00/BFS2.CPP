/**
 * 木を1つ構築することを1手とみなしたgreedy解法(悪い例)
 */
#include <bits/stdc++.h>
using namespace std;

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

/// @brief 各頂点の親を出力
/// @param answer 頂点iの親の頂点
void output(vector<int> &answer)
{
    for (int i = 0; i < N; i++)
        cout << answer[i] << " \n"[i + 1 == N];
}

struct Greedy
{
    using ScoreType = int; // ゲームの評価スコアの型を決めておく。
    const int depth = H;

    /// @brief 状態
    struct State
    {
        ScoreType score;
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
                    }
                }
            }
        }

        /// @brief 現在の状態から、次の状態リストを作る
        /// @param next_states 次の状態
        void expand(
            vector<State> &next_states) const
        {
            for (int i = 0; i < N; i++)
            {
                State ps = *this;
                ps.bfs(i);
                next_states.push_back(ps);
            }
        }
    };

    /// @brief greedyで行動を決定する
    /// @param state 初期状態
    /// @return 最も良い最終状態
    State solve(const State &state)
    {
        State now_state = state;
        while (true)
        {
            vector<State> next_states;
            now_state.expand(next_states);
            if (next_states.empty())
                break;
            ScoreType best_score = -1;
            for (auto &&next_state : next_states)
            {
                if (next_state.score > best_score)
                {
                    best_score = next_state.score;
                    now_state = next_state;
                }
            }
            // 全ての頂点を使い終わっていたら終了
            bool is_all_determined = true;
            for (int i = 0; i < N; i++)
                if (now_state.parents[i] == -2)
                {
                    is_all_determined = false;
                    break;
                }
            if (is_all_determined)
                break;
        }
        return now_state;
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
    Greedy greedy;
    vector<int> parents = greedy.getAnswer();
    output(parents);
}
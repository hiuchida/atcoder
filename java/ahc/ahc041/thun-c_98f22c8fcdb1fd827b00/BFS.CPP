/**
 * 以下のルールで森を構築するルールベース解法
 * ルール
 *  各頂点を順番に根としてBFSで木を構築していき、
 *  全ての頂点を検討し終わるまでそれを繰り返す。
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

struct Rule
{

    /// @brief 状態
    struct State
    {
        vector<int> parents; // 各頂点の親の頂点。-1は根、-2は未確定
        State() : parents(N, -2)
        {
        }

        void bfs(int root_i)
        {
            if (parents[root_i] != -2)
                return;
            queue<pair<int, int>> q;
            parents[root_i] = -1;
            q.push({0, root_i});
            while (!q.empty())
            {
                auto [d, parent] = q.front();
                q.pop();
                for (auto &&child : G[parent])
                {
                    if (parents[child] == -2 && d < H)
                    {
                        parents[child] = parent;
                        q.push({d + 1, child});
                    }
                }
            }
        }
    };

    /// @brief ルールに従って行動を決定する
    /// @param state 初期状態
    /// @return 最も良い最終状態
    State solve(const State &state)
    {
        State now_state = state;
        for (int i = 0; i < N; i++)
            now_state.bfs(i);
        return now_state;
    }

    /// @brief ルールに従って解を求め、必要な情報を抜き出して出力する
    vector<int> getAnswer()
    {
        State best_state = solve(State());
        return best_state.parents;
    }
};
int main()
{
    ios::sync_with_stdio(false); // cin, coutの同期を切る
    cin.tie(0);                  // cinのバッファリングを切る
    cout.tie(0);                 // coutのバッファリングを切る
    input();
    Rule rule;
    vector<int> parents = rule.getAnswer();
    output(parents);
}
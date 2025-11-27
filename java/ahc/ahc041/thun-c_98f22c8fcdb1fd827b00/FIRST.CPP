/**
 * 提出して正の点数を得るためになにもしないプログラム
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

int main()
{
    ios::sync_with_stdio(false); // cin, coutの同期を切る
    cin.tie(0);                  // cinのバッファリングを切る
    cout.tie(0);                 // coutのバッファリングを切る
    input();
    vector<int> parents(N, -1);
    output(parents);
}
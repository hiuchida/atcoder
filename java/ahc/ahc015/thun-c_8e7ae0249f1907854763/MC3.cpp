#include <assert.h>
#include <math.h>
#include <iostream>
#include <vector>
#include <array>
#include <string>
#include <sstream>
#include <random>
#include <algorithm>
#include <queue>
#include <set>
using std::array, std::vector;
using std::cout, std::endl, std::cerr, std::cin;
using std::deque;
using std::pair;
using std::swap;
using std::tuple;
constexpr int END_TURN = 100; // ゲーム終了ターン

array<int, END_TURN> nexts{};
constexpr int F = 0;
constexpr int B = 1;
constexpr int L = 2;
constexpr int R = 3;
constexpr char ACTION_CHARS[] = "FBLR";

// 可能な行動一覧
vector<int> rule_actions(END_TURN, -1);

int main()
{
    for (int t = 0; t < END_TURN; t++)
    {
        cin >> nexts[t];
    }

    // イチゴは下にスイカは右上に、カボチャは左上に集める
    vector<vector<int>> rule_action_samples = {
        {F, B, B},
        {F, L, R},
        {F, L, R},
    };
    for (int t = 0; t < END_TURN - 1; t++)
    {
        int now = nexts[t];
        int next = nexts[t + 1];
        rule_actions[t] = rule_action_samples[now - 1][next - 1];
    }
    // 100回目はなんでもいい
    rule_actions[END_TURN - 1] = {F};
    // 毎ターンの入力と出力を繰り返す
    for (int t = 0; t < END_TURN; t++)
    {
        int pt;
        cin >> pt;
        int action = rule_actions[t];
        cout << ACTION_CHARS[action] << endl;
    }
    return 0;
}
#include <iostream>
#include <vector>
#include <array>
#include <string>
#include <array>
#include <vector>
#include <sstream>
#include <random>
#include <math.h>
#include <chrono>
#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
#include <assert.h>
// #define DUMPL(x) cerr << "L" << __LINE__ << "\t" << #x << "\t" << (x) << endl
// #define DEBUG(x) cerr << "L" << __LINE__ << "\t" << (x) << endl
#define DUMPL(x)
#define DEBUG(x)
using std::array, std::vector;
using std::cout, std::endl, std::cerr, std::cin;
using std::deque;
using std::pair;
using std::swap;
using std::tuple;
std::mt19937 mt_for_action(0);

// 時間を管理するクラス
class TimeKeeper
{
private:
    std::chrono::high_resolution_clock::time_point start_time_;
    std::chrono::high_resolution_clock::time_point before_time_;
    int64_t time_threshold_;
    int64_t end_turn_;
    int64_t turn_;

public:
    // 時間制限をミリ秒単位で指定してインスタンスをつくる。
    TimeKeeper(const int64_t &time_threshold, const int64_t end_turn)
        : start_time_(std::chrono::high_resolution_clock::now()),
          //   before_time_(start_time_),
          time_threshold_(time_threshold),
          end_turn_(end_turn),
          turn_(0)
    {
        before_time_ = start_time_;
    }
    void setTurn(const int t)
    {
        turn_ = t;
        this->before_time_ = std::chrono::high_resolution_clock::now();
    }

    // インスタンス生成した時から指定した時間制限を超過したか判定する。
    bool isTimeOver()
    {
        auto now = std::chrono::high_resolution_clock::now();
        auto whole_diff = now - this->start_time_;
        auto whole_count = std::chrono::duration_cast<std::chrono::milliseconds>(whole_diff).count();
        auto last_diff = now - this->before_time_;
        auto last_count = std::chrono::duration_cast<std::chrono::milliseconds>(last_diff).count();

        auto remaining_time = time_threshold_ - whole_count;
        auto now_threshold = remaining_time / (end_turn_ - this->turn_);
        return last_count >= now_threshold;
    }
};

constexpr int H = 10;
constexpr int W = 10;
constexpr int HW = H * W;
constexpr int END_TURN = 100; // ゲーム終了ターン

array<int, END_TURN> nexts{};

constexpr int F = 0;
constexpr int B = 1;
constexpr int L = 2;
constexpr int R = 3;
constexpr char ACTION_CHARS[] = "FBLR";
constexpr int dx[4] = {1, -1, 0, 0}; // 右、左、下、上への移動方向のx成分
constexpr int dy[4] = {0, 0, 1, -1}; // 右、左、下、上への移動方向のy成分

// 可能な行動一覧
array<int, 4> legal_actions = {F, B, L, R};

class State
{
private:
    array<array<int, W>, H> board_{};

public:
    int t_ = 0;
    State()
    {
    }

    // ゲームが終了したか判定する
    bool isDone() const
    {
        return this->t_ >= END_TURN;
    }

    // 指定したactionでゲームを1ターン進め、次のプレイヤー視点の盤面にする
    void advance(const int action)
    {
        // DUMPL(action);
        switch (action)
        {
        case F:
        {
            // DEBUG("F");
            for (int x = 0; x < W; x++)
            {
                int dest = 0;
                for (int y = dest; y < H; y++)
                {
                    if (this->board_[y][x] != 0)
                    {
                        swap(this->board_[dest][x], this->board_[y][x]);
                        ++dest;
                    }
                }
            }
            break;
        }
        case B:
        {
            // DEBUG("B");
            for (int x = 0; x < W; x++)
            {
                int dest = H - 1;

                for (int y = dest; y >= 0; y--)
                {
                    if (this->board_[y][x] != 0)
                    {
                        swap(this->board_[dest][x], this->board_[y][x]);
                        dest--;
                    }
                }
            }
            break;
        }

        case L:
        {
            // DEBUG("L");
            for (int y = 0; y < H; y++)
            {
                int dest = 0;
                for (int x = dest; x < W; x++)
                {
                    if (this->board_[y][x] != 0)
                    {
                        swap(this->board_[y][dest], this->board_[y][x]);
                        ++dest;
                    }
                }
            }
            break;
        }
        case R:
        {
            // DEBUG("R");
            for (int y = 0; y < H; y++)
            {
                int dest = W - 1;
                for (int x = dest; x >= 0; x--)
                {
                    if (this->board_[y][x] != 0)
                    {
                        swap(this->board_[y][dest], this->board_[y][x]);
                        dest--;
                    }
                }
            }
            break;
        }
        default:
            break;
        }
        ++this->t_;
    }

    void randomUpdate()
    {
        int reverse_t = END_TURN - this->t_;
        int pt = mt_for_action() % reverse_t + 1;
        update(pt);
    }
    void update(int pt)
    {
        for (int i = 0; i < H * W; i++)
        {
            int x = i % W;
            int y = i / W;
            if (this->board_[y][x] == 0)
            {
                --pt;
                if (pt == 0)
                {
                    this->board_[y][x] = nexts[this->t_];
                    return;
                }
            }
        }
    }

    int getGroupSize(int y, int x, array<array<int, W>, H> &checked) const
    {
        int candy = this->board_[y][x];
        checked[y][x] = true;
        assert(candy != 0);

        deque<pair<int, int>> q;
        q.emplace_back(y, x);
        int cnt = 0;
        while (!q.empty())
        {
            ++cnt;
            auto [now_y, now_x] = q.front();

            q.pop_front();
            for (int di = 0; di < 4; di++)
            {
                int ty = now_y + dy[di];
                int tx = now_x + dx[di];
                if (0 <= ty && ty < H && 0 <= tx && tx < W && checked[ty][tx] != 1 && this->board_[ty][tx] == candy)
                {
                    checked[ty][tx] = 1;
                    q.emplace_back(ty, tx);
                }
            }
        }
        assert(cnt != 0);
        return cnt;
    }

    // 勝敗情報を取得する
    double getScore(bool show = false) const
    {
        double score = 0;
        array<array<int, W>, H> checked{};
        for (int y = 0; y < H; y++)
        {
            for (int x = 0; x < W; x++)
            {
                if (this->board_[y][x] != 0 && checked[y][x] == 0)
                {
                    int group_size = getGroupSize(y, x, checked);
                    if (show)
                    {
                        cerr << "group_size\t" << y << "," << x << "\t" << group_size << endl;
                    }
                    score += group_size * group_size;
                }
            }
        }
        assert(score != 0);
        score /= (double)END_TURN * END_TURN; // 0~1の値をとるように正規化
        assert(score > 0);
        assert(score < 1);
        return score;
    }
    // 現在のゲーム状況を文字列にする
    std::string toString() const
    {
        std::stringstream ss{};
        ss << "t\t" << t_ << "\n";
        ss << "\n";
        for (int y = 0; y < H; y++)
        {
            for (int x = 0; x < W; x++)
            {
                ss << this->board_[y][x] << ",";
            }
            ss << "\n";
        }
        return ss.str();
    }

    void printTrueScores()
    {
        vector<int> bunbos(3 + 1);
        for (int t = 0; t < END_TURN; t++)
        {
            bunbos[nexts[t]]++;
        }
        int bunbo = 0;
        for (int i = 1; i <= 3; i++)
        {
            // cerr << "bunbo " << i << "\t" << bunbos[i] << "\t" << bunbos[i] * bunbos[i] << endl;
            bunbo += bunbos[i] * bunbos[i];
        }
        auto score_bunshi = this->getScore(false) * 100 * 100;
        cerr << this->toString() << endl;
        cerr << "score_bunshi\t" << score_bunshi << endl;
        cerr << "score_bunbo\t" << bunbo << endl;
        cerr << "score\t" << score_bunshi / (double)bunbo * 1000000 << endl;
    }
};

// ランダムに行動を決定する
int randomAction(const State &state)
{
    return legal_actions[mt_for_action() % (legal_actions.size())];
}

namespace montecarlo
{
    State global_state;
    // ランダムプレイアウトをして勝敗スコアを計算する
    double playout(State state)
    { // const&にすると再帰中にディープコピーが必要になるため、高速化のためポインタにする。(constでない参照でも可)

        state.advance(randomAction(state));
        int before_turn = state.t_;
        // while (!state.isDone())
        while (!state.isDone() && state.t_ - before_turn < 5)
        {
            state.randomUpdate();
            state.advance(randomAction(state));
        }
        return state.getScore();
    }

    constexpr const double C = 1;              // UCB1の計算に使う定数
    constexpr const int EXPAND_THRESHOLD = 10; // ノードを展開する閾値

    // MCTSの計算に使うノード
    class Node
    {
    private:
        vector<int> actions_; // stateにランダム要素があるため、stateではなく行動を保持
        double w_;

    public:
        std::vector<Node> child_nodes_;
        double n_;

        Node() : w_(0), n_(0) {}

        // 決定している行動をやりなおしつつ最後までプレイアウトする
        double redo_playout()
        {
            State state = global_state;
            for (const auto action : this->actions_)
            {
                state.advance(action);
                if (state.isDone())
                {
                    return state.getScore();
                }
                state.randomUpdate();
            }
            return playout(state);
        }

        // ノードの評価を行う
        double evaluate()
        {
            if (this->child_nodes_.empty())
            {
                double value = this->redo_playout();
                // DUMPL(value);
                this->w_ += value;
                ++this->n_;

                if (this->actions_.size() < END_TURN && this->n_ == EXPAND_THRESHOLD)
                    this->expand();
                assert(value < 1);
                assert(value > 0);
                return value;
            }
            else
            {
                double value = this->nextChildNode().evaluate();
                this->w_ += value;
                ++this->n_;
                assert(value < 1);
                assert(value > 0);
                return value;
            }
        }

        // ノードを展開する
        void expand()
        {
            this->child_nodes_.clear();
            for (const auto action : legal_actions)
            {
                this->child_nodes_.emplace_back();

                this->child_nodes_.back().actions_ = this->actions_;
                this->child_nodes_.back().actions_.emplace_back(action);
            }
        }

        // どのノードを評価するか選択する
        Node &nextChildNode()
        {
            for (auto &child_node : this->child_nodes_)
            {
                if (child_node.n_ == 0)
                {
                    // DUMPL(child_node.actions_.size());
                    return child_node;
                }
            }
            double t = 0;
            for (const auto &child_node : this->child_nodes_)
            {
                t += child_node.n_;
            }
            double best_value = -1;
            int best_action_index = -1;
            assert(!this->child_nodes_.empty());
            for (int i = 0; i < this->child_nodes_.size(); i++)
            {
                const auto &child_node = this->child_nodes_[i];
                double ucb1_value = child_node.w_ / child_node.n_ + (double)C * std::sqrt(2. * std::log(t) / child_node.n_);
                if (ucb1_value > best_value)
                {
                    best_action_index = i;
                    best_value = ucb1_value;
                }
            }
            assert(best_value > -1);
            return this->child_nodes_[best_action_index];
        }
    };

    // 制限時間(ms)を指定してMCTSで行動を決定する
    int mctsActionWithTimeThreshold(TimeKeeper &time_keeper)
    {
        Node root_node = Node();
        root_node.expand();
        for (int cnt = 0;; cnt++)
        {
            if (time_keeper.isTimeOver())
            {
                DUMPL(cnt);
                break;
            }
            root_node.evaluate();
        }

        int best_action_searched_number = -1;
        int best_action_index = -1;
        for (int i = 0; i < legal_actions.size(); i++)
        {
            int n = root_node.child_nodes_[i].n_;
            // DUMPL(i);
            // DUMPL(n);
            if (n > best_action_searched_number)
            {
                best_action_index = i;
                best_action_searched_number = n;
            }
        }
        return legal_actions[best_action_index];
    }
}

int main()
{
    cerr << "main.cpp" << endl;
    for (int t = 0; t < END_TURN; t++)
    {
        cin >> nexts[t];
    }
    montecarlo::global_state = State();
    vector<int> pts(END_TURN);
    vector<int> actions(END_TURN);
    TimeKeeper time_keeper{1950, END_TURN};
    for (int t = 0; t < END_TURN; t++)
    {
        time_keeper.setTurn(t);
        auto &pt = pts[t];
        cin >> pt;
        montecarlo::global_state.update(pt);
        // DUMPL(t);

        // int action = randomAction(montecarlo::global_state);
        int action = montecarlo::mctsActionWithTimeThreshold(time_keeper);
        actions[t] = action;
        cout << ACTION_CHARS[action] << endl;

        // if (t < 13)
        // {
        //     montecarlo::global_state.printTrueScores();
        //     DUMPL(ACTION_CHARS[action]);
        // }

        montecarlo::global_state.advance(actions[t]);
        // cout << ACTION_CHARS[0] << endl;
    }
    DEBUG("main end");
    // montecarlo::global_state.printTrueScores();
    // cerr << montecarlo::global_state.toString() << endl;
    {
        // State state{};
        // for (int t = 0; t < actions.size(); t++)
        // {

        //     state.update(pts[t]);
        //     state.advance(actions[t]);
        // }
        // cerr << "start state" << endl;
        // state.printTrueScores();

        // cerr << state.toString() << endl;

        // State state{};
        // cerr << "start state" << endl;
        // for (int t = 0; t < 1; t++)
        // {

        //     state.update(pts[t]);
        //     cerr << state.toString() << endl;
        //     // cerr << actions[t] << "\t" << (ACTION_CHARS[actions[t]]) << endl;

        //     // state.advance(actions[t]);
        //     state.advance(L);
        //     state.printTrueScores();
        // }
    }
    return 0;
}
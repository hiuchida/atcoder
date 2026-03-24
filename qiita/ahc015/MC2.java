import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random(0);

	double log(double a) {
		return (a == 0.0) ? -999 : Math.log(a);
	}

	double sqrt(double a) {
		return Math.sqrt(a);
	}

	static class PairInt {
		int first;
		int second;

		PairInt(int f, int s) {
			this.first = f;
			this.second = s;
		}
	}

	int mt_for_action() {
		return rand.nextInt(Integer.MAX_VALUE);
	}

// 時間を管理するクラス
	class TimeKeeper {
		long start_time_;
		long before_time_;
		long time_threshold_;
		long end_turn_;
		long turn_;

		// 時間制限をミリ秒単位で指定してインスタンスをつくる。
		TimeKeeper(final long time_threshold, final long end_turn) {
			start_time_ = System.currentTimeMillis();
			before_time_ = start_time_;
			time_threshold_ = time_threshold;
			end_turn_ = end_turn;
			turn_ = 0;
		}

		void setTurn(final int t) {
			turn_ = t;
			this.before_time_ = System.currentTimeMillis();
		}

		// インスタンス生成した時から指定した時間制限を超過したか判定する。
		boolean isTimeOver() {
			var now = System.currentTimeMillis();
			// var whole_diff = now - this.start_time_;
			var whole_diff = this.before_time_ - this.start_time_;
			var whole_count = whole_diff;
			var last_diff = now - this.before_time_;
			var last_count = last_diff;

			var remaining_time = time_threshold_ - whole_count;
			var now_threshold = remaining_time / (end_turn_ - this.turn_);
			return last_count >= now_threshold;
		}
	}

	int H = 10;
	int W = 10;
	int HW = H * W;
	int END_TURN = 100; // ゲーム終了ターン

	int[] nexts = new int[END_TURN];

	final int F = 0;
	final int B = 1;
	final int L = 2;
	final int R = 3;
	String ACTION_CHARS = "FBLR";
	int[] dx = { 1, -1, 0, 0 }; // 右、左、下、上への移動方向のx成分
	int[] dy = { 0, 0, 1, -1 }; // 右、左、下、上への移動方向のy成分

// 可能な行動一覧
	int[] legal_actions = { F, B, L, R };

	class State {
		int[][] board_;

		int t_ = 0;

		State() {
			this.board_ = new int[H][W];
		}

		State(State that) {
			this.board_ = new int[H][];
			for (int i = 0; i < H; i++)
				board_[i] = Arrays.copyOf(that.board_[i], W);
			t_ = that.t_;
		}

		// ゲームが終了したか判定する
		boolean isDone() {
			return this.t_ >= END_TURN;
		}

		void swap(int i1, int j1, int i2, int j2) {
			int tmp = board_[i1][j1];
			board_[i1][j1] = board_[i2][j2];
			board_[i2][j2] = tmp;
		}

		// 指定したactionでゲームを1ターン進め、次のプレイヤー視点の盤面にする
		void advance(final int action) {
			// DUMPL(action);
			switch (action) {
			case F: {
				// DEBUG("F");
				for (int x = 0; x < W; x++) {
					int dest = 0;
					for (int y = dest; y < H; y++) {
						if (this.board_[y][x] != 0) {
							swap(dest, x, y, x);
							++dest;
						}
					}
				}
				break;
			}
			case B: {
				// DEBUG("B");
				for (int x = 0; x < W; x++) {
					int dest = H - 1;

					for (int y = dest; y >= 0; y--) {
						if (this.board_[y][x] != 0) {
							swap(dest, x, y, x);
							dest--;
						}
					}
				}
				break;
			}

			case L: {
				// DEBUG("L");
				for (int y = 0; y < H; y++) {
					int dest = 0;
					for (int x = dest; x < W; x++) {
						if (this.board_[y][x] != 0) {
							swap(y, dest, y, x);
							++dest;
						}
					}
				}
				break;
			}
			case R: {
				// DEBUG("R");
				for (int y = 0; y < H; y++) {
					int dest = W - 1;
					for (int x = dest; x >= 0; x--) {
						if (this.board_[y][x] != 0) {
							swap(y, dest, y, x);
							dest--;
						}
					}
				}
				break;
			}
			default:
				break;
			}
			++this.t_;
		}

		void randomUpdate() {
			int reverse_t = END_TURN - this.t_;
			int pt = mt_for_action() % reverse_t + 1;
			update(pt);
		}

		void update(int pt) {
			for (int i = 0; i < H * W; i++) {
				int x = i % W;
				int y = i / W;
				if (this.board_[y][x] == 0) {
					--pt;
					if (pt == 0) {
						this.board_[y][x] = nexts[this.t_];
						return;
					}
				}
			}
		}

		int getGroupSize(int y, int x, boolean[][] checked) {
			int candy = this.board_[y][x];
			checked[y][x] = true;
			assert (candy != 0);

			ArrayDeque<PairInt> q = new ArrayDeque<>();
			q.add(new PairInt(y, x));
			int cnt = 0;
			while (!q.isEmpty()) {
				++cnt;
				PairInt pi = q.poll();
				int now_y = pi.first;
				int now_x = pi.second;

				for (int di = 0; di < 4; di++) {
					int ty = now_y + dy[di];
					int tx = now_x + dx[di];
					if (0 <= ty && ty < H && 0 <= tx && tx < W && checked[ty][tx] != true
							&& this.board_[ty][tx] == candy) {
						checked[ty][tx] = true;
						q.add(new PairInt(ty, tx));
					}
				}
			}
			assert (cnt != 0);
			return cnt;
		}

		// 勝敗情報を取得する
		double getScore(boolean show) {
			double score = 0;
			boolean[][] checked = new boolean[H][W];
			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					if (this.board_[y][x] != 0 && checked[y][x] == false) {
						int group_size = getGroupSize(y, x, checked);
						if (show) {
							System.err.println("group_size\t" + y + "," + x + "\t" + group_size);
						}
						score += group_size * group_size;
					}
				}
			}
			assert (score != 0);
			score /= (double) END_TURN * END_TURN; // 0~1の値をとるように正規化
			assert (score > 0);
			assert (score < 1);
			return score;
		}

		// 現在のゲーム状況を文字列にする
		@Override
		public String toString() {
			StringBuilder ss = new StringBuilder();
			ss.append("t\t" + t_ + "\n");
			ss.append("\n");
			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					ss.append(this.board_[y][x] + ",");
				}
				ss.append("\n");
			}
			return ss.toString();
		}

		void printTrueScores() {
			int[] bunbos = new int[3 + 1];
			for (int t = 0; t < END_TURN; t++) {
				bunbos[nexts[t]]++;
			}
			int bunbo = 0;
			for (int i = 1; i <= 3; i++) {
				System.err.println("bunbo " + i + "\t" + bunbos[i] + "\t" + (bunbos[i] * bunbos[i]));
				bunbo += bunbos[i] * bunbos[i];
			}
			var score_bunshi = this.getScore(true) * 100 * 100;
			System.err.println(this.toString());
			System.err.println("score_bunshi\t" + score_bunshi);
			System.err.println("score_bunbo\t" + bunbo);
			System.err.println("score\t" + score_bunshi / (double) bunbo * 1000000);
		}
	}

// ランダムに行動を決定する
	int randomAction(final State state) {
		return legal_actions[mt_for_action() % (legal_actions.length)];
	}

	State global_state;

	// ランダムプレイアウトをして勝敗スコアを計算する
	double playout(State state) {

		state.advance(randomAction(state));
		int before_turn = state.t_;
		// while (!state.isDone())
		while (!state.isDone() && state.t_ - before_turn < 5) {
			state.randomUpdate();
			state.advance(randomAction(state));
		}
		return state.getScore(false);
	}

	final double C = 1; // UCB1の計算に使う定数
	final int EXPAND_THRESHOLD = 10; // ノードを展開する閾値

	// MCTSの計算に使うノード
	class Node {
		ArrayList<Integer> actions_ = new ArrayList<>(); // stateにランダム要素があるため、stateではなく行動を保持
		double w_;

		ArrayList<Node> child_nodes_ = new ArrayList<>();
		double n_;

		Node() {
			w_ = 0;
			n_ = 0;
		}

		// 決定している行動をやりなおしつつ最後までプレイアウトする
		double redo_playout() {
			State state = new State(global_state);
			for (final var action : this.actions_) {
				state.advance(action);
				if (state.isDone()) {
					return state.getScore(false);
				}
				state.randomUpdate();
			}
			return playout(state);
		}

		// ノードの評価を行う
		double evaluate() {
			if (this.child_nodes_.isEmpty()) {
				double value = this.redo_playout();
				// DUMPL(value);
				this.w_ += value;
				++this.n_;

				if (this.actions_.size() < END_TURN && this.n_ == EXPAND_THRESHOLD)
					this.expand();
				assert (value < 1);
				assert (value > 0);
				return value;
			} else {
				double value = this.nextChildNode().evaluate();
				this.w_ += value;
				++this.n_;
				assert (value < 1);
				assert (value > 0);
				return value;
			}
		}

		// ノードを展開する
		void expand() {
			this.child_nodes_.clear();
			for (final var action : legal_actions) {
				Node node = new Node();
				this.child_nodes_.add(node);

				node.actions_ = new ArrayList<>(this.actions_);
				node.actions_.add(action);
			}
		}

		// どのノードを評価するか選択する
		Node nextChildNode() {
			for (var child_node : this.child_nodes_) {
				if (child_node.n_ == 0) {
					// DUMPL(child_node.actions_.size());
					return child_node;
				}
			}
			double t = 0;
			for (final var child_node : this.child_nodes_) {
				t += child_node.n_;
			}
			double best_value = -1;
			int best_action_index = -1;
			assert (!this.child_nodes_.isEmpty());
			for (int i = 0; i < this.child_nodes_.size(); i++) {
				final var child_node = this.child_nodes_.get(i);
				double ucb1_value = child_node.w_ / child_node.n_ + (double) C * sqrt(2. * log(t) / child_node.n_);
				if (ucb1_value > best_value) {
					best_action_index = i;
					best_value = ucb1_value;
				}
			}
			assert (best_value > -1);
			return this.child_nodes_.get(best_action_index);
		}
	};

	// 制限時間(ms)を指定してMCTSで行動を決定する
	int mctsActionWithTimeThreshold(TimeKeeper time_keeper) {
		Node root_node = new Node();
		root_node.expand();
		for (int cnt = 0;; cnt++) {
			if (time_keeper.isTimeOver()) {
				// DUMPL(cnt);
				log_add(time_keeper, global_state, cnt);
				break;
			}
			root_node.evaluate();
		}

		int best_action_searched_number = -1;
		int best_action_index = -1;
		for (int i = 0; i < legal_actions.length; i++) {
			int n = (int) root_node.child_nodes_.get(i).n_;
			// DUMPL(i);
			// DUMPL(n);
			if (n > best_action_searched_number) {
				best_action_index = i;
				best_action_searched_number = n;
			}
		}
		return legal_actions[best_action_index];
	}

	int main() {
		// DEBUG("main.cpp");
		for (int t = 0; t < END_TURN; t++) {
			nexts[t] = sc.nextInt();
		}
		global_state = new State();
		int[] pts = new int[END_TURN];
		int[] actions = new int[END_TURN];
		TimeKeeper time_keeper = new TimeKeeper(1900, END_TURN);
		for (int t = 0; t < END_TURN; t++) {
			time_keeper.setTurn(t);
			pts[t] = sc.nextInt();
			var pt = pts[t];
			global_state.update(pt);
			// DUMPL(t);

			// int action = randomAction(montecarlo::global_state);
			int action = mctsActionWithTimeThreshold(time_keeper);
			actions[t] = action;
			System.out.println(ACTION_CHARS.charAt(action));
			System.out.flush();

			if (t < 13) {
				// global_state.printTrueScores();
				// DUMPL(ACTION_CHARS[action]);
			}

			global_state.advance(actions[t]);
			// System.out.println(ACTION_CHARS[0]);
		}
		// DEBUG("main end");
		// global_state.printTrueScores();
		// System.err.println(global_state.toString());
		{
			// State state{};
			// for (int t = 0; t < actions.size(); t++)
			// {

			// state.update(pts[t]);
			// state.advance(actions[t]);
			// }
			// System.err.println("start state");
			// state.printTrueScores();

			// System.err.println(state.toString());

			// State state{};
			// System.err.println("start state");
			// for (int t = 0; t < 1; t++)
			// {

			// state.update(pts[t]);
			// System.err.println(state.toString());
			// // System.err.println(actions[t] << "\t" << (ACTION_CHARS[actions[t]]));

			// // state.advance(actions[t]);
			// state.advance(L);
			// state.printTrueScores();
			// }
		}
		return 0;
	}

	static List<String> log_list = new ArrayList<>();

	static void log_add(TimeKeeper time_keeper, State base_state, int simulate_cnt) {
		long now = System.currentTimeMillis();
		log_list.add(
				"time=" + (now - time_keeper.start_time_) + " turn=" + base_state.t_ + " simulate_cnt=" + simulate_cnt);
	}

	public static void main(String[] args) {
		Main main = new Main();
		try {
			main.main();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for (String s : log_list)
//			System.err.println(s);
	}
}

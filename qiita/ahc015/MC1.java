import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random(0);

	static class PairInt {
		int first;
		int second;

		PairInt(int f, int s) {
			this.first = f;
			this.second = s;
		}
	}
// 毎回乱数を使う原始モンテカルロ法

// 定数
	int H = 10; // 箱の縦方向の大きさ
	int W = 10; // 箱の横方向の大きさ
	int END_TURN = 100; // ゲーム終了ターン
	final int F = 0; // 前方向に傾ける
	final int B = 1; // 後ろ方向に傾ける
	final int L = 2; // 左向に傾ける
	final int R = 3; // 右方向に傾ける
// 出力に使う文字
	String ACTION_CHARS = "FBLR";

	int mt() {
		return rand.nextInt(Integer.MAX_VALUE);
	}

	int[] future_candies = new int[END_TURN]; // 各ターンで追加されるキャンディの種類

// 可能な行動一覧
	int[] legal_actions = { F, B, L, R };

// 時間を管理するクラス（ゲームで学ぶ探索アルゴリズム実践入門のサンプルとは管理方法が異なる）
	class TimeKeeper {
		long start_time_;
		long before_time_;
		long time_threshold_;
		long end_turn_;
		long turn_;

		// ターン制の問題で全ターン含めての制限時間（ミリ秒）と最大ターン数を指定し、
		// インスタンスをつくる。
		TimeKeeper(final long time_threshold, final long end_turn) {
			start_time_ = System.currentTimeMillis();
			time_threshold_ = time_threshold;
			end_turn_ = end_turn;
			turn_ = 0;
			before_time_ = start_time_;
		}

		// ターンとターン開始時間を更新する
		void setTurn(final int t) {
			turn_ = t;
			this.before_time_ = System.currentTimeMillis();
		}

		// 各ターンに割り振られた制限時間を超過したか判定する。
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

	class State {
		// 箱に追加されたキャンディの種類。0ならキャンディがない状態。
		int[][] board_;

		// 現在のターン
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

		// 指定したactionで1ターン進める
		void advance(final int action) {
			// FBLRの4方向でケース分けしているが、
			// 基本的には以下の処理を行う。
			// destに先行してy(or x)を傾ける方向と逆側に進め、
			// キャンディが見つかったらdestの位置にキャンディを移動させる
			// destをインクリメント(or デクリメント)して次の空きマスを探す
			switch (action) {
			case F: {
				for (int x = 0; x < W; x++) {
					int dest = 0;
					// yの進む向きは傾ける方向と逆側である点に注意
					for (int y = dest; y < H; y++) {
						if (this.board_[y][x] != 0) {
							// swapにより、dest側は0以外の値、y側は0が格納される
							swap(dest, x, y, x);
							// destをインクリメントすることで、次にスライド先にする空きマスを探す
							++dest;
						}
					}
				}
				break;
			}
			case B: {
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
			// L, R は F,B と違い、destをxについてのループにする点に注意
			// 余談: フォーマッタがこの部分のインデントをキモくしててつらい、、
			case L: {
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
			++this.t_; // ターンを進めるのを忘れずに(実装方針次第ではここで管理しなくてもよい)
		}

		// ランダムに盤面を更新する
		void randomUpdate() {
			int reverse_t = END_TURN - this.t_;
			int p = 1;
			if (reverse_t != 0) {
				p = mt() % reverse_t + 1;
			}
			update(p);
		}

		// 指定したパラメータで盤面を更新する
		void update(int pt) {
			for (int i = 0; i < H * W; i++) {
				int x = i % W;
				int y = i / W;
				if (this.board_[y][x] == 0) {
					--pt;
					if (pt == 0) {
						this.board_[y][x] = future_candies[this.t_];
						return;
					}
				}
			}
		}

		// 座標y,xを基準に連結成分の大きさを調べる。調査済みの座標はchekedをtrueにする。
		int getGroupSize(int y, int x, boolean[][] checked) {
			// 右、左、下、上への移動方向のx成分。BFSに利用するので順序はどうでもいい
			int[] dx = { 1, -1, 0, 0 };
			// 右、左、下、上への移動方向のy成分
			int[] dy = { 0, 0, 1, -1 };
			int candy = this.board_[y][x];
			checked[y][x] = true;

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
			return cnt;
		}

		// スコアを取得する
		double getScore() {
			double score = 0;
			boolean[][] checked = new boolean[H][W];
			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					if (this.board_[y][x] != 0 && checked[y][x] == false) {
						int group_size = getGroupSize(y, x, checked);
						score += group_size * group_size;
					}
				}
			}
			// 問題文通りのスコアなら分母があるが、分母はどんな操作を指定しても固定なので計算不要
			return score;
		}
	}

// モンテカルロ法に関連する名前空間

// ランダムに行動を決定する
	int play1turn(final State state) {
		return legal_actions[mt() % (legal_actions.length)];
	}

// ランダムプレイアウトをして勝敗スコアを計算する
	double playout(State state) {
		state.advance(play1turn(state));
		int before_turn = state.t_;
		while (!state.isDone()) {
			state.randomUpdate();
			state.advance(play1turn(state));
		}
		return state.getScore();
	}

// 制限時間(ms)を指定して行動を決定する
	int primitiveMontecalro(TimeKeeper time_keeper, final State base_state) {
		double[] w = new double[legal_actions.length];
		for (int simulate_cnt = 0;; simulate_cnt++) {
			if (time_keeper.isTimeOver()) {
				log_add(time_keeper, base_state, simulate_cnt);
				break;
			}
			for (int d = 0; d < legal_actions.length; d++) {
				// 現在の状況に戻す。
				// java state=base_state;では参照となる。コピーコンストラクタでコピーを作る。
				var state = new State(base_state);
				state.advance(d);
				state.randomUpdate();
				if (state.isDone()) {
					w[d] += state.getScore();
				} else {
					w[d] += playout(state);
				}
			}
		}
		int ret = -1;
		double best = -1;
		for (int d = 0; d < legal_actions.length; d++) {
			if (w[d] > best) {
				ret = d;
				best = w[d];
			}
		}
		return ret;
	}

	int main() {
		// END_TURN分のキャンディの種類を入力から受け取る
		for (int t = 0; t < END_TURN; t++) {
			future_candies[t] = sc.nextInt();
		}

		// メインループで使用するstateを初期化する
		State state = new State();

		// 制限時間を1900ミリ秒で設定する。（2秒ちょうどだとオーバーヘッドで超過するため）
		TimeKeeper time_keeper = new TimeKeeper(1900, END_TURN);

		// 処理のメインループ
		for (int t = 0; t < END_TURN; t++) {
			// ターンの開始をtime_keeperに記録する
			time_keeper.setTurn(t);
			// 今回追加されるキャンディの位置を入力から受け取る
			int pt;
			pt = sc.nextInt();
			// 入力から受け取った位置にキャンディを追加する
			state.update(pt);
			// 今回の操作を考える
			int action = primitiveMontecalro(time_keeper, state);
			// 今回の操作を出力する
			System.out.println(ACTION_CHARS.charAt(action));
			System.out.flush();
			// 今回の操作によってstateを更新する
			state.advance(action);
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

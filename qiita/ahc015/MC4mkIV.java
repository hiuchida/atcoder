import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random(0);

	int pos(int y, int x) {
		return pos_tbl[y][x];
//		return y * W + x;
	}

	int gety(int pos) {
		return gety_tbl[pos];
//		return pos / W;
	}

	int getx(int pos) {
		return getx_tbl[pos];
//		return pos % W;
	}

//	static class PairInt {
//		int first;
//		int second;
//
//		PairInt(int f, int s) {
//			this.first = f;
//			this.second = s;
//		}
//	}

	static class MyDeque {
		int[] ary;
		int head;
		int tail;

		MyDeque() {
			this(100, -1);
		}

		MyDeque(int n, int i) {
			n++;
			n += n % 2 == 0 ? 1 : 0;
			if (i < 0)
				i = n / 2;
			ary = new int[n];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}

		boolean isEmpty() {
			return size() == 0;
		}

		int size() {
			int t = tail;
			if (head > t)
				t += ary.length;
			return t - head;
		}

		int get(int i) {
			i += head;
			if (i >= ary.length)
				i -= ary.length;
			return ary[i];
		}

		private void grow() {
//			System.err.println("grow");
			int s0 = ary.length - 1;
			int[] tmp = new int[s0 * 2 + 1];
			int s1 = ary.length / 2;
			int s2 = ary.length - head;
			int s3 = s1 + s2;
			int s4 = tail;
			Arrays.fill(tmp, 0, tmp.length, -1);
			System.arraycopy(ary, head, tmp, s1, s2);
			System.arraycopy(ary, 0, tmp, s3, s4);
			ary = tmp;
			head = s1;
			tail = head + s0;
		}

		void addFirst(int x) {
			if (size() == ary.length - 1)
				grow();
			head--;
			if (head < 0)
				head += ary.length;
			ary[head] = x;
		}

		void addLast(int x) {
			if (size() == ary.length - 1)
				grow();
			ary[tail] = x;
			tail++;
			if (tail >= ary.length)
				tail -= ary.length;
		}

		int removeFirst() {
			if (size() == 0)
				return -1;
			int x = ary[head];
			ary[head] = -1;
			head++;
			if (head >= ary.length)
				head -= ary.length;
			return x;
		}

		int removeLast() {
			if (size() == 0)
				return -1;
			tail--;
			if (tail < 0)
				tail += ary.length;
			int x = ary[tail];
			ary[tail] = -1;
			return x;
		}

		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
// 問題特性に応じた工夫を加えたモンテカルロ法のサンプルコード

// 定数
	final int H = 10; // 箱の縦方向の大きさ
	final int W = 10; // 箱の横方向の大きさ
	final int SIZE = H * W;
	final int END_TURN = 100; // ゲーム終了ターン
	final int F = 0; // 前方向に傾ける
	final int B = 1; // 後ろ方向に傾ける
	final int L = 2; // 左向に傾ける
	final int R = 3; // 右方向に傾ける
// 1ターン1方向あたりの最大シミュレーション回数
	final int SIMULATION_MAX = 14000;
// 出力に使う文字
	final String ACTION_CHARS = "FBLR";

	int mt() {
		return rand.nextInt(Integer.MAX_VALUE);
	}

	int[] future_candies = new int[END_TURN]; // 各ターンで追加されるキャンディの種類
// 探索で利用するキャンディの位置候補
// p_it[何回目の探索か][ターン] = キャンディの位置
	int[][] p_it = new int[SIMULATION_MAX][END_TURN];

// ルールで決めた行動リスト
	int[] rule_actions = new int[END_TURN];

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

	// 以下の変数は、シングルスレッド実行環境で競合しないので、すべてのStateインスタンス内で使いまわす
	int[] visitedId = new int[SIZE]; // 訪問マークとしてユニークなIDを上書きするため、getScoreの度に初期化しない
	int currentId = 0; // 毎回異なるID
	MyDeque queue = new MyDeque(SIZE, -1); // getScoreの度に空となるので、インスタンスを使いまわす

	class State {
		// 箱に追加されたキャンディの種類。0ならキャンディがない状態。
		int[] board_; // 高速化のため1次元配列に変更

		// 現在のターン
		int t_ = 0;

		State() {
			this.board_ = new int[SIZE];
		}

		State(State that) {
			board_ = Arrays.copyOf(that.board_, SIZE);
			t_ = that.t_;
		}

		// ゲームが終了したか判定する
		boolean isDone() {
			return this.t_ >= END_TURN;
		}

		void swap(int pos1, int pos2) {
			int tmp = board_[pos1];
			board_[pos1] = board_[pos2];
			board_[pos2] = tmp;
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
					int y0 = 0;
					int dest = pos(y0, x);
					// yの進む向きは傾ける方向と逆側である点に注意
					for (int y = y0; y < H; y++) {
						int pos = pos(y, x);
						if (this.board_[pos] != 0) {
							// swapにより、dest側は0以外の値、y側は0が格納される
							swap(dest, pos);
							// destをインクリメントすることで、次にスライド先にする空きマスを探す
							dest += W;
						}
					}
				}
				break;
			}
			case B: {
				for (int x = 0; x < W; x++) {
					int y0 = H - 1;
					int dest = pos(y0, x);

					for (int y = y0; y >= 0; y--) {
						int pos = pos(y, x);
						if (this.board_[pos] != 0) {
							swap(dest, pos);
							dest -= W;
						}
					}
				}
				break;
			}
			// L, R は F,B と違い、destをxについてのループにする点に注意
			// 余談: フォーマッタがこの部分のインデントをキモくしててつらい、、
			case L: {
				for (int y = 0; y < H; y++) {
					int x0 = 0;
					int dest = pos(y, x0);
					for (int x = x0; x < W; x++) {
						int pos = pos(y, x);
						if (this.board_[pos] != 0) {
							swap(dest, pos);
							dest++;
						}
					}
				}
				break;
			}
			case R: {
				for (int y = 0; y < H; y++) {
					int x0 = W - 1;
					int dest = pos(y, x0);
					for (int x = x0; x >= 0; x--) {
						int pos = pos(y, x);
						if (this.board_[pos] != 0) {
							swap(dest, pos);
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
		void randomUpdate(int simulate_cnt) {
			if (this.t_ < END_TURN) { // java Index 100 out of bounds for length 100を避ける
				update(p_it[simulate_cnt][this.t_]);
			}
		}

		// 指定したパラメータで盤面を更新する
		void update(int pt) {
			for (int i = 0; i < SIZE; i++) {
				if (this.board_[i] == 0) {
					--pt;
					if (pt == 0) {
						this.board_[i] = future_candies[this.t_];
						return;
					}
				}
			}
		}

		// 座標y,xを基準に連結成分の大きさを調べる。調査済みの座標はchekedをtrueにする。
		int getGroupSize(int startPos) {
			// 右、左、下、上への移動方向のx成分。BFSに利用するので順序はどうでもいい
			int[] dx = { 1, -1, 0, 0 };
			// 右、左、下、上への移動方向のy成分
			int[] dy = { 0, 0, 1, -1 };
			int candy = this.board_[startPos];
			visitedId[startPos] = currentId; // 訪問マークとして現在のIDを書き込む

			queue.addLast(startPos);
			int cnt = 0;
			while (!queue.isEmpty()) {
				++cnt;
				int pi = queue.removeFirst();
				int now_y = gety(pi);
				int now_x = getx(pi);

				for (int di = 0; di < 4; di++) {
					int ty = now_y + dy[di];
					int tx = now_x + dx[di];
					if (0 <= ty && ty < H && 0 <= tx && tx < W) {
						int nextPos = pos(ty, tx);
						if (visitedId[nextPos] != currentId && this.board_[nextPos] == candy) {
							visitedId[nextPos] = currentId; // 訪問マークとして現在のIDを書き込む
							queue.addLast(nextPos);
						}
					}
				}
			}
			return cnt;
		}

		// スコアを取得する
		double getScore() {
			double score = 0;
			currentId++; // 毎回異なるIDを使う
			for (int i = 0; i < SIZE; i++) {
				if (this.board_[i] != 0 && visitedId[i] != currentId) {
					int group_size = getGroupSize(i);
					score += group_size * group_size;
				}
			}
			// 問題文通りのスコアなら分母があるが、分母はどんな操作を指定しても固定なので計算不要
			return score;
		}
	}

// モンテカルロ法に関連する名前空間

// 1ターン分の行動を決定する
	int play1turn(final State state) {
		return rule_actions[state.t_];
	}

// ランダムプレイアウトをして勝敗スコアを計算する
	double playout(State state, int simulate_cnt) {
		state.advance(play1turn(state));
		int before_turn = state.t_;
		while (!state.isDone()) {
			state.randomUpdate(simulate_cnt);
			state.advance(play1turn(state));
		}
		return state.getScore();
	}

// 制限時間(ms)を指定して行動を決定する
	int primitiveMontecalro(TimeKeeper time_keeper, final State base_state) {
		double[] w = new double[legal_actions.length];
		for (int simulate_cnt = 0; simulate_cnt < SIMULATION_MAX; simulate_cnt++) {
			if (time_keeper.isTimeOver()) {
				log_add(time_keeper, base_state, simulate_cnt);
				break;
			}
			for (int d = 0; d < 4; d++) {
				// 現在の状況に戻す。
				// java state=base_state;では参照となる。コピーコンストラクタでコピーを作る。
				var state = new State(base_state);
				state.advance(d);
				state.randomUpdate(simulate_cnt);
				if (state.isDone()) {
					w[d] += state.getScore();
				} else {
					w[d] += playout(state, simulate_cnt);
				}
			}
		}
		int ret = -1;
		double best = -1;
		for (int d = 0; d < 4; d++) {
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

		// END_TURN分のキャンディ位置の乱数をSIMULATION_MAX個ずつ生成する
		for (int i = 0; i < SIMULATION_MAX; i++)
			for (int t = 0; t < END_TURN; t++) {
				int reverse_t = END_TURN - t;
				int p = 1;
				if (reverse_t != 0) {
					p = mt() % reverse_t + 1;
				}
				p_it[i][t] = p;
			}

		// rule_action_samples[現在のターンのキャンディ][次ターンのキャンディ]=操作
		// となるようにルールを設計
		int[][] rule_action_samples = { { F, B, B }, { F, L, R }, { F, L, R }, };

		// 上で設計したルールに従い、全ターンのルールベース解を事前計算
		for (int t = 0; t < END_TURN - 1; t++) {
			int now = future_candies[t];
			int next = future_candies[t + 1];
			rule_actions[t] = rule_action_samples[now - 1][next - 1];
		}

		// 最後はなんでもいい（問題文にも記載あり）
		rule_actions[END_TURN - 1] = F;

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

	int[][] pos_tbl = new int[H][W];
	int[] gety_tbl = new int[SIZE];
	int[] getx_tbl = new int[SIZE];

	void init() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				pos_tbl[i][j] = i * W + j;
				gety_tbl[i * W + j] = i;
				getx_tbl[i * W + j] = j;
			}
		}
	}

	static List<String> log_list = new ArrayList<>();
	static int simulate_sum;

	static void log_add(TimeKeeper time_keeper, State base_state, int simulate_cnt) {
		long now = System.currentTimeMillis();
		simulate_sum += simulate_cnt;
		log_list.add("time=" + (now - time_keeper.start_time_) + " turn=" + base_state.t_ + " simulate_cnt="
				+ simulate_cnt + " simulate_sum=" + simulate_sum);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		try {
			main.main();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for (String s : log_list)
//			System.err.println(s);
	}
}

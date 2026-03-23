import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	int END_TURN = 100; // ゲーム終了ターン

	int[] nexts = new int[END_TURN];
	int F = 0;
	int B = 1;
	int L = 2;
	int R = 3;
	String ACTION_CHARS = "FBLR";

// 可能な行動一覧
	int[] rule_actions = new int[END_TURN];

	int main() {
		for (int t = 0; t < END_TURN; t++) {
			nexts[t] = sc.nextInt();
		}

		// イチゴは下にスイカは右上に、カボチャは左上に集める
		int[][] rule_action_samples = { { F, B, B }, { F, L, R }, { F, L, R }, };
		for (int t = 0; t < END_TURN - 1; t++) {
			int now = nexts[t];
			int next = nexts[t + 1];
			rule_actions[t] = rule_action_samples[now - 1][next - 1];
		}
		// 100回目はなんでもいい
		rule_actions[END_TURN - 1] = F;
		// 毎ターンの入力と出力を繰り返す
		for (int t = 0; t < END_TURN; t++) {
			int pt;
			pt = sc.nextInt();
			int action = rule_actions[t];
			System.out.println(ACTION_CHARS.charAt(action));
		}
		return 0;
	}

	public static void main(String[] args) {
		Main main = new Main();
		try {
			main.main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

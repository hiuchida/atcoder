import java.util.*;

public class FullAuto2048 {
    // 方向の定義
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int BEAM_WIDTH = 50;  // 探索幅
    private static final int SEARCH_DEPTH = 5; // 探索深さ
    private static final Random RANDOM = new Random();

    public static void main0(String[] args) {
        // テスト用の初期盤面
        int[][] board = {
            {0, 0, 2, 2},
            {0, 4, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        int bestMove = findBestMove(board);
        String[] moveNames = {"UP", "DOWN", "LEFT", "RIGHT"};
        System.out.println("AIが推奨する次の一手: " + moveNames[bestMove]);
    }

    public static void main(String[] args) {
        int[][] gameBoard = new int[4][4];
        addRandomTile(gameBoard);
        addRandomTile(gameBoard);

        System.out.println("ゲームスタート！");
        
        while (!isGameOver(gameBoard)) {
            printBoard(gameBoard);
            
            // AIが次の一手を決定
            int bestMove = findBestMove(gameBoard);
            
            // 実際に移動（動かせない手を選んだ場合はループを抜ける安全策）
            int[][] next = simulateMove(gameBoard, bestMove);
            if (Arrays.deepEquals(gameBoard, next)) {
                // 万が一、AIが動かせない手を選択し続けたら終了
                break; 
            }
            
            gameBoard = next;
            addRandomTile(gameBoard);
            
            try { Thread.sleep(100); } catch (InterruptedException e) {} // 動きを見やすく
        }

        printBoard(gameBoard);
        System.out.println("ゲームオーバー！");
        System.out.println("最終最大タイル: " + getMaxTile(gameBoard));
    }

    // --- ビームサーチ本体 ---
    public static int findBestMove(int[][] initialBoard) {
        // 現在の有望な候補リスト
        List<BoardState> currentBeam = new ArrayList<>();
        currentBeam.add(new BoardState(initialBoard, new ArrayList<>()));

        for (int d = 0; d < SEARCH_DEPTH; d++) {
            List<BoardState> nextCandidates = new ArrayList<>();

            for (BoardState state : currentBeam) {
                // 4方向に動かしてみる
                for (int move = 0; move < 4; move++) {
                    int[][] nextBoard = simulateMove(state.board, move);
                    if (!isEqual(state.board, nextBoard)) {
                        // 本来はここでランダムなタイル出現も考慮するが、
                        // 簡易版ではそのまま次へ
                        List<Integer> nextMoves = new ArrayList<>(state.moves);
                        nextMoves.add(move);
                        nextCandidates.add(new BoardState(nextBoard, nextMoves));
                    }
                }
            }

            // 全候補を評価スコアでソートし、上位 BEAM_WIDTH 個だけに絞る
            Collections.sort(nextCandidates);
            int endIdx = Math.min(nextCandidates.size(), BEAM_WIDTH);
            currentBeam = new ArrayList<>(nextCandidates.subList(0, endIdx));
        }

        // 最終的に最もスコアが高かったルートの「最初の一手」を返す
        return currentBeam.isEmpty() ? 0 : currentBeam.get(0).moves.get(0);
    }

    // --- 盤面移動ロジック ---
    private static int[][] simulateMove(int[][] board, int dir) {
        int[][] res = copyBoard(board);
        // 回転させて「左移動」に統一
        // 0:上, 1:下, 2:左, 3:右 (例としての定義)
        if (dir == UP) res = rotateLeft(res);
        else if (dir == DOWN) res = rotateRight(res);
        else if (dir == RIGHT) res = rotate180(res);

        // 全行に対して「左移動」を適用
        for (int i = 0; i < 4; i++) {
            res[i] = moveLineLeft(res[i]);
        }

        // 回転を戻す
        if (dir == UP) res = rotateRight(res);
        else if (dir == DOWN) res = rotateLeft(res);
        else if (dir == RIGHT) res = rotate180(res);
        return res;
    }

    // 1行分を左に動かす基幹ロジック
    public static int[] moveLineLeft(int[] line) {
        int[] nextLine = new int[4];
        int targetPos = 0;

        // 1. まず0以外を左に詰め込む
        for (int i = 0; i < 4; i++) {
            if (line[i] != 0) {
                nextLine[targetPos++] = line[i];
            }
        }

        // 2. 左から順に合体判定
        for (int i = 0; i < 3; i++) {
            if (nextLine[i] != 0 && nextLine[i] == nextLine[i + 1]) {
                nextLine[i] *= 2;      // 合体！
                nextLine[i + 1] = 0;   // 合体された方は消える
                // 合体が発生したら、再度左詰めが必要（後述）
            }
        }

        // 3. 合体後の隙間をもう一度詰める
        int[] finalLine = new int[4];
        targetPos = 0;
        for (int i = 0; i < 4; i++) {
            if (nextLine[i] != 0) {
                finalLine[targetPos++] = nextLine[i];
            }
        }
        return finalLine;
    }

    // --- ユーティリティ ---
    private static int[][] rotateLeft(int[][] b) {
        int[][] r = new int[4][4];
        for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) r[3 - j][i] = b[i][j];
        return r;
    }

    private static int[][] rotateRight(int[][] b) {
        int[][] r = new int[4][4];
        for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) r[j][3 - i] = b[i][j];
        return r;
    }

    private static int[][] rotate180(int[][] b) {
        return rotateLeft(rotateLeft(b));
    }

    // --- システム系 ---
    private static void addRandomTile(int[][] board) {
        List<int[]> empty = new ArrayList<>();
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++) if (board[r][c] == 0) empty.add(new int[]{r, c});
        if (!empty.isEmpty()) {
            int[] cell = empty.get(RANDOM.nextInt(empty.size()));
            board[cell[0]][cell[1]] = (RANDOM.nextDouble() < 0.9) ? 2 : 4;
        }
    }

    private static boolean isGameOver(int[][] board) {
        for (int m = 0; m < 4; m++) {
            if (!Arrays.deepEquals(board, simulateMove(board, m))) return false;
        }
        return true;
    }

    private static int getMaxTile(int[][] board) {
        int max = 0;
        for (int[] r : board) for (int c : r) max = Math.max(max, c);
        return max;
    }

    private static int[][] copyBoard(int[][] b) {
        int[][] res = new int[4][4];
        for (int i = 0; i < 4; i++) System.arraycopy(b[i], 0, res[i], 0, 4);
        return res;
    }

    private static boolean isEqual(int[][] a, int[][] b) {
        return Arrays.deepEquals(a, b);
    }

    private static void printBoard(int[][] b) {
        System.out.println("-----------------");
        for (int[] r : b) {
            for (int c : r) System.out.printf("%4d ", c);
            System.out.println();
        }
    }

    static class BoardState implements Comparable<BoardState> {
        int[][] board;
        List<Integer> moves;
        double score;

        BoardState(int[][] b, List<Integer> m) {
            this.board = b;
            this.moves = m;
            this.score = evaluate();
        }

        double evaluate() {
            double s = 0;
            int empty = 0;
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (board[r][c] == 0) empty++;
                    // シンプルな重み付け（左上に大きい数字を集める）
                    s += board[r][c] * Math.pow(0.5, r + c);
                }
            }
            return s + (empty * 10);
        }

        @Override
        public int compareTo(BoardState o) {
            return Double.compare(o.score, this.score);
        }
    }
}

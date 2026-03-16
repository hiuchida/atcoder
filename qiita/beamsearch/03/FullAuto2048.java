import java.util.*;

public class FullAuto2048 {
    private static final int BEAM_WIDTH = 50;
    private static final int SEARCH_DEPTH = 6;
    private static final Random RANDOM = new Random(0); //!!
    static String[] command = {"UP", "DOWN", "LEFT", "RIGHT",}; //!!

    public static void main(String[] args) {
        int[][] gameBoard = new int[4][4];
        addRandomTile(gameBoard);
        addRandomTile(gameBoard);

        System.out.println("ゲームスタート！");
        
        String cmd=""; //!!
        for (int t=0; true; t++) { //!!
            printBoard(t, gameBoard, cmd); //!!
            if (isGameOver(gameBoard)) break; //!!
            
            // AIが次の一手を決定
            int bestMove = findBestMove(gameBoard);
            cmd=command[bestMove]; //!!
            
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

//        printBoard(gameBoard, cmd); //!!
        System.out.println("ゲームオーバー！");
        System.out.println("最終最大タイル: " + getMaxTile(gameBoard));
    }

    // --- ビームサーチ ---
    private static int findBestMove(int[][] currentBoard) {
        List<BoardState> beam = new ArrayList<>();
        beam.add(new BoardState(currentBoard, new ArrayList<>()));

        for (int d = 0; d < SEARCH_DEPTH; d++) {
            List<BoardState> nextCandidates = new ArrayList<>();
            for (BoardState state : beam) {
                for (int m = 0; m < 4; m++) {
                    int[][] nextB = simulateMove(state.board, m);
                    if (!Arrays.deepEquals(state.board, nextB)) {
                        List<Integer> nextMoves = new ArrayList<>(state.moves);
                        nextMoves.add(m);
                        nextCandidates.add(new BoardState(nextB, nextMoves));
                    }
                }
            }
            if (nextCandidates.isEmpty()) break;
            Collections.sort(nextCandidates);
            int end = Math.min(nextCandidates.size(), BEAM_WIDTH);
            beam = new ArrayList<>(nextCandidates.subList(0, end));
        }
        return beam.isEmpty() ? 0 : beam.get(0).moves.get(0);
    }

    // --- 移動ロジック (回転を利用) ---
    private static int[][] simulateMove(int[][] board, int dir) {
        int[][] res = copyBoard(board);
        if (dir == 0) rotateLeftInPlace(res);       // UP
        else if (dir == 1) rotateRightInPlace(res); // DOWN
        else if (dir == 3) rotate180InPlace(res);   // RIGHT

        for (int i = 0; i < 4; i++) res[i] = moveLineLeft(res[i]);

        if (dir == 0) rotateRightInPlace(res);
        else if (dir == 1) rotateLeftInPlace(res);
        else if (dir == 3) rotate180InPlace(res);
        return res;
    }

    private static int[] moveLineLeft(int[] line) {
        int[] next = new int[4];
        int pos = 0;
        for (int x : line) if (x != 0) next[pos++] = x;
        for (int i = 0; i < 3; i++) {
            if (next[i] != 0 && next[i] == next[i + 1]) {
                next[i] *= 2;
                next[i + 1] = 0;
            }
        }
        int[] finalLine = new int[4];
        pos = 0;
        for (int x : next) if (x != 0) finalLine[pos++] = x;
        return finalLine;
    }

    // --- インプレース回転 ---
    private static void rotateLeftInPlace(int[][] b) {
        transpose(b);
        reverseRows(b); // 反時計回りの簡易的な組み合わせ
    }
    
    private static void rotateRightInPlace(int[][] b) {
        // 時計回り: 転置してから行を反転
        transpose(b);
        for(int i=0; i<4; i++) {
            for(int j=0; j<2; j++) {
                int t = b[i][j]; b[i][j] = b[i][3-j]; b[i][3-j] = t;
            }
        }
    }

    private static void rotate180InPlace(int[][] b) {
        for (int i = 0; i < 8; i++) {
            int r1 = i / 4, c1 = i % 4, r2 = (15 - i) / 4, c2 = (15 - i) % 4;
            int t = b[r1][c1]; b[r1][c1] = b[r2][c2]; b[r2][c2] = t;
        }
    }

    private static void transpose(int[][] b) {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int t = b[i][j]; b[i][j] = b[j][i]; b[j][i] = t;
            }
        }
    }

    private static void reverseRows(int[][] b) {
        // 下に回転させるイメージの反転
        for (int i = 0; i < 2; i++) {
            int[] t = b[i]; b[i] = b[3 - i]; b[3 - i] = t;
        }
    }

    // --- システム系 ---
    private static void addRandomTile(int[][] board) {
        List<int[]> empty = new ArrayList<>();
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++) if (board[r][c] == 0) empty.add(new int[]{r, c});
        if (!empty.isEmpty()) {
            int[] cell = empty.get(RANDOM.nextInt(empty.size()));
            board[cell[0]][cell[1]] = (RANDOM.nextDouble() < 0.9) ? -2 : -4; //!!
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
        int[][] r = new int[4][4];
        for (int i = 0; i < 4; i++) System.arraycopy(b[i], 0, r[i], 0, 4);
        return r;
    }

    private static void printBoard(int t, int[][] b, String cmd) { //!!
        System.out.println("----------------- "+t+" "+cmd); //!!
        for (int[] r : b) {
            for (int c : r) {
                boolean bMark=false; //!!
            	if (c<0) { //!!
            		bMark=true; //!!
            		c=-c; //!!
            	} //!!
            	System.out.printf("%4d%c", c, bMark ? '*' : ' '); //!!
            }
            System.out.println();
        }
        for (int i=0; i<b.length; i++) { //!!
        	for (int j=0; j<b[0].length; j++) { //!!
        		if (b[i][j]<0) b[i][j]*=-1; //!!
        	} //!!
        } //!!
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
            // 重み付けマップ: 左上に大きい数字を誘導
            double[][] weights = {
                {100, 80, 60, 40},
                {10,  20, 30, 40},
                {10,  8,  6,  4},
                {1,   2,  3,  4}
            };
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (board[r][c] == 0) empty++;
                    s += board[r][c] * weights[r][c];
                }
            }
            return s + (empty * 50);
        }

        @Override
        public int compareTo(BoardState o) {
            return Double.compare(o.score, this.score);
        }
    }
}

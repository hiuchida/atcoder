# beamsearch

AIチャットが生成したビームサーチのサンプル。

## 00 初期バージョン

AIチャットが回答した断片的なソースを01のソースにマージしたもの。

評価関数
if (board[r][c] == 0) empty++;
s += board[r][c] * Math.pow(0.5, r + c);
return s + (empty * 10);

回転関数が非効率
int[][] rotateLeft(int[][] b)
int[][] rotateRight(int[][] b)
int[][] rotate180(int[][] b)→rotateLeft(rotateLeft(b))

## 01 未修正

AIチャットが一通り動くものを生成したもの。

評価関数
    double[][] weights = {
        {100, 80, 60, 40},
        {10,  20, 30, 40},
        {10,  8,  6,  4},
        {1,   2,  3,  4}
    };
    if (board[r][c] == 0) empty++;
    s += board[r][c] * weights[r][c];
    return s + (empty * 50);

回転関数の改善（インプレース回転）
void rotateLeftInPlace(int[][] b)→transpose(b); reverseRows(b);
void rotateRightInPlace(int[][] b)→transpose(b); t = b[i][j]; b[i][j] = b[i][3-j]; b[i][3-j] = t;
void rotate180InPlace(int[][] b)→t = b[r1][c1]; b[r1][c1] = b[r2][c2]; b[r2][c2] = t;
void transpose(int[][] b)→t = b[i][j]; b[i][j] = b[j][i]; b[j][i] = t;
void reverseRows(int[][] b)→t = b[i]; b[i] = b[3 - i]; b[3 - i] = t;

## 02 修正

乱数のseedを0に固定する。

addRandomTileにて-2,-4を入れる。
printBoardのときに負の数を頼りに末尾に*をつけて出力する。
負の数を正の数に戻す。

printBoardのときにcmdを出力する。

## 03 修正

02版では、addRandomTileの後にisGameOverの判定を行い、printBoardを呼び出す。
isGameOverでは空き地がなくなると動かす手段がなくなるのでゲームオーバー判定するが、
本来は空き地がなくても合体できればゲームオーバーとならないため、-2を追加したことで、
2との合体が出来ずに合体できるはずなのに誤判定される。
addRandomTile、printBoard、isGameOverの順番に変える。
このため、ループ後のprintBoardは不要となる。

whileをfor文に変更し、printBoardにてターン数を出力する。


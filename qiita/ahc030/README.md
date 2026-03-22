# ahc030ソース置き場

javaクラス名はすべてMainで、ファイル名とは一致しません。

## 01 コンパイルできない状態

NotebookLMで、対応するcppファイルをjavaに翻訳した直後のjavaファイル。

## 02 コンパイルを通した状態

01のjavaファイルのコンパイルエラーとなっている部分を最低限の修正を行う。

## 03 ランダムな占い

04_all_pool_random_divination.javaをちゃんと回答できるようにする。

## 04 相互情報量を最大化する占い

03_all_pool_hill_climb.javaをちゃんと回答できるようにする。

- 03_all_pool_hill_climb_cache1.java 基準ファイル
- 03_all_pool_hill_climb_cache2.java 調査ログ
- 03_all_pool_hill_climb_cache3.java pr_if_xのキャッシュ
- 03_all_pool_hill_climb_cache4.java ln_pr_if_xのキャッシュ
- 03_all_pool_hill_climb_cache5.java SMALL_VALUE以上に限定
- 03_all_pool_hill_climb_climb1.java 山登り調査用

## 05 事後確率とgiveup改良

04_all_pool_random_divination.javaと03_all_pool_hill_climb.javaのgiveupを改良する。

- 04_all_pool_random_divination_debug.java 事後確率とバイナリーエントロピー調査用

## 06 数を絞ったランダムなプール

02_random_pool.javaをちゃんと回答できるようにする。

- 04_all_pool_random_divination.java Zobrist Hashによる重複チェック
- 03_all_pool_hill_climb.java 尤度が低いものを削除

## 99 cppと同等となる移植

### 変数

- Scanner sc : java独自、わざわざ引数に追加しないため。
- long start : get_time()関数の中でstatic変数を書いているが、javaはメソッド内にstatic変数が書けないため。

### 関数

- erf0() : javaに誤差関数がないため近似関数。

以下はcppの関数をそのまま使うため。
- erf(double a)
- exp(double a)
- isinf(double a)
- log(double a)
- max(int a, int b)
- min(double a, double b)
- min(int a, int b)
- round(double a)
- shuffle(List<OilLayout> list, Xorshift rnd)
- sqrt(double a)

### クラス

- PairDouble : double first,secondを持たせる。
- PairDoubleInt : double first,int secondを持たせる。
- PairInt : int first,secondを持たせる。compare(PairInt a, PairInt b)を定義。
- PairListInt : IntList query_coordinates,int retを持たせる。2
- PairIntPairInt : int volume,PairInt pairを持たせる。3
- BoolList : ArrayList<Boolean> listをラップ。
- BoolListList : ArrayList<ArrayList<Boolean>>をラップ。
- ByteList : ArrayList<Byte> listをラップ。
- DoubleList : ArrayList<Double> listをラップ。
- IntList : ArrayList<Integer> listをラップ。
- LongList : ArrayList<Long> listをラップ。
- LongDoubleMap : HashMap<Long, Double> mapをラップ。

### 特記事項

- Xorshiftの中の>>は、符号なしで右シフトするため>>>に変更する。
- Xorshiftの中のuint64_tをlongにしても符号ありなので、符号ビットをクリアする。
- simulated_annealing()でsize_tは負の数にならないが、intにすると負の数となり追加の範囲チェックが必要なので、Integer.MAX_VALUEを足して大きい値にする。


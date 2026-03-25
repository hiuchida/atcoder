# ahc015ソース置き場

javaクラス名はすべてMainで、ファイル名とは一致しません。

- MC1.java : 問題特性を利用しない、シンプルなモンテカルロ法
- MC2.java : 問題特性を利用しない、探索部分のみに工夫を加えたモンテカルロ法
- MC3.java : 問題特性に応じたルールベース手法
- MC4.java : 問題特性に応じた工夫を加えたモンテカルロ法
- MC4mkII.java : MC4.javaの高速化
- MC4mkIII.java : MC4.javaの高速化2
- PI.java : πの近似値

### 変数

- Scanner sc : java独自、わざわざ引数に追加しないため。
- Random rand : std::mt19937 mt{0}の代替のmt()メソッドのため。

### 関数

以下はcppの関数をそのまま使うため。
- log(double a)
- sqrt(double a)

### クラス

- PairInt : int first,secondを持たせる。compare(PairInt a, PairInt b)を定義。

### 特記事項

- Stateクラス内にswap関数を追加する。
- auto state = base_state;がcppではコピーだが、javaは参照値が代入するだけなので、コピーコンストラクタで複製を作る。
- update(p_it[simulate_cnt][this.t_]);にて例外となる。cppでは範囲外のメモリを参照して動いているようだが、this.t_ \< END_TURNのチェックを追加する。
- 制限時間を1950ミリ秒でローカルPCでは問題ないように見えるが、AtcoderサーバーではTLEとなるので、1900ミリ秒に変更する。
- log_addメソッドとList\<String> log_listを追加する。各ターンの経過時間、ターン数、シミュレーション数を保管し、最後に出力する。

## MC4mkII.java

MC4.javaがcppと比べてスコアが悪いので、高速化を目指す。

- ArrayDeque\<PairInt>をArrayDeque\<Integer>に置き換え。（あまり効果なし）
- ArrayDeque\<PairInt>をMyDequeに置き換え。（あまり効果なし）
- pos(),gety(),getx()の結果をpos\_tbl,gety\_tbl,getx\_tblに事前キャッシュ化。（効果あり）

## MC4mkIII.java

- update()のgetx(),gety()を適用する。

## MC4mkIV.java

- 定数SIZE = H * Wを追加する。
- boolean\[\]\[\] checkedをint\[\] visitedIdに変更し、常に異なるcurrentIdをチェックすることでgetScoreの度の初期化コストを無くす。
- MyDeque queueを使いまわして、getScoreの度の生成コストを無くす。
- int[]\[\] board_をint[] board_に変更し、(y,x)でなくposでループする。


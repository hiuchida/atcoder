# ahc015ソース置き場

javaクラス名はすべてMainで、ファイル名とは一致しません。

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


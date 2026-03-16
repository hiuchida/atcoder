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


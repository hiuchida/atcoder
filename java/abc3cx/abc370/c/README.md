# Main0,Main1
例１の説明で、そもそも辞書順となっていないのが、何で？と思ったが、
残り時間少なくなって、adbeから4文字目を置換してadbcとすると、
acbe<adbcなので、2文字目が先に置換するということなのだろうけど。
3
acbe
acbc
bcbc

# Main
解説でN^3の方法が、N=100でも10^6で確かにすべて洗い出せばよかったのか。
100文字がすべて異なるとき100!が総当たりかと思ったが、
1からnの中から1番目の位置を探して、置換して、を繰り返せばよい。
dfsとbfsとの違いのよう。

# Main_gt_lt
問題の意味が分かったときに、Si<TiのときとSi>Tiのときで、右側からと
左側からと方向が違うことに気が付いたので、Main0はまるでカバーしていない。
if (cs != ct) {の判定でなく、先にif (cs > ct)を処理し、次にif (cs < ct)を処理する。

# Main\_retry
前回2024/12/31から2025/3/28まで時間をおいて再挑戦。
素直に問題文とおりに解く。
sとtが異なる間ループし、iを0からs.length-1までループし、
異なる文字を1つ置き換えた文字列を作成し、それをTreeSet<String> setへ追加した。
ss=set.first()が辞書順に最も小さい文字列なので、それをansへ追加する。
sをssに置き換えてループの先頭に戻る。


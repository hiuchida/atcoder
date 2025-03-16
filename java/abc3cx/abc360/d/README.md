# Main01
TreeSet<Pair> set0とTreeSet<Integer> set1に追加する。
set1からx<set0.v && set0.v<=x+2\*tとなるset0の個数をカウントする。
x+2\*tがオーバーフローし、WA20個。

# Main02
Main01を元に、x+2L*tに修正。WA17個。

# Main03
Main02を元に、ceilingをhigherに変更。WA17個。

# Main
Main03を元に、int ansをlong ansに変更。AC。

# Main01
nを読み込み、int[] aryに読み込み、ソートする。
Deque<Integer> queを用意し、queにary[i]を追加する。
idx=1として、queが空になるまでループする。
a=que.peek()を得て、a==idxの場合que.poll()して取り除きidx++する。
queサイズが2以上なら後ろから2個取り除きidx++する。
それ以外はqueから取り除く。
ループが終了したら、idx-1を出力する。
WA7個。

# Main
Main01を元に、Set<Integer> setを用意し、
setにaが存在する場合a=INT\_MAXに置き換えて、存在しない場合setにaを追加する。
（重複したものは優先して売る）


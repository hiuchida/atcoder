# Main01
TLE。
Deque<Info> queから見つかるまで取り出しているため、後ろにあると遅い。

# Main02
Main01から少しでも速くなるようにqueに入れる条件を厳しく。

# Main03
List<Info> queに変えて、ソート。
このときにTreeSet<Info>が思いついていない。

# Main
TreeSet<Info> queで優先度の高い順に並べる。
ただし、作る過程でソートキーがlong pw;のみで、
同じpwのときに順序が不定となっていた。
第2ソートキーとして座標this.y * 1000 + this.xを追加。

# Main_PriorityQueue
PriorityQueue<Info> queに書き換え。
ソートキーがpwのみに変わるが、queの外で訪問済フラグが必要となる。

# Main\_dxdy
Mainからint\[\] dx,dyを使って4方向をループする。

# Main_PriorityQueue\_dxdy
Main_PriorityQueueからint\[\] dx,dyを使って4方向をループする。

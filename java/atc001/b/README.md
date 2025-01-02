# Main
2016年11月に何となく作ったUnionFindクラス。
親はuf[i] = i;で、merge(int u, int v)の中で常にv->uの連結している。
root(int v)の中で、再帰呼び出しで、経路圧縮している。

# Main_rank
経路圧縮を止める必要はないので、さらにmergeの時にランクによる連結方向の判定を行う。
SlideShareの解説が、par[]とrank[]の2つの配列を持っているので、上記のMainでは
止めたと思うが、他のソースを見ていて、初期値-1で自分自身が親、この絶対値でランクを表現する。
つまり、-1と-1をmergeするとき片方(u<vのときu)を-2にする。また-2と-1ならば右→左に連結する。

# Main_size
経路圧縮のまま、サイズによる連結方向の判定を行う。
ランクとほぼ同じで、初期値-1で、小さいサイズを大きいサイズが引き継ぐ。
同じサイズの場合、片方(u<vのときu）を新しい親とする。
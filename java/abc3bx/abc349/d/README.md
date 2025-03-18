# Main01
lt=0,rt=1 << 60から初めて、dfs(lt, rt, l, r, 1)で範囲を絞っていった。
WA4個。TLE24個。

# Main
lt=0,rt=1L << 60から初めて、dfs(lt, rt, l, r, 1)で範囲を絞っていった。

rtがintの1を60ビットシフトしオーバーフローしていて、上限が足りていなかった。

# Main\_fix
Mainを元に修正。
List<PairL> list=new ArrayList<>();をdfs()内のローカル変数にしているが、
static変数として確定したものを追加すればよい。

# Main\_updown
左からx=l、y<=rとなるPairL(x,y)の候補を探し、最大のyとなるPairLをList<PairL> listに追加する。
search内で、vを1から2^k<=rとなるまでループし、j=l/vが割り切れる場合の、y=(j+1)\*vを計算する。
左からlistに追加するので、listのソートは不要。


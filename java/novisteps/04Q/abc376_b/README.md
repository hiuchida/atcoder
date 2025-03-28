# Main01
例題データでWAなので、提出なし。
大小比較で何となると思い、ネストしたifを書いたが、これでも足りないケースがある。

# Main02
例題データでWAなので、提出なし。
Nで循環するから、大小関係が整うまで+Nすればよいと考えたが、右回り、左回りが整理できていない。

# Main
解説を読んでfrom,to,ngの6通りを整理する。
from to ng to-from
from ng to from+n-to
to from ng from-to
to ng from to+n-from
ng from to to-from
ng to from from-to
to-fromとfrom-toは絶対値で計算可能なので、3通りの条件分岐を行う。

# Main_func
switchが2回書くことになるので、計算はメソッドにする。

# Main_swap
from<toとなるようにswapすると、3通り。
from to ng to-from
from ng to from+n-to
ng from to to-from
from ng toかそれ以外なので2通り。

3通りが2通りであまりメリットは感じないが、from<toの制約をつける。
calc()でfromとtoを交換するのtmpが必要となるので、calc0()に変えて、calc()でswapする。

Nで循環するのでnを足せばよい考えは、
from ng toの並びのときに、from2=from+nとして、from2-to=from+n-toなので、同じこと。
おそらくnを足し過ぎたか、逆回りの方が近かったなど。

# Main_zero
fromを0になるように回転させてtoとngを相対位置にすることも思いついたが、
マイナスになるのを嫌った。実際は、+nした状態で-fromし、nで余りを取れば、
1からn-1の範囲に収まるので、toとngの大小関係で右方向か左方向か決まる。

解説のコードだと、-1%5==4となるはずで、-1%5==-1の場合、自分で+nして(-1+5)%5=4にする。
比較すると、こちらの方が複雑ではないので、思いついたときにこの方針で作っていれば出来ていたはず。

# Main\_retry
前回2024/12/26から2025/3/28まで時間をおいて再挑戦。
N=100、Q=100程度なので、右回りと左回りに1回づつ回転させ、もう一方の手になったらINT\_MAXを返すようにした。
既に同じ位置で0回の場合と、右回りと左回りの片方がc回でもう一方がINT\_MAXとなりminを取る。

iを0からq-1までループし、'L'ならmy=l,you=r、'R'ならmy=r,you=lとしてcount()をd=1と-1の2通りで呼び出す。
戻り値をd,eで受け取り、最小値をfとして、ansに加える。
lまたはrをtに移動させ、ループの先頭に戻る。

count内で、c=0とし、my!=tの間ループし、c++し、modadd(my, d)した後のmy==youのときINT\_MAXで打ち切る。
ループが終了したらcを返す。

modadd内で、1からnで循環するため、増分dを加え、マイナス時のためにnを加えて、1baseを0baseにするため-1し、
nで余りを取り、0baseを1baseにするため+1する。
test_modaddで、1からnに対する+1と-1の結果を確認する。


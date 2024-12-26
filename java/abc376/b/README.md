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

# Main01
qを読み込み、que=new MyDeque(q, -1)を用意し、que.addFirst(1)を追加する。
q回ループし、cmdを読み込む。
cmd==1の場合、xを読み込み、que.addLast(x)に追加する。
cmd==2の場合、que.removeFirst()から先頭を取り除く。
cmd==3の場合、ans=0、w=1を用意し、
iをque.size()-1から0までループし、v=que.get(i)に入れ、
v=modmul(v, w)、w=modmul(w, 10)、ans=modadd(ans, v)を計算する。
ansを出力する。
TLE4個。

# Main
Main01を元に、
10の逆元M_10=modinv(10, M)を用意する。
ループ前にw=1、val=1を用意する。
cmd==1の場合、val=modmul(val, 10)、val=modadd(val, x)、w=modmul(w, 10)を計算する。
cmd==2の場合、y=que.removeFirst()を取り出し、v=modmul(y, w)、val=modadd(val, -v)、w=modmul(w, M_10)を計算する。
cmd==3の場合、valを出力する。

MyDeque_int20250327を適用


# Main01
nを読み込み、Maze mzへ読み込む。
yを1からnまでループし、xを1からnまでループする。
cnt=0を初期化し、dを0から5までループする。
yy=y+dを計算し、yy<=nかつmap[yy][x]が黒ならばcnt++する。
yy>nならば中断し、ループdが終わりcnt>=4ならばok。
同様にxx=x+dを計算し、xx<=nかつmap[y][xx]が黒ならばcnt++する。
xx>nならば中断し、ループdが終わりcnt>=4ならばok。
ループy,xが終了したらng。
WA7個。

# Main02
Main01を元に、縦、横の他に斜めのチェックを追加する。
yy=y+d,xx=x+dとyy=y-d,xx=x+dも同様に計算し、cnt>=4ならばok。
WA4個。

# Main
Main02を元に、yy>nのような範囲外のチェックの際に、cnt=0にクリアする。

# Main\_fix
Mainを元に、4方向の判定をcheck()にまとめる。
範囲チェックは1<=yy && yy<=n && xx<=nを行う。dx=-1はないので常に1<=xx。


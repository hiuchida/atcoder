# Main01
n,mを読み込み、bok=init(n, m)を呼び出し、!bokの場合-1を出力する。
int[][] aryを用意し、全要素をINT\_MAXで初期化する。
Deque<Point> queを用意し、ary[0][0]=0を入れ、queに(0, 0)を追加する。
queが空になるまでループし、p=que.poll()に取出し、now=ary[p.x][p.y]を入れる。
setの全要素をpdにループし、dを0からDXXサイズをループし、
x=p.x+pd.x*DXX[d]+pd.y*DXY[d]
y=p.y+pd.x*DYX[d]+pd.y*DYY[d]
を計算し、0<=x && x<n && 0<=y && y<nの場合に、v=ary[x][y]を入れ、
now<vの場合ary[x][y]=now+1を入れ、queに(x, y)を追加する。
ループが終了したら、ary[i][j]を出力する。

init内で、bok=falseを用意し、min=Math.min(n*n, m)を入れる。
iを0からi*i<=mまでループし、jをi+1からj*j<=mまでループし、
x=i*i+j*jを計算し、x==mの場合、setに(i, j)を追加し、
i==1 || j==1の場合bok=trueをセットする。
WA23個。TLE5個。

# Main11
Main01を元に、問題文の誤解を修正する。（全体で1個-1を出力していた）
!bokの場合-1を出力する。　を削除する。
ary[i][j]を出力する。
を
v=ary[i][j]を入れ、v==INT\_MAXの場合、v=-1を更新する。
WA1個。TLE11個。

# Main


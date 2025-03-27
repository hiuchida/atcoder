# Main
a,b,c,d,e,f,xを読み込む。
calc(a,b,c,x)を呼び出し、xtに入れる。
同様にcalc(d,e,f,x)を呼び出し、xaに入れる。
xt>xa、xt<xa、その他を判定する。

calc()内で、ans=0で初期化する。
x>0の間ループし、min(a,x)をaaに入れる。
ans+=aa\*bを加え、残り時間x-=aa、x-=cを引く。
ループが終了したらansを返す。


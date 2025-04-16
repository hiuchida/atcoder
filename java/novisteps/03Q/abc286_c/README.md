# Main
n,a,b,sを読み込み、char[] aryに展開する。
ans=LONG\_MAXに初期化する。
iを0からn-1までループし、c=a\*iを用意し、cnt=check(ary, i)を呼び出し、c+=b\*cntを加え、ansにcの最小値を更新する。
ansを出力する。

checkの中で、ans=0を用意し、iを0からn/2回ループし、
i1=(i+st)%n、i2=(n-1-i+st)%nを入れ、buf[i1]!=buf[i2]の場合、ans++をカウントする。
ansを返す。

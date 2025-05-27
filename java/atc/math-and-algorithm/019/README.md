# Main
n個を読み込み、int[] cntを用意する。
n回ループし、aを読み込み、cnt[a]++をカウントする。
	
c1=calc_c(cnt[1], 2)、c2=calc_c(cnt[2], 2)、c3=calc_c(cnt[3], 2)を入れ、c1+c2+c3を出力する。

calc_cの中で、
ans=1を用意し、
iを0からk回ループし、ans*=n-iを入れる。
iを1からkまでループし、ans/=iを入れる。
ansを返す。

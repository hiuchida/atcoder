# Main
nを読み込み、int[] aryに読み込み、sum=0を用意し、sum+=ary[i]を計算する。
ans=MAX\_INT、lt=0を用意し、iを0からn-1回ループし、
lt+=ary[i]、rt=sum-lt、x=abs(lt-rt)を入れ、ansにxの最小値を更新する。
ループ終了したら、ansを出力する。

# Main
n,tを読み込み、int[] aryに読み込む。
ans=0を用意し、iを0からn回ループし、x=tを入れ、
i<n-1の場合、xにary[i+1]-ary[i]の最小値を更新する。
ans+=xを加える。
ループ終了したら、ansを出力する。

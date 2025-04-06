# Main
nを読み込み、int[] aryに読み込む。
min=ary[0]、ans=0を用意し、iを0からn回ループする。
min>ary[i]のときans+=min-ary[i]を加え、
min<ary[i]のときmin=ary[i]を更新する。
ループが終了したら、ansを出力する。

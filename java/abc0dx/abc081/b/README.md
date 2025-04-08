# Main
nを読み込み、int[] aryに読み込む。
ans=INT\_MAXを用意し、iを0からn回ループし、x=calc(ary[i])を呼び出し、ansにxの最小値を更新する。
ループ終了したらansを出力する。

calcの中で、ans=0を用意し、n%2==0の間ループし、
ans++、n/=2を更新する。
ループ終了したらansを返す。

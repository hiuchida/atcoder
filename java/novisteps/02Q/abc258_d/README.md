# Main
n,xを読み込み、int[] aa,abに読み込む。
long[] sumを用意し、sum[i+1]=sum[i]+aa[i]+ab[i]を計算する。
ans=INT\_MAXを用意し、iをn回ループし、
c=i+1、y=sum[i+1]が各ステージを1回クリアしたときの時間。
x>cとクリア回数が足りない場合、y+=(x-c)*ab[i]を加える。
ansをyの最小値で更新する。
ループが終了したら、ansを出力する。

# Main
n,t,aを読み込み、d=MAX\_INT、ans=0を用意する。
iを1からn回ループし、hを読み込み、x=t-h*0.006を計算し、dd=abs(x-a)を入れる。
d>ddの場合、ans=i、d=ddを更新する。
ループが終了したら、ansを出力する。

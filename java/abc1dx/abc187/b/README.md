# Main
nを読み込み、int[] ax,ayに読み込む。
ans=0を用意し、iを0からn回ループし、jをi+1からn-1までループする。
dx=abs(ax[i]-ax[j])、dy=abs(ay[i]-ay[j])を入れ、dx>=dyの場合、ans++をカウントする。

ansを出力する。

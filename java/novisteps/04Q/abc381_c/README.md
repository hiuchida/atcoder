# Main
2重ループで、'/'を探し、左右の等間隔の'1'と'2'の個数を数える。
範囲チェックif (l < 0 || n-1 < r)が効くので番兵は不要。

d==0の場合のみでも、d*2+1が1なので、ans=1の初期値は不要。

解説は両端をチェックしているが、iが範囲内で、j<i<kのため、j>=Nとk<0は必ずfalseとなる。

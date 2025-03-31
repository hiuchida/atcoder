# Main
n,q,sを読み込み、head=0を用意する。
iをq回ループし、t,xを読み込む。
t==1のとき、head=(head+n-x)%nで先頭位置を進める。
t==2のとき、pos=(head+x-1)%nを計算し、posの文字を出力する。

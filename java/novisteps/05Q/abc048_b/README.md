# Main01
a,b,xを読み込む。
aa=(a-1)/x、bb=b/xを入れ、bb-aaを出力する。
WA3個。

# Main
Main01を元に、
a==0の場合は、bb+1を出力する。(0もxの倍数にカウントする）

# Main\_fix
2016/12/04のソースでは、ceil()とfloor()を定義し、
aa=ceil(a, x)、bb=floor(b, x)を計算し、
aa<=bbのとき(bb-aa)/x+1を出力する。
それ以外は0を出力する。


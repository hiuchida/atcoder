# Main01-Main07
int[] aryに読み込み、累積和をint\[\] sumに計算する。
高速化のためにsumをソートしたが、この時点で回答が間違っている。
WA15個。
WA12個。RE14個。ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 3
WA15個。
WA16個。
WA17個。TLE2個。
WA13個。TLE2個。
WA17個。TLE2個。

# Main08-Main09
int[] aryに読み込み、累積和をint\[\] sumに計算する。
高速化のためにsumをソートしたが、この時点で回答が間違っている。
WA19個。
WA19個。

# Main
lを先にループし、内側にr=l+1をループしていたので、n^2から改善できなかった。
rを先にループし、内側にl=r-1をループした場合、sum\[0\]からsum\[l\]までの中にsum\[r\]-kの個数が分かればよい。
sumは重複するので、Counterでrより左側のsumをカウントする。

